package com.core.ai.CoreAI.configurations;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientsConfig {

    @Bean
    public ChatClient clientConfig(ChatClient.Builder builder, SyncMcpToolCallbackProvider mcpTools) {
        return builder
                .defaultToolCallbacks(mcpTools.getToolCallbacks()) // expose Docker MCP tools
                .build();
    }
}
