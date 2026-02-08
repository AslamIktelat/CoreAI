package com.core.ai.CoreAI.configurations;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
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

}
