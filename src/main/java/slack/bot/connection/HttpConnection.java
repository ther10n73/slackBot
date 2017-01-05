package slack.bot.connection;

import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

@Service
public class HttpConnection {
    private HttpsURLConnection connection;

    public String send(String uri) throws IOException {
        StringBuilder response = new StringBuilder();
        URL url = new URL(uri);
        connection = (HttpsURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null){
            response.append(line);
        }
        return response.toString();
    }
}
