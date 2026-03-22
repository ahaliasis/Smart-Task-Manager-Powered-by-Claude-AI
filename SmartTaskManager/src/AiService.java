import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

import java.util.ArrayList;

public class AiService {

    private final AnthropicClient client;

    public AiService() {
        client = AnthropicOkHttpClient.fromEnv();
    }

    public String organizeTasks(ArrayList<Task> tasks) {

        if (tasks.isEmpty()) {
            return "No tasks to organize.";
        }

        // Build a text summary of all tasks to send to Claude
        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append("Task #").append(i).append("\n");
            taskList.append(tasks.get(i).toString()).append("\n");
        }

        String prompt = "You are a task management assistant. " +
                "Here are the user's tasks:\n\n" +
                taskList +
                "\nPlease organize these tasks by priority and deadline, " +
                "and give a short suggestion for which task to tackle first and why.";

        MessageCreateParams params = MessageCreateParams.builder()
                .maxTokens(1024L)
                .addUserMessage(prompt)
                .model(Model.CLAUDE_HAIKU_4_5)
                .build();

        var message = client.messages().create(params);

        // Extract the text response
        return message.content().stream()
                .filter(block -> block.isText())
                .map(block -> block.asText().text())
                .findFirst()
                .orElse("No response from AI.");
    }
}
