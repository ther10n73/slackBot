package slack.bot.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import slack.bot.chat.SendMessages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static slack.bot.connection.HttpConstants.TOKEN;
/**
 * Created by Олег on 19.01.2017.
 */
@Component
public class UserCache {
    private final SendMessages sendMessages;
    private Map<String, UsersList.User> users = new HashMap<>();

    @Autowired
    public UserCache(SendMessages sendMessages) {
        this.sendMessages = sendMessages;
    }

    public Map<String, UsersList.User> execute() {
        try {
            UsersList user = sendMessages.sendMessages(UserRequestBuilder.newBuilder(TOKEN).build().toString(), UsersList.class);
            user.getUsers().forEach(u -> users.put(u.getId(), u));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
