package com.core.ai.CoreAI.configurations;

import com.core.ai.CoreAI.services.AgentService;
import com.core.ai.CoreAI.tools.ToolManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class ServicesConfig {
    @Bean
    public ToolManager toolManager()
    {
        return new ToolManager();
    }
    @Bean
    public AgentService agentService()
    {
        return new AgentService();
    }

}
