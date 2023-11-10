# Tell Me Chuck Norris Jokes
## Description

Telegram Bot that tells 101 different Chuck Norris jokes in any language set by the user using the command ‘set language’.


## Table of Contents

- [Dependencies](#dependencies)
- [Usage](#usage)
- [Commands](#commands)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Dependencies

This project is built using Maven, and the following dependencies are specified in the `pom.xml` file.
```xml
    <dependencies>
    <!-- Telegram Bots Dependency -->
    <dependency>
        <groupId>org.telegram</groupId>
        <artifactId>telegrambots</artifactId>
        <version>6.8.0</version>
    </dependency>

    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.14.1</version> 
    </dependency>

    <!-- Apache HttpClient -->
    <dependency>
        <groupId>org.apache.httpcomponents.client5</groupId>
        <artifactId>httpclient5-fluent</artifactId>
        <version>5.1.4</version>
    </dependency>

    <!-- Gson for Azure AI translator-->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.8</version>
    </dependency>

    <!-- OkHttp for Azure AI translator-->
    <dependency>
        <groupId>com.squareup.okhttp3</groupId>
        <artifactId>okhttp</artifactId>
        <version>4.9.1</version>
    </dependency>

    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20210307</version> 
    </dependency>


</dependencies>
```

## Usage

- Connect to the bot by open the Telegram app on your mobile device or use the desktop version.
- In the Telegram app, use the search bar at the top to search for the bot's username, which is called 'TellChuckNorrisJokesBot'.
- Once you have found the bot in the search results, click on its username.
  This will open the bot's profile.
- Click the "Start" button at the bottom of the screen to initiate a conversation with the bot.
- Set a language by using the following text in the bot chat 'set language [language]'. 
- Choose a joke number by typing a number in range 1-101.
- You can change the language at any point by entering the instruction 'set language [language].'

## Commands

- `/description`: get bot description 

## Configuration

1. Create a new bot on Telegram and get the API token.
2. Copy the token to the configuration file (`config.properties`).
3. Create Azure AI Translator resource.
4. Copy the Azure tokens (key, endpoint and location) to the configuration file (`config.properties`).


## Contributing

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/awesome-feature`.
3. Make your changes and commit them: `git commit -m 'Add awesome feature'`.
4. Push to the branch: `git push origin feature/awesome-feature`.
5. Submit a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details.


