package slack.bot.channel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ChannelList {
    @JsonProperty("ok")
    private String result;
    @JsonProperty("channels")
    private List<Channel> channels;

    public String getResult() {
        return result;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public static class Channel {
        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("is_channel")
        private String isChannel;
        @JsonProperty("created")
        private String created;
        @JsonProperty("creator")
        private String creator;
        @JsonProperty("is_archived")
        private String isArchived;
        @JsonProperty("is_general")
        private String isGeneral;
        @JsonProperty("is_member")
        private String isMember;
        @JsonProperty("members")
        private HashSet<String> members;
        @JsonProperty("topic")
        private Map<String, String> topic;
        @JsonProperty("purpose")
        private Map<String, String> purpose;
        @JsonProperty("previous_names")
        private List<String> previousNames;
        @JsonProperty("num_members")
        private String numMembers;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getIsChannel() {
            return isChannel;
        }

        public String getCreated() {
            return created;
        }

        public String getCreator() {
            return creator;
        }

        public String getIsArchived() {
            return isArchived;
        }

        public String getIsGeneral() {
            return isGeneral;
        }

        public String getIsMember() {
            return isMember;
        }

        public HashSet<String> getMembers() {
            return members;
        }

        public Map<String, String> getTopic() {
            return topic;
        }

        public Map<String, String> getPurpose() {
            return purpose;
        }

        public List<String> getPreviousNames() {
            return previousNames;
        }

        public String getNumMembers() {
            return numMembers;
        }
    }
}
