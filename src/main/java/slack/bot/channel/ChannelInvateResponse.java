package slack.bot.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by Олег on 18.01.2017.
 */
public class ChannelInvateResponse {
    @JsonProperty("ok")
    private String result;
    @JsonProperty("channel")
    private Map<String, String> channel;
    @JsonProperty("row")
    private Map<String, String> row;
}
