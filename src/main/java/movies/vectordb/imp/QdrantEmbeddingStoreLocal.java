package movies.vectordb.imp;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections;
import movies.domain.Movie;
import movies.service.LogicService;
import movies.vectordb.util.MoviesEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QdrantEmbeddingStoreLocal implements MoviesEmbeddingStore
{
    @Autowired
    LogicService logicService;

    public static  String QDRANT_URL = "http://localhost:6334";
    private static final String INDEX_NAME = "MoviesIndex";

    private static final EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
    private static QdrantClient qdrantClient;
    private static QdrantEmbeddingStore qdrantEmbeddingStore;

    public String loadEmbeddingStore()
    {
        String status = "loading failed";
        try
        {
            EmbeddingStore<TextSegment> embeddingStore = getEmbeddingStore();

            List<Movie> movies = logicService.getAllMovies();

            List<TextSegment> textSegments = new ArrayList<>();

            movies.forEach( movie -> {
                TextSegment segment = TextSegment.from( movie.toString() );
                textSegments.add( segment );
            });

            // Convert segments into embeddings
            List<Embedding> embeddings = embeddingModel.embedAll(textSegments).content();

            // Store embeddings into Qdrant
            embeddingStore.addAll( embeddings, textSegments );

            status = "Successfully loaded";

        }
        catch ( Exception e )
        {
            status += " with exception " + e.getMessage();
        }
        return status;
    }

    public static EmbeddingStore<TextSegment> getEmbeddingStore() throws URISyntaxException {

        if ( qdrantEmbeddingStore != null )
        {
            return qdrantEmbeddingStore;
        }
        String qdrantHostName = new URI( QDRANT_URL ).getHost();
        int qdrantPort = new URI( QDRANT_URL ).getPort();

        QdrantGrpcClient.Builder grpcClientBuilder = QdrantGrpcClient.newBuilder(qdrantHostName, qdrantPort, false);
        qdrantClient = new QdrantClient(grpcClientBuilder.build());

        try
        {
            qdrantClient.createCollectionAsync(
                    INDEX_NAME,
                    Collections.VectorParams.newBuilder()
                            .setSize(384)
                            .setDistance(Collections.Distance.Cosine)
                            .build()).get();
        }
        catch (Exception e)
        {

        }
        qdrantEmbeddingStore = QdrantEmbeddingStore.builder()
                .client(qdrantClient)
                .collectionName(INDEX_NAME)
                .build();

        return qdrantEmbeddingStore;
    }

}
