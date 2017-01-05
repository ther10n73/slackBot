package slack.bot.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class MessageSendResult {
    @JsonProperty("ok")
    private String result;
    @JsonProperty("ts")
    private String ts;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("message")
    private Map<String, String> message;

    public String getResult() {
        return result;
    }

    public String getTs() {
        return ts;
    }

    public String getChannel() {
        return channel;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessageSendResult{" +
                "result='" + result + '\'' +
                ", ts='" + ts + '\'' +
                ", channel='" + channel + '\'' +
                ", message-'" + message + '\'' +
                '}';
    }
}
