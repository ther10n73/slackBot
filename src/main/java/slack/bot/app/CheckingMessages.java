package slack.bot.app;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import slack.bot.channel.ChanelHistoryBuilder;
import slack.bot.channel.ChannelHistory.Message;
import slack.bot.channel.ChannelList.Channel;
import slack.bot.chat.SendMessages;
import slack.bot.connection.HttpConstants;

import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

@Component
public class CheckingMessages {
    private final HistoryCache historyCache;
    private final ChatLists chatLists;
    private final AnswerApi answerApi;
    private final static long PERIOD = 5000L;
    private final String id = "U3K4J0L84";
    private static final Logger logger = Logger.getLogger(CheckingMessages.class);

    @Autowired
    public CheckingMessages(HistoryCache historyCache, AnswerApi answerApi, ChatLists chatLists) {
        this.chatLists = chatLists;
        this.historyCache = historyCache;
        this.answerApi = answerApi;
    }

    public void check() throws IOException {
        chatLists.getChannelAndGroupsList().forEach(g -> {
        String uri = ChanelHistoryBuilder.newBuilder(HttpConstants.TOKEN, g.getId())
                .setLatest(getTimeSeconds())
                .setOldest(getTimeMinusPeriod())
                .build().toString();
            try {
                List<Message> msg = historyCache.execute(uri);
                msg.forEach(m -> sendAnswer(m, g));
            } catch (Exception e) {
                logger.error("Failed send answer for request.", e);
            }
        });
    }

    private long getTimeSeconds() {
        return Instant.now().getMillis()/1000L;
    }

    private long getTimeMinusPeriod() {
        return getTimeSeconds() - PERIOD;
    }

    private void sendAnswer(Message m, Channel g) {
        if (m.getText().contains("U3K4J0L84") && !m.isSendAnswer()) {
            answerApi.execute(m, g);
        }
    }
}
