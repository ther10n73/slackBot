package slack.bot.chat;

import com.sun.deploy.util.StringUtils;
import slack.bot.app.RequestBuilder;

public class MessageBuilder extends RequestBuilder {
    public final static String ME_MESSAGE = "chat.meMessage";
    private final String text;

    private MessageBuilder(String token, String channel, String text) {
        super(token, channel);
        this.text = text;
    }

    public static Builder newBuilder(String token, String channel, String text) {
        return new MessageBuilder(token, channel, text).new Builder();
    }

    @Override
    public String toString() {
        return SLACK_HOST + ME_MESSAGE + super.toString() + "&text=" + text;
    }

    public class Builder {
        private String parse;
        private String linkNames;
        private String attachments;
        private String unfurlLinks;
        private String unfurlMedia;
        private String userName;
        private String asUser;
        private String iconUrl;
        private String iconEmoji;
        private StringBuilder request = new StringBuilder();

        public Builder setParse(String parse) {
            this.parse = parse;
            request.append("&parse=" + parse);
            return this;
        }

        public Builder setIconEmoji(String iconEmoji) {
            this.iconEmoji = iconEmoji;
            request.append("&icon_emoji=" + iconEmoji);
            return this;
        }

        public Builder setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
            request.append("&icon_url=" + iconUrl);
            return this;
        }

        public Builder setAsUser(String asUser) {
            this.asUser = asUser;
            request.append("&as_user=" + asUser);
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            request.append("&user_name=" + userName);
            return this;
        }

        public Builder setUnfurlMedia(String unfurlMedia) {
            this.unfurlMedia = unfurlMedia;
            request.append("&unfurl_media=" + unfurlMedia);
            return this;
        }

        public Builder setUnfurlLinks(String unfurlLinks) {
            this.unfurlLinks = unfurlLinks;
            request.append("&unfurl_links=" + unfurlLinks);
            return this;
        }

        public Builder setAttachments(String attachments) {
            this.attachments = attachments;
            request.append("&attachments=" + attachments);
            return this;
        }

        public Builder setLinkNames(String linkNames) {
            this.linkNames = linkNames;
            request.append("&link_names=" + linkNames);
            return this;
        }

        public Builder build() {
            return this;
        }

        @Override
        public String toString() {
            return MessageBuilder.this.toString() + request.toString() + "&pretty=1";
        }
    }
}
