package com.core.ai.CoreAI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    ChatClientService chatClientService;

    // Bot calls it
    public Object execute(String task)
    {
       return chatClientService.call(null,task);
    }

}
