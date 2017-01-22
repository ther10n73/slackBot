package slack.bot.app;

import slack.bot.users.UserRequestBuilder;

public class RequestBuilder {
    public final static String SLACK_HOST = "https://slack.com/api/";
    public final static String END_OF_REQUEST = "&pretty=1";
    private final String token;
    private final String channel;

    public RequestBuilder(String token, String channel) {
        this.token = token;
        this.channel = channel;
    }

    public String getToken() {
        return token;
    }

    public String getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "?token=" + token + "&channel=" + channel;
    }
}
