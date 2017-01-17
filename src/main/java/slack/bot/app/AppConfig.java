package slack.bot.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import slack.bot.chat.SendMessages;
import slack.bot.connection.HttpConnection;

@Configuration
public class AppConfig {
    private SendMessages sendMessages = new SendMessages(new HttpConnection(), new ObjectMapper());
    private CheckingMessages checkingMessages = new CheckingMessages(new HistoryCache(sendMessages), sendMessages, new ChatLists(sendMessages));

    @Bean
    public BotMain main() {
        return new BotMain(checkingMessages);
    }
}
