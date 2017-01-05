package slack.bot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slack.bot.connection.HttpConnection;
import slack.bot.connection.HttpConstants;
import java.io.IOException;

@Service
public class RealTimeMessage {
    private final HttpConnection connection;

    @Autowired
    public RealTimeMessage(HttpConnection connection) {
        this.connection = connection;
    }

    public void start() throws IOException {
        String uri = RequestBuilder.SLACK_HOST + "rtm.start?token=" + HttpConstants.TOKEN + "&pretty=1";
        System.out.println(connection.send(uri));
    }
}
