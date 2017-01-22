package slack.bot.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import slack.bot.connection.HttpConnection;

import java.io.IOException;
import org.apache.log4j.Logger;

public class SendMessages {
    private final HttpConnection httpConnection;
    private final ObjectMapper mapper;
    private final static Logger logger = Logger.getLogger(SendMessages.class);

    public SendMessages(HttpConnection httpConnection, ObjectMapper mapper) {
        this.httpConnection = httpConnection;
        this.mapper = mapper;
    }

    public <T> T sendMessages(String uri, Class<T> clazz) throws IOException {
        String response = httpConnection.send(uri);
        logger.info("respose: " + response);
        return mapper.readValue(response.getBytes(), clazz);
    }
}
