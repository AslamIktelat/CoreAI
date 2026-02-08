package com.core.ai.CoreAI.configurations;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientsConfig {


    @Bean
    @Qualifier("ai/llama3.1")
    public ChatClient llamaClient(ChatModel model) {
        return ChatClient.builder(model)
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("ai/llama3.1")
                        //.temperature()
                        .build())
                .build();
    }


    @Bean
    @Qualifier("ai/qwen2.5:3B-Q4_K_M")
    public ChatClient safetyChatClient(ChatModel model) {
        return ChatClient.builder(model)
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("ai/qwen2.5:3B-Q4_K_M")
                        .build())
                .build();
    }


//    @Bean
//    public ChatClient clientConfig(ChatClient.Builder builder, SyncMcpToolCallbackProvider mcpTools) {
//
//        for (int i = 0; i < mcpTools.getToolCallbacks().length; i++) {
//            System.out.println("----------------------------------");
//            System.out.println("MCP Tool Index: " + i);
//            System.out.println("name " + mcpTools.getToolCallbacks()[i].getToolDefinition().name());
//            System.out.println("Description " + mcpTools.getToolCallbacks()[i].getToolDefinition().description());
//            System.out.println("Schema" + mcpTools.getToolCallbacks()[i].getToolDefinition().inputSchema());
//            System.out.println("----------------------------------");
//        }
//
//
//        return builder
//                .defaultToolCallbacks(mcpTools.getToolCallbacks()) // expose Docker MCP tools
//                .build();
//    }
}
