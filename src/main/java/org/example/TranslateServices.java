package org.example;

import java.io.IOException;
import java.util.Properties;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class TranslateServices {
    private Properties config;
    private OkHttpClient client;
    public TranslateServices() {
        client = new OkHttpClient();
    }

    public String translateText(String textToTranslate, String targetLanguage) {
        String response = this.Post(textToTranslate, targetLanguage);
        return extractTranslatedText(response);
    }

    // This function performs a POST request.
    private String Post(String text, String targetLanguage) {

      //  String key=config.getProperty("AZURE_KEY");
      //  String endpoint=config.getProperty("AZURE_ENDPOINT");
      //  String location=config.getProperty("AZURE_LOCATION");

        String key=System.getenv("AZURE_KEY");
        String endpoint=System.getenv("AZURE_ENDPOINT");
        String location=System.getenv("AZURE_LOCATION");

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "[{\"Text\": \""+text+"\"}]");
        Request request = new Request.Builder()
                .url(endpoint+"/translate?api-version=3.0&from=en&to="+targetLanguage)
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                // location required for using a multi-service or regional (not global) resource.
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return "An unexpected error occurred. Please try again.";
        }
    }

    // This function extracts the translated text from httpResponse
     private static String extractTranslatedText(String httpResponse) {
         JSONArray jsonArray = new JSONArray(httpResponse);  // Parse the JSON string into a JSONArray
         JSONObject firstObject = jsonArray.getJSONObject(0);  // Get the first object in the array
         JSONArray translationsArray = firstObject.getJSONArray("translations");  // Get the "translations" array from the first object
         JSONObject translationObject = translationsArray.getJSONObject(0);  // Get the first object in the "translations" array
         return translationObject.getString("text");  // return the "text" value
    }
}
