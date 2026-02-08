package com.core.ai.CoreAI.services;


import com.core.ai.CoreAI.agentPersona.AgentFilesHandler;
import com.core.ai.CoreAI.agentPersona.AgentPersona;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatClientService  {

    AgentPersona safetyagentPersona;
    AgentPersona ruleragentPersona;

    private final ChatClient safetyChatClient;
    private final ChatClient ruleChatClient;


    public ChatClientService(@Qualifier("ai/llama3.1") ChatClient safetyChatClient, @Qualifier("ai/llama3.1") ChatClient ruleChatClient, AgentFilesHandler agentFilesHandler, @Value("${safety.agent.persona.path}") String filepathS,@Value("${ruler.agent.persona.path}") String filepathR)  {
        this.safetyChatClient = safetyChatClient;
        this.ruleChatClient=ruleChatClient;
        safetyagentPersona=agentFilesHandler.readAgentPersona(filepathS);
        ruleragentPersona=agentFilesHandler.readAgentPersona(filepathR);


    }


    public Object callSafety( Class entity,String task) {
        List<Message> messages =safetyagentPersona.introductionMessages();
        messages.add(new UserMessage("Task: " + task));
        Prompt prompt= Prompt.builder().messages(messages).build();
        if(entity != null)
            return safetyChatClient.prompt(prompt).call().entity(entity);
        else
            return safetyChatClient.prompt(prompt).call().content();
    }



    public Object callRuler( Class entity,String task) {
        List<Message> messages =ruleragentPersona.introductionMessages();
        messages.add(new UserMessage("Task: " + task));
        Prompt prompt= Prompt.builder().messages(messages).build();
        if(entity != null)
            return ruleChatClient.prompt(prompt).call().entity(entity);
        else
            return ruleChatClient.prompt(prompt).call().content();
    }


}
