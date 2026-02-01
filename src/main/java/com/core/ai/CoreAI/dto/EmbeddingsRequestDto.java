package com.core.ai.CoreAI.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmbeddingsRequestDto(
        @JsonProperty("model") String model,
        @JsonProperty("input") List<String> input,
        @JsonProperty("keep_alive") String keepAlive,
        @JsonProperty("options") Map<String, Object> options,
        @JsonProperty("truncate") Boolean truncate) {

    /**
     * Shortcut constructor to create a EmbeddingRequest without options.
     * @param model The name of model to generate embeddings from.
     * @param input The text or list of text to generate embeddings for.
     */
    public EmbeddingsRequestDto(String model, String input) {
        this(model, List.of(input), null, null, null);

    }
}
