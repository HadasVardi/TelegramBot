package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws TelegramApiException {
        TextServices.languageToCodeLanguageMap=TextServices.createLanguageToCodeLanguageMap();
        ChuckNorrisJokeScraper.initChuckNorrisJoke();
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();
        botsApi.registerBot(bot);
      //  bot.sendText(677498390L, "app is running!");  //The L just turns the Integer into a Long
    }
}