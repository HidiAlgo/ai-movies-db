package movies.client;

import movies.vectordb.imp.QdrantEmbeddingStoreLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/embedding-store")
public class EmbeddingStoreClient
{
    @Autowired
    QdrantEmbeddingStoreLocal qdrantEmbeddingStoreLocal;

    @GetMapping("/load")
    public String loadStore()
    {
        return qdrantEmbeddingStoreLocal.loadEmbeddingStore();
    }
}
