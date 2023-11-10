package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class ChuckNorrisJokeScraper {

    public static final int NUMBEROFJOKES=101;

    public static String[] EnglishJokes= new String[NUMBEROFJOKES];

    public static String getEnglishJoke(int index) {
        return EnglishJokes[index-1];
    }

     static void initChuckNorrisJoke() {
        String url = "https://parade.com/968666/parade/chuck-norris-jokes/"; // target URL
        try {
            Document document = Jsoup.connect(url)
                    .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/115.0")
                    .header("Accept", "*/*")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Connection", "keep-alive")
                    .header("Referer", "https://www.google.com/")
                    .timeout(5000)
                    .get();

            // Select the div with class "m-detail--body"
            Element bodyElement = document.selectFirst(".m-detail--body");
            // Find the ordered list (ol) containing the jokes
            Element olElement = bodyElement.selectFirst("ol");
            // Select all list items (li) within the ordered list
            Elements jokeElements = olElement.select("li");
            // Print each Chuck Norris joke
            for (int i = 0; i < jokeElements.size(); i++) {
                Element jokeElement = jokeElements.get(i);
                String jokeText = jokeElement.text();
                EnglishJokes[i]=jokeText;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}