package slack.bot.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class UsersList {
    @JsonProperty("ok")
    private String result;
    @JsonProperty("members")
    private List<User> users;
    @JsonProperty("cache_ts")
    private String cacheTs;

    public String getResult() {
        return result;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getCacheTs() {
        return cacheTs;
    }

    @Override
    public String toString() {
        return "UsersList{" +
                "result=" + result +
                ", users=" + users +
                '}';
    }
    public static class User {
        @JsonProperty("id")
        private String id;
        @JsonProperty("team_id")
        private String teamId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("deleted")
        private String deleted;
        @JsonProperty("status")
        private String status;
        @JsonProperty("color")
        private String color;
        @JsonProperty("real_name")
        private String realName;
        @JsonProperty("tz")
        private String tz;
        @JsonProperty("tz_label")
        private String tzLabel;
        @JsonProperty("tz_offset")
        private String tzOffset;
        @JsonProperty("profile")
        private Map<String, String> profile;
        @JsonProperty("is_admin")
        private String isAdmin;
        @JsonProperty("is_owner")
        private String isOwner;
        @JsonProperty("is_primary_owner")
        private String isPrimaryOwner;
        @JsonProperty("is_restricted")
        private String isRestricted;
        @JsonProperty("is_ultra_restricted")
        private String isUltraRestricted;
        @JsonProperty("is_bot")
        private String isBot;

        public String getId() {
            return id;
        }

        public String getTeamId() {
            return teamId;
        }

        public String getName() {
            return name;
        }

        public String getDeleted() {
            return deleted;
        }

        public String getStatus() {
            return status;
        }

        public String getColor() {
            return color;
        }

        public String getRealName() {
            return realName;
        }

        public String getTz() {
            return tz;
        }

        public String getTzLabel() {
            return tzLabel;
        }

        public String getTzOffset() {
            return tzOffset;
        }

        public Map<String, String> getProfile() {
            return profile;
        }

        public String getIsAdmin() {
            return isAdmin;
        }

        public String getIsOwner() {
            return isOwner;
        }

        public String getIsPrimaryOwner() {
            return isPrimaryOwner;
        }

        public String getIsRestricted() {
            return isRestricted;
        }

        public String getIsUltraRestricted() {
            return isUltraRestricted;
        }

        public String getIsBot() {
            return isBot;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", teamId='" + teamId + '\'' +
                    ", name='" + name + '\'' +
                    ", deleted='" + deleted + '\'' +
                    ", status='" + status + '\'' +
                    ", color='" + color + '\'' +
                    ", realName='" + realName + '\'' +
                    ", tz='" + tz + '\'' +
                    ", tzLabel='" + tzLabel + '\'' +
                    ", tzOffset='" + tzOffset + '\'' +
                    ", profile=" + profile +
                    ", isAdmin='" + isAdmin + '\'' +
                    ", isOwner='" + isOwner + '\'' +
                    ", isPrimaryOwner='" + isPrimaryOwner + '\'' +
                    ", isRestricted='" + isRestricted + '\'' +
                    ", isUltraRestricted='" + isUltraRestricted + '\'' +
                    ", isBot='" + isBot + '\'' +
                    '}';
        }
    }
}
