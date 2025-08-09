package movies.chatAssistant;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.time.Duration;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

public class ChatLanguageModel
{

    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

    // returns GPT_4_0_MINI
    public static ChatModel getOpenAiChatModelGPT_4_0_MINI()
    {
        // Chat Language Model ( good for conversation like text based generations )
        return OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1") // using a free version of the openai just for learning purpose
                .apiKey( OPENAI_API_KEY ) // need to give the api key to access the models
                .modelName( GPT_4_O_MINI )  // open ai model name
                .temperature( 0.7 ) // this defines how random the response is, 2 is maximum 0 is low
                .timeout( Duration.ofSeconds(60) ) // time it waits until a response come
                .logRequests( false ) // any logs defined in requests
                .logResponses( false ) // any logs defined in response
                .build();
    }
}
