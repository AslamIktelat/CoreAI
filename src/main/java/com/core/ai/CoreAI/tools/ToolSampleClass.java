package com.core.ai.CoreAI.tools;

import org.springframework.stereotype.Component;

@Component("ToolSampleClass")
public class ToolSampleClass implements MCPTool
{

    public void execute() {
        System.out.println("This is a sample method in ToolSampleClass.");
    }

}

