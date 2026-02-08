package com.core.ai.CoreAI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    ChatClientService chatClientService;

    // User call this method to execute a task, the task will be passed to the chatClientService to be executed and the result will be returned to the user
    public Object execute(String task)
    {
       return chatClientService.call(null,task);
    }

}
