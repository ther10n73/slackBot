package slack.bot.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import slack.bot.channel.ChannelHistory.Message;
import slack.bot.channel.ChannelInvateResponse;
import slack.bot.channel.ChannelList.Channel;
import slack.bot.chat.MessageBuilder;
import slack.bot.chat.MessageSendResult;
import slack.bot.chat.SendMessages;
import slack.bot.connection.HttpConstants;
import slack.bot.users.UserCache;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Олег on 17.01.2017.
 */
public class AnswerApi {
    private final SendMessages sendMessages;
    private final UserCache userCache;
    private Matcher matcher;
    private static final Logger logger = Logger.getLogger(AnswerApi.class);
    private static final String CHANNEL_INVITE = "channels.invite";
    private static final Pattern solutionPattern = Pattern.compile("^(.*<@U3K4J0L84>)([\\s\\S]*)?([Пп]ривет|[Дд]обрый день)(.*)$");
    private static final Pattern invitePattern = Pattern.compile("^(.*<@U3K4J0L84>)([Пп\\s\\S]*)?(озови|ригласи)(.*)$");
    private static final Pattern userPattern = Pattern.compile("(.*)(<@)([A-Z0-9]*)(>)(.*)");
    private static final List<String> SOLUTION_MAP = Arrays.asList("Привет, %1s", "Здравствуй, %1s", "Привествую Вас, %1s");


    @Autowired
    public AnswerApi(SendMessages sendMessages, UserCache userCache) {
        this.sendMessages = sendMessages;
        this.userCache = userCache;
    }

    synchronized void execute(Message message, Channel channel) {
        matcher = solutionPattern.matcher(message.getText());
        if (matcher.matches()) {
            hello(message, channel.getId());
        }
        matcher = invitePattern.matcher(message.getText());
        if (matcher.matches()) {
            matcher = userPattern.matcher(message.getText());
            toInvite(channel.getId(), matcher.group(3));
        }
    }
    private void toInvite(String channel, String user) {
        String uri = RequestBuilder.SLACK_HOST + CHANNEL_INVITE + new RequestBuilder(HttpConstants.TOKEN, channel) + "&user=" + user + RequestBuilder.END_OF_REQUEST;
        try {
            ChannelInvateResponse response = sendMessages.sendMessages(uri, ChannelInvateResponse.class);
        } catch (IOException e) {
            logger.error("Failed send invite.", e);
        }
    }

    public void hello(Message m, String channelId) {
        int i = new Random().nextInt(SOLUTION_MAP.size());
        String name = userCache.execute().get(m.getUser()).getName();
        String answer = MessageBuilder
                .newBuilder(HttpConstants.TOKEN, channelId, URLEncoder.encode(String.format(SOLUTION_MAP.get(i), name)))
                .build().toString();
        try {
            sendMessages.sendMessages(answer, MessageSendResult.class);
            m.setSendAnswer(true);
        } catch (IOException e) {
            logger.error("Failed send message.", e);
            m.setSendAnswer(false);
        }
    }

    public void fromKick() {
    }
}
