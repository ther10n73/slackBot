package slack.bot.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import slack.bot.chat.SendMessages;
import slack.bot.connection.HttpConnection;
import slack.bot.users.UserCache;

@Configuration
public class AppConfig {
    private SendMessages sendMessages = new SendMessages(new HttpConnection(), new ObjectMapper());
    private CheckingMessages checkingMessages = new CheckingMessages(new HistoryCache(sendMessages), new AnswerApi(sendMessages, new UserCache(sendMessages)), new ChatLists(sendMessages));

    static {
        init();
    }
    @Bean
    public BotMain main() {
        return new BotMain(checkingMessages);
    }

    private static void init() {
        PropertyConfigurator.configure("D:\\github\\slackBot\\src\\main\\resourses\\log4j.properties");
    }
}
