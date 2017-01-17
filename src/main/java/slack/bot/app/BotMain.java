package slack.bot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BotMain {
    private final CheckingMessages checkingMessages;
    private boolean isProcess = true;

    @Autowired
    public BotMain(CheckingMessages checkingMessages) {
        this.checkingMessages = checkingMessages;
    }

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        ctx.getBean(BotMain.class).run();
    }

    @Bean
    public void run() {
        Runnable thread = new Runnable() {
            public void run() {
                while (isProcess) {
                    try {
                        checkingMessages.check();
                        Thread.sleep(5000L);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        isProcess = false;
                    }
                }
            }
        };
        thread.run();
    }
}