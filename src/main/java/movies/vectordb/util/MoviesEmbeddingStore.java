package movies.vectordb.util;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;

import java.net.URISyntaxException;

public interface MoviesEmbeddingStore
{
    EmbeddingStore<TextSegment> getEmbeddingStore() throws URISyntaxException;
}
