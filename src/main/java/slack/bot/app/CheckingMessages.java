package slack.bot.app;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import slack.bot.channel.ChanelRequestBuilder;
import slack.bot.channel.ChannelHistory.Message;
import slack.bot.channel.ChannelList;
import slack.bot.chat.MessageBuilder;
import slack.bot.chat.MessageSendResult;
import slack.bot.chat.SendMessages;
import slack.bot.connection.HttpConstants;

import java.io.IOException;
import java.util.List;

@Component
public class CheckingMessages {
    private final HistoryCache historyCache;
    private final ChatLists chatLists;
    private final SendMessages sendMessages;
    private final static long PERIOD = 5000L;
    private final String id = "U3K4J0L84";

    @Autowired
    public CheckingMessages(HistoryCache historyCache, SendMessages sendMessages, ChatLists chatLists) {
        this.chatLists = chatLists;
        this.historyCache = historyCache;
        this.sendMessages = sendMessages;
    }

    public void check() throws IOException {
        chatLists.getChannelAndGroupsList().forEach(g -> {
        String uri = ChanelRequestBuilder.newBuilder(HttpConstants.TOKEN, g.getId())
                .setLatest(getTimeSeconds())
                .setOldest(getTimeMinusPeriod())
                .build().toString();
        System.out.println(uri);
            try {
                List<Message> msg = historyCache.execute(uri);
                msg.forEach(m -> sendAnswer(m, g));
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        });
    }

    private long getTimeSeconds() {
        return Instant.now().getMillis()/1000L;
    }

    private long getTimeMinusPeriod() {
        return getTimeSeconds() - PERIOD;
    }

    private void sendAnswer(Message m, ChannelList.Channel g) {
        if (m.getText().contains("U3K4J0L84") && !m.isSendAnswer()) {
                String answer = MessageBuilder
                        .newBuilder(HttpConstants.TOKEN, g.getId(), "привет")
                        .build().toString();
                try {
                    System.out.println(m.hashCode());
                    sendMessages.sendMessages(answer, MessageSendResult.class);
                    m.setSendAnswer(true);
                } catch (IOException e) {
                    e.printStackTrace();
                    m.setSendAnswer(false);
                }
        }
    }
}
