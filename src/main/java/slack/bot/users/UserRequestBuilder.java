package slack.bot.users;

import slack.bot.app.RequestBuilder;

/**
 * Created by Олег on 18.01.2017.
 */
public class UserRequestBuilder extends RequestBuilder {
    private final String CHANNEL_HOST = "users.list";

    private UserRequestBuilder(String token) {
        super(token, null);
    }

    public static Builder newBuilder(String token) {
        return new UserRequestBuilder(token).new Builder();
    }

    @Override
    public String toString() {
        return SLACK_HOST + CHANNEL_HOST + "?token=" + getToken();
    }

    public class Builder {
        private String precense;
        private StringBuilder requestPath = new StringBuilder();

        public Builder setPrecense(String precense) {
            this.precense = precense;
            requestPath.append("&precense=" + precense);
            return this;
        }

        public Builder build() {
            return this;
        }

        @Override
        public String toString() {
            return UserRequestBuilder.this.toString() + requestPath.toString() + "&pretty=1";
        }
    }
}
