package slack.bot.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UsersList {
    @JsonProperty("ok")
    private String result;
    @JsonProperty("members")
    private List<User> users;
    @JsonProperty("cache_ts")
    private String cacheTs;

    @Override
    public String toString() {
        return "UsersList{" +
                "result=" + result +
                ", users=" + users +
                '}';
    }
}
