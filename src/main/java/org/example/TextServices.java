package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextServices {
    public static boolean isvalidCommand(String text){
        return (text.startsWith("set language ") && !text.substring("set language ".length()).isEmpty());
    }

    public static boolean canTranslateToLanguage(String language) {
        return Main.languageToCodeLanguageMap.containsKey((language));
    }

    public static String getLanguageFromCommand(String command) {
        return command.substring("set language ".length()).toLowerCase();
    }

    public static String getLanguageCode(String language) {
        return Main.languageToCodeLanguageMap.get(language);
    }

    public static String getTranslatedJoke(String indexString, String targetLanguage, TranslateServices translateRequest) {
        int jokeIndex = Integer.parseInt(indexString);
        String EnglishJoke = ChuckNorrisJokeScraper.getEnglishJoke(jokeIndex);
        return translateRequest.translateText(EnglishJoke, targetLanguage);
    }

    public static boolean isNumericInput(String input) {
        try {
            parseTextToInt(input);
            return true;
        } catch (NumberFormatException e) { // input is not an integer
            return false;
        }
    }

    public static boolean isValidJokeIndex(String input) {
        if (isNumericInput(input)) {
            int intValue = parseTextToInt(input);
            return (intValue>=1 && intValue<=ChuckNorrisJokeScraper.NUMBEROFJOKES);
        }
        return false;
    }

    public static int parseTextToInt(String text) {
        return Integer.parseInt(text);
    }

     static Map<String,String> createLanguageToCodeLanguageMap(){
        String url = "https://learn.microsoft.com/en-us/azure/ai-services/translator/language-support";
        Map<String, String> languageMap = new HashMap<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements rows = doc.select("table tbody tr");
            for (Element row : rows) {
                Elements columns = row.select("td");
                if (columns.size() >= 2) {
                    String language = columns.get(0).text().toLowerCase();
                    String languageCode = columns.get(1).text();

                    // Add the language and language code to the HashMap
                    languageMap.put(language, languageCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return languageMap;
    }
}
