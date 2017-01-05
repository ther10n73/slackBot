package slack.bot.app;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import slack.bot.channel.ChanelRequestBuilder;
import slack.bot.channel.ChannelHistoryCache;
import slack.bot.chat.MessageBuilder;
import slack.bot.chat.MessageSendResult;
import slack.bot.chat.SendMessages;
import slack.bot.connection.HttpConnection;
import slack.bot.connection.HttpConstants;

import java.io.IOException;

@Component
public class CheckingMessages {
    private final HttpConnection httpConnection;
    private final SendMessages sendMessages;
    private final static long PERIOD = 1000L;
    private final String id = "U3K4J0L84";

    @Autowired
    public CheckingMessages(HttpConnection httpConnection, SendMessages sendMessages) {
        this.httpConnection = httpConnection;
        this.sendMessages = sendMessages;
    }

    public ChannelHistoryCache check() throws IOException {
        String uri = ChanelRequestBuilder.newBuilder(HttpConstants.TOKEN, "C3K514LTC")
                .setLatest(getTimeSeconds())
                .setOldest(getTimeMinusPeriod())
                .build().toString();
        System.out.println(uri);
        ChannelHistoryCache channel = sendMessages.sendMessages(uri, ChannelHistoryCache.class);
        channel.getMessages().stream()
                .filter(m -> m.getText().contains(id) && m.getText().toLowerCase().contains("привет"))
                .findFirst()
                .map(t -> {
                    try {
                        String answer = MessageBuilder.newBuilder(HttpConstants.TOKEN,"C3K514LTC", "Йо").build().toString();
                        sendMessages.sendMessages(answer, MessageSendResult.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return channel;
                });
        return null;
    }

    private long getTimeSeconds() {
        return Instant.now().getMillis()/1000L;
    }

    private long getTimeMinusPeriod() {
        return getTimeSeconds() - PERIOD;
    }
}
