package slack.bot.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ChannelHistoryCache {
    @JsonProperty("ok")
    private String result;
    @JsonProperty("latest")
    private String latest;
    @JsonProperty("oldest")
    private String oldest;
    @JsonProperty("messages")
    private List<Message> messages;
    @JsonProperty("has_more")
    private String hasMore;

    public String getResult() {
        return result;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getHasMore() {
        return hasMore;
    }

    @Override
    public String toString() {
        return "ChannelHistoryCache{" +
                "result='" + result + '\'' +
                ", messages=" + messages +
                ", hasMore='" + hasMore + '\'' +
                '}';
    }

    public static class Message {
        @JsonProperty("inviter")
        private String inviter;
        @JsonProperty("text")
        private String text;
        @JsonProperty("edited")
        private Map<String, String> edited;
        @JsonProperty("bot_id")
        private String botId;
        @JsonProperty("username")
        private String userName;
        @JsonProperty("type")
        private String type;
        @JsonProperty("subtype")
        private String subType;
        @JsonProperty("user")
        private String user;
        @JsonProperty("ts")
        private String ts;

        public String getBotId() {
            return botId;
        }

        public String getInviter() {
            return inviter;
        }

        public String getText() {
            return text;
        }

        public String getUserName() {
            return userName;
        }

        public String getType() {
            return type;
        }

        public String getSubType() {
            return subType;
        }

        public String getUser() {
            return user;
        }

        public String getTs() {
            return ts;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "inviter='" + inviter + '\'' +
                    ", text='" + text + '\'' +
                    ", botId='" + botId + '\'' +
                    ", userName='" + userName + '\'' +
                    ", type='" + type + '\'' +
                    ", subType='" + subType + '\'' +
                    ", user='" + user + '\'' +
                    ", ts='" + ts + '\'' +
                    '}';
        }
    }
}
