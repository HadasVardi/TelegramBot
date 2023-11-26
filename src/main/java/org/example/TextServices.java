package org.example;

import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.*;

public class TextServices {

    public static Map<String,String> languageToCodeLanguageMap;
    public static boolean isvalidCommand(String text){
        return (text.startsWith("set language ") && !text.substring("set language ".length()).isEmpty());
    }

    public static boolean canTranslateToLanguage(String language) {
        return languageToCodeLanguageMap.containsKey((language));
    }

    public static String getLanguageFromCommand(String command) {
        return command.substring("set language ".length()).toLowerCase();
    }

    public static String getLanguageCode(String language) {
        return languageToCodeLanguageMap.get(language);
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


    /* //scraping - instead, init a map from language name to iso language code
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
     */


    // This function performs a GET request.
    public static void initLanuageNameToISOCodeMap() {
        String key=System.getenv("AZURE_KEY");
        String endpoint=System.getenv("AZURE_ENDPOINT");
        String apiUrl= endpoint+"/languages?api-version=3.0&scope=translation";

        // Create OkHttpClient
        OkHttpClient client = new OkHttpClient();

        // Build the request
        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("Content-Type", "application/json")
                .addHeader("Ocp-Apim-Subscription-Key", key)
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            extractHttpResponseTOMap(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractHttpResponseTOMap(String httpResponse) {
        languageToCodeLanguageMap= new HashMap<>();
        JSONObject jsonObject = new JSONObject(httpResponse);

        // Get the "translation" object
        JSONObject translationObject = jsonObject.getJSONObject("translation");

        // Create the map
        for (String key : translationObject.keySet()) {
            JSONObject innerObject = translationObject.getJSONObject(key);
            String name = innerObject.getString("name").toLowerCase();
            languageToCodeLanguageMap.put(name, key);
        }
    }

}
