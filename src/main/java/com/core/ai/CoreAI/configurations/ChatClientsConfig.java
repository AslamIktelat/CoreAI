package com.core.ai.CoreAI.configurations;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientsConfig {

    @Bean
    public ChatClient clientConfig(ChatClient.Builder builder, SyncMcpToolCallbackProvider mcpTools) {

        for (int i = 0; i < mcpTools.getToolCallbacks().length; i++) {
            System.out.println("----------------------------------");
            System.out.println("MCP Tool Index: " + i);
            System.out.println("name " + mcpTools.getToolCallbacks()[i].getToolDefinition().name());
            System.out.println("Description " + mcpTools.getToolCallbacks()[i].getToolDefinition().description());
            System.out.println("Schema" + mcpTools.getToolCallbacks()[i].getToolDefinition().inputSchema());
            System.out.println("----------------------------------");
        }


        return builder
                .defaultToolCallbacks(mcpTools.getToolCallbacks()) // expose Docker MCP tools
                .build();
    }
}
