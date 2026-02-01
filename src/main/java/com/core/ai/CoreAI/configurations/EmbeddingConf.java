package com.core.ai.CoreAI.configurations;


import com.core.ai.CoreAI.embeddingModel.EmbeddingModellocally;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class EmbeddingConf {

    @Value("${embedding.model.locally.base-url}")
    private String baseURL;
    @Value("${embedding.model.locally.uri:/engines/llama.cpp/v1/embeddings}")
    private String uri;
    @Value("${embedding.model.locally.model}")
    private String model;
    @Bean
    @Primary
    public EmbeddingModel createEmbeddingModel()
    {
        return new EmbeddingModellocally(baseURL,uri,model);

    }

}
