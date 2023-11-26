package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.*;

public class Bot extends TelegramLongPollingBot{
    private Map<Long, String> userStates = new HashMap<>();  // User states (language selection) to track conversation progress
    private TranslateServices translateRequest = new TranslateServices();
    public static String botUsername= "TellChuckNorrisJokesBot";

    private static final String botDesription= "This bot tells 101 different Chuck Norris jokes in any language." +
            " Choose your favorite language by typing \"set language [Spanish, for example]\". " +
            "Then type a number between 1 to 101 to get your joke!";

    private static final String botStart= "Choose the language you want the joke in by typing \"set language [language]\". " +
            "Then type a number between 1 to 101 to get your joke!";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text= getUserText(update);
            long chatId = update.getMessage().getChatId();

            if (update.getMessage().isCommand()) {
                checkIfUserSentValidCommand(text, chatId);
            }
            // User hasn't set a valid language yet
            else if (!userStates.containsKey(chatId)) {
                checkAndSetUserLanguage(chatId, text, false);
            }
            // User's language is set
            else {
                if (TextServices.isNumericInput(text)) { // User's input is numeric
                    if (TextServices.isValidJokeIndex(text)) {
                        String translatedText=TextServices.getTranslatedJoke(text, getUserLanguage(chatId), translateRequest);
                        sendText(chatId, translatedText);
                    }
                    else { // User's numeric input not a valid joke index
                        sendText(chatId, "Please enter a valid joke index (1-101).");
                    }
                }
                else { // User's input contains alphabetic text
                    checkAndSetUserLanguage(chatId, text, true);
                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    private void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //  Who are we sending a message to
                .text(what).build();    //  Message content
        try {
            execute(sm);                        //  Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //  Any error will be printed here
        }
    }

    private void checkAndSetUserLanguage(long chatId, String userCommand, boolean prevLanguageIsSet) {
        if (TextServices.isvalidCommand(userCommand)) {
                String userLanguage=TextServices.getLanguageFromCommand(userCommand);
                if (TextServices.canTranslateToLanguage(userLanguage)) {
                    setUserLanguage(chatId, userLanguage);
                    String translatedText=translateRequest.translateText("no problem", TextServices.getLanguageCode(userLanguage));
                    sendText(chatId, translatedText);
                }
                else {
                    sendText(chatId, "Invalid translation language. Please provide a valid language.");
                }
        }
        else {
            if (prevLanguageIsSet) {
                sendText(chatId, "Please enter a valid joke index (1-101) or set a new language by sending 'set language [language]'");
            }
            else {
                sendText(chatId, "Please set the language first by sending 'set language [language]'");
            }
        }
    }

    private void setUserLanguage(long chatId, String userLanguage) {
        String userLanguageCode=TextServices.getLanguageCode(userLanguage);
        userStates.put(chatId, userLanguageCode);
    }

    private String getUserLanguage(long chatId) {
        return userStates.get(chatId);
    }

    private String getUserText(Update userUpdate){
        Message msg = userUpdate.getMessage();
        return msg.getText().toLowerCase();
    }

    private void checkIfUserSentValidCommand(String msg, long chatId) {
        if (msg.equals("/description")) {
            sendText(chatId, botDesription);
        }
        else if (msg.equals("/start")) {
            sendText(chatId, botStart);
        }
        else {
            sendText(chatId, "Invalid Command");
        }
    }
}


