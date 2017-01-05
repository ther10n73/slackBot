package slack.bot.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import slack.bot.channel.ChanelRequestBuilder;
import slack.bot.channel.ChannelHistoryCache;
import slack.bot.connection.HttpConnection;
import slack.bot.connection.HttpConstants;

import java.io.IOException;

public class SendMessages {
    private final HttpConnection httpConnection;
    private final ObjectMapper mapper;

    public SendMessages(HttpConnection httpConnection, ObjectMapper mapper) {
        this.httpConnection = httpConnection;
        this.mapper = mapper;
    }

    public <T> T sendMessages(String uri, Class<T> clazz) throws IOException {
        System.out.println("chat.meMessage: uri = " + uri);
        String response = httpConnection.send(uri);
        System.out.println(response);
        return mapper.readValue(response.getBytes(), clazz);
    }
}
