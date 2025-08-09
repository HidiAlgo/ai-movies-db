package movies.chatAssistant;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface ChatAssistant
{
    @SystemMessage(
        """
        You are an assistant for providing information of movies. You should be able to provide recommendation about movies and also query
        internal database which will be provided as a tool for reviews.
        """
    )
    @UserMessage("Here is the user's question: {{question}}")
    String chat(@V("question") String question);
}
