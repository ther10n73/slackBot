package slack.bot.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import slack.bot.chat.SendMessages;
import slack.bot.connection.HttpConnection;

@Configuration
public class AppConfig {
    private RealTimeMessage realTimeMessage = new RealTimeMessage(new HttpConnection());
    private CheckingMessages checkingMessages = new CheckingMessages(new HttpConnection(), new SendMessages(new HttpConnection(), new ObjectMapper()));

    @Bean
    public BotMain main() {
        return new BotMain(realTimeMessage, checkingMessages);
    }
}
