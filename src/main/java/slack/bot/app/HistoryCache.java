package slack.bot.app;

import org.apache.log4j.Logger;
import slack.bot.channel.ChannelHistory;
import slack.bot.channel.ChannelHistory.Message;
import slack.bot.chat.SendMessages;
import java.util.ArrayList;
import java.util.List;

public class HistoryCache {
    private final SendMessages sendMessages;
    private List<Message> messages;
    private static final Logger logger = Logger.getLogger(HistoryCache.class);

    public HistoryCache(SendMessages sendMessages) {
        this.sendMessages = sendMessages;
        if (messages == null) {
            messages = new ArrayList<>();
        }
    }

    List<Message> execute(String uri) {
        try {
            logger.info("channels.history: " + uri);
            sendMessages.sendMessages(uri, ChannelHistory.class)
                    .getMessages()
                    .forEach(m -> {
                        if (!messages.contains(m)) {
                            m.setSendAnswer(false);
                            messages.add(m);
                        } else if (!(m.getEdited() == null)) {
                            messages.add(m);
                        }
                    });
        } catch (Exception e) {
            logger.error("Can't get history message.", e);
        }
        return messages;
    }
}
