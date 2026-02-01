package com.core.ai.CoreAI.tools;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ToolManager {

    @Autowired
    private ApplicationContext applicationContext;

    public MCPTool getToolByBean(String beanName) {
        try {
            return (MCPTool) applicationContext.getBean(beanName);
        }
        catch (BeansException e) {
            throw new RuntimeException("Tool with bean name " + beanName + " not found.", e);
        }
    }
    public ArrayList<MCPTool> getTools() {
        ArrayList<MCPTool> tools = new ArrayList<>();
        String[] toolBeanNames = applicationContext.getBeanNamesForType(MCPTool.class);
        for (String beanName : toolBeanNames) {
            tools.add((MCPTool) applicationContext.getBean(beanName));
        }
        return tools;
    }

}
