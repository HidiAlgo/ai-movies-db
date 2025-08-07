package movies.vectordb.imp;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import io.qdrant.client.QdrantGrpcClient;
import movies.vectordb.util.MoviesEmbeddingStore;

import java.net.URI;
import java.net.URISyntaxException;

public class QdrantEmbeddingStore implements MoviesEmbeddingStore
{
    public static  String QDRANT_URL = "http://localhost:6333";
    @Override
    public EmbeddingStore<TextSegment> getEmbeddingStore() throws URISyntaxException {
        String qdrantHostName = new URI( QDRANT_URL ).getHost();
        int qdrantPort = new URI( QDRANT_URL ).getPort();

        return null;
    }
}
