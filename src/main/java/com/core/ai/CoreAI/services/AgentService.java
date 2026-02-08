package com.core.ai.CoreAI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AgentService {
    @Autowired
    ChatClientService chatClientService;


    // Bot calls it
    public Object executeSafety(String task)
    {
       return chatClientService.callSafety(null,task);
    }
    public Object executeSafety(String task,Class entity)
    {
        return chatClientService.callSafety(entity,task);
    }

    public Object executeRuler(String task)
    {
        return chatClientService.callRuler(null,task);
    }
    public Object executeRuler(String task,Class entity)
    {
        return chatClientService.callRuler(entity,task);
    }
}
