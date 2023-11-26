# Tell Me Chuck Norris Jokes
## Description

Telegram Bot that tells 101 different Chuck Norris jokes in any language set by the user using the command ‘set language’.


## Table of Contents

- [Dependencies](#dependencies)
- [Prerequisites](#prerequisites)
- [Setting Up Azure AI Service and Obtaining Bot Token](#setting-up-azure-bot-service-and-obtaining-bot-token)
- [Getting Started](#getting-started)
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

    <dependency>
        <groupId>com.azure</groupId>
        <artifactId>azure-ai-textanalytics</artifactId>
        <version>5.2.0</version>
    </dependency>
    
</dependencies>
```

## Prerequisites

Make sure you have the following prerequisite installed before running the code on your local machine:

- [Java Runtime Environment (JRE)](https://www.oracle.com/java/technologies/downloads/#java8) (Version 8 or higher): Your code is compiled and intended to run with the Java Runtime Environment. Please install the JRE before attempting to execute the program.

## Setting Up Azure AI Service and Obtaining Bot Token

This project utilizes the Azure Translator API for translating jokes. Follow these steps to set up and integrate the Azure Translator Text service:

1. Create an Azure Account: <br/>
If you don't have an Azure account, you'll need to [create an Azure account](https://azure.microsoft.com/en-us/free/) first. Follow the instructions on the Azure website to sign up.

2. Access the Azure Portal:<br/>
Once you have an Azure account, log in to the [Azure Portal](https://login.live.com/oauth20_authorize.srf?scope=openid+profile+email+offline_access&response_type=code&client_id=51483342-085c-4d86-bf88-cf50c7252078&response_mode=form_post&redirect_uri=https%3a%2f%2flogin.microsoftonline.com%2fcommon%2ffederation%2foauth2msa&state=rQQIARAAjZRLiNtWFIatOJnO5NUh9BEKLbMIpQ9kX-lKsjQQqGzJL_nKL_nZhdHLlmw9PJJsS-6udBG6aXaFtKtCoB1oGboqgUAChcJsGkpX7aZkFVICJauUFFpPSvZZ3MOF88P5L_d8_9ltJgNBhsiA99JkBuxf0SlKowALcahpAKc4ncA1KmfgXI4yaahrxhjq1S0aEBQEwaWzu9vkqxc_P3-U__q79C9P3wjv3cB2Ro69NDO67x5ib1tRNA_3s9m5H0Sqk1HXi-BZKxvaE8_2srZnmHH2Bwy7h2EPMOzwVMhAFjIUDekcyVKApFkqIwt6ggSUIEWMhsoAojYAm3tcU6wZWs-iQa81rQviGglVGwmOI7udtSwMgFxCESKbJLI3erKz2uhtpKBIVsRk4KLNEWO0RsRvp16u84vIIk-KH9hr8_GpnbEfuKO5H0Y30rfP1OemVzEKvueZepQ5kZleZOtqZPteI_DnZhDZZniVlaoGqdeWVZpemiXDEqiWVhOjchhYqi01m-0VKZFuaXEwlqUSI-FgpakHeCsaCq2-WhbjnJeH3XWvxDgys5CqB5UpD1twFYz5ldEQzDlegErLAI4yWcy5UWQMyJ4Ryt1qHHUGbMtbOpLk1MlcCKsk7CplT50tvII9LPQqhlTCLVNNEOgh0iPWMuL6Aq5wyZQUQUmqI4Gb1fTpotGZUEpPHVV7irx5VpMrNPoKjvdyFg9gpx1UiBGeyOZ41qKnBbSedFjBmaIoNvscv2hKpbBQt9xILuWYOLGSQmhJk0KRn_pdwldYl8uzgufwtpKDQpVNJCfQzbIJRy6UQWEV9ic00VWouB2sl0PP6hYabL45qpRIX88v9da4Qsq6PCOKEt5jdBSNOS5gZkhM9KlerzZC23cncZvoqvlBvVvqJjOzPRSEsgOdTr1eO0AxXQm7y5IdDYurxgpnbVau9Lmo1pvUndqa6eCLZdNWbFptiSLy5Nj10ZTru6ZlyNNA77PuoE37Uzy2imLPN7p8WV1o-Wmc5wN5HeXI2tpjWV2YW7LSWQpFRdGEKe6XZ2JJykn9YlGStGHb5m3UZw60_mzUxXV5FBMJmAmka9BwVZkW85P5QY0ehHAxLJZafAJKA47lamx1rM2BisgwgSgmHKUi8FGrrkteOJImlJXUZwtacnL6sBGPuVmf7opUFE66Lq1QNf4wfeU5hq7qqRPT3SzvBsINiasNf_4qzHhmlP0-_aalGmq4JODEVW0n43uurQd-6I9P1O6t9BWD4TRWYwBOjw0DpyCTw1V1DPCcqjEMNzY3P0Iep2svMi27CM1gZLsbdELfewbS3mbhPNvYM0-m780Df2w75v30peeuPvjf1sbKvdPYw9OvbW_tvnUZ20u98wpI729vn91NXU7tpZ6cxr46swmmXz-9rd_96Of6Zz91Hnzzz43U8ZlseS40mtDqA09cVA2TWoahAt6fr6ZCG3VaoiyaVB3ALE_z4CrcJ65vYde3to63dirCSBYVKkf-tYVdewm7tfOCsXb_3MbH60dfIvfDXf6w9-e3f1z7BHt4bhcAACkAiCJHCDTJwsfntp9H5sfnL2ynLu3sBHd377z76Pfy0XnszoXUk4t__3jz3y9uPn1UPtpN_Qc1&username=hadasv13%40gmail.com&estsfed=1&uaid=0e1532080c9742b0a8ae03d2fb1a73d0&cobrandid=ed5d1924-9524-4e70-8f68-5ee5e35afbef&fci=c44b4083-3bb0-49c1-b47d-974e53cbdf3c&acr_values=urn%3amicrosoft%3apolicies%3amfa#).

3. Create Azure Translator Resource: <br/>
In the Azure Marketplace, search for "Translators" and follow the prompts to create a new instance.

4. Obtain the Azure Translator API Key, Endpoint and Location: <br/>
   Once your Translator service instance is created, obtain the API key, endpoint and location: <br/>
   In the Azure Portal, go to the configuration page of your Translator service instance.<br/>
   Look for the "Keys and Endpoint", and find the API key, endpoint and location associated with your service.


To Obtain Your Bot Token, you need to contact @BotFather, issuing the /newbot command and following the steps until you're given a new token. You can find a step-by-step guide [here](https://core.telegram.org/bots/features#creating-a-new-bot).

## Getting Started
To run the code from the command line using the provided JAR file, you need to set the environment variables in the same command line session. <br/>
Follow these steps to run the code using the provided JAR file:

Set Environment Variables: <br/>
- Open the command prompt (CMD) and navigate to the Project Directory where you downloaded the project. For example:
    ```bash
    cd path/to/your/project
    ```
- From the project directory, navigate to the directory where the JAR file is located:
    ```bash
    cd out/artifacts/TelegramBot_jar
    ```
- Set the obtained tokens as environment variables:

    ```xml
    # For Unix-based systems
    export AZURE_ENDPOINT=your_azure_endpoint
    export AZURE_KEY=your_azure_api_key
    export AZURE_LOCATION=your_azure_api_location
    export BOT_TOKEN=your_actual_token_value
    ```

    ```xml
    # For Windows
    set AZURE_ENDPOINT=your_azure_endpoint
    set AZURE_KEY=your_azure_api_key
    set AZURE_LOCATION=your_azure_api_location
    set BOT_TOKEN=your_actual_token_value
    ```


Execute the following command to run the JAR file: <br/>
```xml
java -jar TelegramBot.jar
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

This project uses a configuration file located at `src/main/resources/config.properties`. Below are the configurable parameters:

- `AZURE_KEY`: API key for Azure Translator service authentication (required).
- `AZURE_ENDPOINT`: The URL of the Azure Translator service (required).
- `AZURE_LOCATION`: The region where the Azure Translator service is stored.
- `BOT_TOKEN`: API key used for authentication with the Telegram Bot API (required).

## Contributing

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/awesome-feature`.
3. Make your changes and commit them: `git commit -m 'Add awesome feature'`.
4. Push to the branch: `git push origin feature/awesome-feature`.
5. Submit a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details.


