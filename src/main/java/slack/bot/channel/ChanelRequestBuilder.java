package slack.bot.channel;

import slack.bot.app.RequestBuilder;

public class ChanelRequestBuilder extends RequestBuilder {
    private final static String CHANNEL_HISTORY = "channels.history";
    private ChanelRequestBuilder(String token, String channel) {
        super(token, channel);
    }

    public static Builder newBuilder(String token, String channel) {
        return new ChanelRequestBuilder(token, channel).new Builder();
    }

    @Override
    public String toString() {
        return SLACK_HOST + CHANNEL_HISTORY + super.toString();
    }

    public class Builder {
        private long latest;
        private long oldest;
        private String inclusive;
        private String count;
        private String unreads;
        private StringBuilder requestPath = new StringBuilder();

        public Builder setLatest(long latest) {
            this.latest = latest;
            requestPath.append("&latest=" + latest);
            return this;
        }

        public Builder setOldest(long oldest) {
            this.oldest = oldest;
            requestPath.append("&oldest=" + oldest);
            return this;
        }

        public Builder setInclusive(String inclusive) {
            this.inclusive = inclusive;
            requestPath.append("&inclusive=" + inclusive);
            return this;
        }

        public Builder setCount(String count) {
            this.count = count;
            requestPath.append("&count=" + count);
            return this;
        }

        public Builder setUnreads(String unreads) {
            this.unreads = unreads;
            requestPath.append("&unreads=" + unreads);
            return this;
        }

        public Builder build() {
            return this;
        }

        @Override
        public String toString() {
            return ChanelRequestBuilder.this.toString() + requestPath.toString() + "&pretty=1";
        }
    }
}
