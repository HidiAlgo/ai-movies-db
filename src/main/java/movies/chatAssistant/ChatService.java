package movies.chatAssistant;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.openai.internal.chat.AssistantMessage;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import movies.vectordb.imp.QdrantEmbeddingStoreLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService
{
    @Autowired
    QdrantEmbeddingStoreLocal qdrantEmbeddingStoreLocal;

    public ChatAssistant getAssistant( EmbeddingStore<TextSegment> embeddingStore, ChatModel chatModel )
    {
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        ContentRetriever contentRetriever = new EmbeddingStoreContentRetriever( embeddingStore, embeddingModel );

        return AiServices.builder( ChatAssistant.class )
                .chatModel( chatModel )
                .chatMemory(MessageWindowChatMemory.withMaxMessages( 20) )
                .contentRetriever( contentRetriever )
                .build();
    }
}
