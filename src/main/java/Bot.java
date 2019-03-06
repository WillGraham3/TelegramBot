import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Properties;

public class Bot extends TelegramLongPollingBot{
    //Field and constructor needed for connecting with bot properties file
    private Properties properties;

    public Bot(Properties properties) {
        this.properties = properties;
    }

    //method send message to user,which have message send by user and response to a request
    public void sendMsg(Message message, String text){

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    //main logic responding to user request
    //TODO need finish this method
    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message != null && message.hasText()){
            switch (message.getText()){
                case "/help":
                    sendMsg(message,"What do you need?");
                    break;
                case "/setting":
                    sendMsg(message,"what you want change?");
                    break;
                default:
            }

        }

    }

    @Override
    public String getBotUsername() {
        return properties.getProperty("BotUsername");
    }
    @Override
    public String getBotToken() {
        return properties.getProperty("BotToken");
    }
}
