package slack.bot.app;

import slack.bot.channel.ChannelHistory;
import slack.bot.channel.ChannelHistory.Message;
import slack.bot.chat.SendMessages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryCache {
    private final SendMessages sendMessages;
    private List<Message> messages;

    public HistoryCache(SendMessages sendMessages) {
        this.sendMessages = sendMessages;
        if (messages == null) {
            messages = new ArrayList<>();
        }
    }

    List<Message> execute(String uri) {
        try {
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
            System.out.println(e.getMessage());
        }
        return messages;
    }
}
