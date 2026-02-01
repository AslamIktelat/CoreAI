package com.core.ai.CoreAI.embeddingModel;

import com.core.ai.CoreAI.dto.EmbeddingResponseDto;
import com.core.ai.CoreAI.dto.EmbeddingsRequestDto;
import org.springframework.ai.chat.metadata.DefaultUsage;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;


public class EmbeddingModellocally extends AbstractEmbeddingModel {

    private final RestClient restClient;
    private final String uri;
    private final String model;
    public EmbeddingModellocally (String baseUrl, String uri, String model)
    {
        this.uri = uri;
        this.model = model;

        Consumer<HttpHeaders> defaultHeaders = headers -> {
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        };
        RestClient.Builder restClientBuilder = RestClient.create().mutate();
        this.restClient = restClientBuilder
                .clone()
                .baseUrl(baseUrl)
                .defaultHeaders(defaultHeaders)
                .build();
    }

    @Override
    public EmbeddingResponse call(EmbeddingRequest request) {
        EmbeddingsRequestDto embeddingsRequest=new EmbeddingsRequestDto(model,request.getInstructions().toString());
        EmbeddingResponseDto response=embedlocally(embeddingsRequest);
        AtomicInteger indexCounter = new AtomicInteger(0);
        List<Embedding> embeddings = response.getData()
                .stream()
                .map(e -> new Embedding(e.getEmbedding(), indexCounter.getAndIncrement()))
                .toList();
        EmbeddingResponseMetadata embeddingResponseMetadata = new EmbeddingResponseMetadata(response.getModel(),
                getDefaultUsage(response));
        EmbeddingResponse embeddingResponse = new EmbeddingResponse(embeddings, embeddingResponseMetadata);
        return embeddingResponse;
    }

    private DefaultUsage getDefaultUsage(EmbeddingResponseDto response) {
        return new DefaultUsage(Optional.of(response.getUsage().getPromptTokens()).orElse(0), 0);
    }
    @Override
    public float[] embed(Document document) {
        assert document.getText() != null;
        return embed(document.getText());
    }



    public EmbeddingResponseDto embedlocally(EmbeddingsRequestDto embeddingsRequest) {
        Assert.notNull(embeddingsRequest, "");

        return this.restClient.post()
                .uri(uri)
                .body(embeddingsRequest)
                .retrieve()
                .body(EmbeddingResponseDto.class);
    }



}
