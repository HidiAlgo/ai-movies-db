package movies.client;


import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import movies.chatAssistant.ChatAssistant;
import movies.chatAssistant.ChatLanguageModel;
import movies.chatAssistant.ChatService;
import movies.domain.AiRequest;
import movies.domain.AiResponse;
import movies.vectordb.imp.QdrantEmbeddingStoreLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AiClient
{
    @Autowired
    ChatService chatService;

    @PostMapping("/chat")
    public AiResponse chat( @RequestBody AiRequest aiRequest )
    {
        AiResponse aiResponse = null;
        try
        {
            EmbeddingStore<TextSegment> embeddingStore = QdrantEmbeddingStoreLocal.getEmbeddingStore();
            ChatAssistant chatAssistant = chatService.getAssistant( embeddingStore, ChatLanguageModel.getOpenAiChatModelGPT_4_0_MINI() );

            String response = chatAssistant.chat( aiRequest.question() );
            aiResponse = new AiResponse( response );
        }
        catch ( Exception exception )
        {

        }
        return aiResponse;
    }
}
