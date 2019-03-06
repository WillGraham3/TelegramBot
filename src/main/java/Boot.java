import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Boot {
    public static void main(String[] args) {
        //download bot properties file
        InputStream inputStream = Boot.class.getResourceAsStream("/bot.properties");
        Properties botprops = new Properties();

        try {
            botprops.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try{
            telegramBotsApi.registerBot(new Bot(botprops));
        }

        catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
}
