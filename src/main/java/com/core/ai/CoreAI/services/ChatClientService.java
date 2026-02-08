package com.core.ai.CoreAI.services;


import com.core.ai.CoreAI.agentPersona.AgentFilesHandler;
import com.core.ai.CoreAI.agentPersona.AgentPersona;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.core.ai.CoreAI.constants.Constants.*;

@Service
public class ChatClientService  {
    AgentPersona agentPersona;
    private final ChatClient chatClient;

    public ChatClientService(ChatClient chatClient, AgentFilesHandler agentFilesHandler)  {
        this.chatClient = chatClient;
        try {
            agentPersona=agentFilesHandler.readAgentPersona(AGENT_PERSONA_FULL_PATH);
        }
        catch (IOException exception)
        {
            System.out.println(" ");
            agentPersona=new AgentPersona(DEFAULT_AGENT_PERSONA,DEFAULT_AGENT_PERSONA,null);
        }
    }


    public Object call( Class entity,String task) {
        List<Message> messages =agentPersona.introductionMessages();
        messages.add(new UserMessage("Task: " + task));
        Prompt prompt= Prompt.builder().messages(messages).build();
        if(entity != null)
            return chatClient.prompt(prompt).call().entity(entity);
        else
            return chatClient.prompt(prompt).toolCallbacks(agentPersona.getCallBackTools()).call().content();
    }


}
