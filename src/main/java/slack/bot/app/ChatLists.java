package slack.bot.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import slack.bot.channel.ChannelList;
import slack.bot.channel.ChannelList.Channel;
import slack.bot.chat.SendMessages;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import static slack.bot.connection.HttpConstants.TOKEN;

@Component
public class ChatLists {
    private final String CHANNEL_LISTS = "https://slack.com/api/channels.list?token=" + TOKEN + "&pretty=1";
    private final SendMessages sendMessages;
    private static final Logger logger = Logger.getLogger(ChatLists.class);

    @Autowired
    public ChatLists(SendMessages sendMessages) {
        this.sendMessages = sendMessages;
    }

    Set<Channel> getChannelAndGroupsList() {
        Set<Channel> membersList = new HashSet<>();
        try {
            ChannelList lists = sendMessages.sendMessages(CHANNEL_LISTS, ChannelList.class);
            logger.info("channels.list: " + CHANNEL_LISTS);
            lists.getChannels().stream()
                    .filter(c -> c.getMembers().contains("U3K4J0L84"))
                    .findAny()
                    .map(membersList::add);
            logger.info("List of chats: " + membersList);
        } catch (IOException e) {
            logger.error("Can't get list of chats.", e);
        }
        return membersList;
    }
}
