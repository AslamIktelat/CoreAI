package com.core.ai.CoreAI;

import com.core.ai.CoreAI.services.ChatClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class CoreAiApplication implements CommandLineRunner {
    @Autowired
    ChatClientService chatClientService;
	public static void main(String[] args) {
		SpringApplication.run(CoreAiApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started...");
       // String res= chatClientService.call(null,"download 10 random face image and describe it based on any actual analysis of the images using the available tools do not return urls you need to download the imgs DON'T USE https://example.com/ it's not a real url website").toString();
//        String res= chatClientService.call(null," Testing connection response with only the llm name").toString();
//
//       System.out.println(res);
        String res= chatClientService.callRuler(null," Testing connection response with only the llm name").toString();
        System.out.println(res);
        System.out.println("Application ended...");



    }


}
