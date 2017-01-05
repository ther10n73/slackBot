package slack.bot.app;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    public CheckingMessages(HttpConnection httpConnection, SendMessages sendMessages) {
        this.httpConnection = httpConnection;
        this.sendMessages = sendMessages;
    }

    public ChannelHistoryCache check() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        long now = Instant.now().getMillis()/1000;
        long period = 15000L;
        String uri = ChanelRequestBuilder.newBuilder(HttpConstants.TOKEN, "C3K514LTC").setLatest(now).setOldest(now - period).build().toString();
        System.out.println(uri);
        ChannelHistoryCache channel = sendMessages.sendMessages(uri, ChannelHistoryCache.class);
        channel.getMessages().stream()
                .filter(m -> m.getText().contains("U3K4J0L84") && m.getText().toLowerCase().contains("привет"))
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
}
