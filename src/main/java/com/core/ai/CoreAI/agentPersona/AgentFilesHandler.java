package com.core.ai.CoreAI.agentPersona;

import com.core.ai.CoreAI.tools.MCPTool;
import com.core.ai.CoreAI.tools.ToolManager;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

import static com.core.ai.CoreAI.constants.Constants.DEFAULT_AGENT_PERSONA;

@Service
public class AgentFilesHandler {
    private final ObjectMapper objectMapper;
    private final ToolManager toolManager;

    public AgentFilesHandler(ToolManager toolManager) {
        this.objectMapper = new ObjectMapper();
        this.toolManager = toolManager;
    }
    public AgentPersona readAgentPersona(String filePath)  {
        try {
            AgentPersonaV1 agentPersonaV1 = objectMapper.readValue(new java.io.File(filePath), AgentPersonaV1.class);
            return convertV1(agentPersonaV1);
        }
        catch (IOException exception)
        {
            return new AgentPersona(DEFAULT_AGENT_PERSONA,DEFAULT_AGENT_PERSONA,null);
        }
    }

    private AgentPersona convertV1(AgentPersonaV1 agentPersonaV1) {
        return new AgentPersona(agentPersonaV1.name,agentPersonaV1.roleDefinition,fillToolList(agentPersonaV1.toolsList));
    }

    private ArrayList<MCPTool>  fillToolList(ArrayList<String> toolsNamesList) {
        ArrayList<MCPTool> toolsList=new ArrayList<>();

        for(String toolBeanName:toolsNamesList) {
            toolsList.add(toolManager.getToolByBean(toolBeanName));
        }
        return toolsList;
    }

    private static class AgentPersonaV1{
        @JsonProperty("name")

        private final String name;
        @JsonProperty("roleDefinition")

        private final String roleDefinition;
        @JsonProperty("toolsList")

        private final ArrayList<String> toolsList;
        public AgentPersonaV1(String name, String roleDefinition,ArrayList<String> toolsList) {
            this.name = name;
            this.roleDefinition = roleDefinition;
            this.toolsList=toolsList;
        }
        public AgentPersonaV1()
        {
            this.name = null;
            this.roleDefinition = null;
            this.toolsList=null;
        }
    }
}
