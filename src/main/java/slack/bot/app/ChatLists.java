package slack.bot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import slack.bot.channel.ChannelList;
import slack.bot.channel.ChannelList.Channel;
import slack.bot.chat.SendMessages;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class ChatLists {
    private final String CHANNEL_LISTS = "https://slack.com/api/channels.list?token=xoxb-121154020276-BR01k89pKu5oSjynnNwWgc6c&pretty=1";
    private final SendMessages sendMessages;

    @Autowired
    public ChatLists(SendMessages sendMessages) {
        this.sendMessages = sendMessages;
    }

    public Set<Channel> getChannelAndGroupsList() {
        Set<Channel> membersList = new HashSet<>();
        try {
            ChannelList lists = sendMessages.sendMessages(CHANNEL_LISTS, ChannelList.class);
            lists.getChannels().stream()
                    .filter(c -> c.getMembers().contains("U3K4J0L84"))
                    .findAny()
                    .map(membersList::add);
            System.out.println(membersList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return membersList;
    }
}
