package com.core.ai.CoreAI.agentPersona;

import com.core.ai.CoreAI.tools.MCPTool;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import java.util.ArrayList;
import java.util.List;



public class AgentPersona {

    private String name;
    private String roleDefinition;
    private ArrayList<MCPTool> toolsList;

    public AgentPersona(String name, String roleDefinition,ArrayList<MCPTool> toolsList) {
        this.name = name;
        this.roleDefinition = roleDefinition;
        this.toolsList=toolsList;
    }



    public List<Message> introductionMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(SystemMessage.builder().text("You are " + this.name+", Your role definition is : " + this.roleDefinition).build());
        return messages;
    }
    public ArrayList<MCPTool> getMCPTools() {
        if(this.toolsList == null)
            this.toolsList= new ArrayList<>();
       return this.toolsList;
    }

    public ToolCallback[] getCallBackTools()
    {

       return ToolCallbacks.from(toolsList.toArray());
    }
}

