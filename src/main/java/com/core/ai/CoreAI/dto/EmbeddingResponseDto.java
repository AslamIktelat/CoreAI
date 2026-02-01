package com.core.ai.CoreAI.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddingResponseDto {

    private String model;
    private String object;
    private Usage usage;
    private List<EmbeddingData> data;

    // getters and setters

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public List<EmbeddingData> getData() {
        return data;
    }

    public void setData(List<EmbeddingData> data) {
        this.data = data;
    }

    // ---- Nested DTOs ----

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Usage {

        @JsonProperty("prompt_tokens")
        private int promptTokens;

        @JsonProperty("total_tokens")
        private int totalTokens;

        public int getPromptTokens() {
            return promptTokens;
        }

        public void setPromptTokens(int promptTokens) {
            this.promptTokens = promptTokens;
        }

        public int getTotalTokens() {
            return totalTokens;
        }

        public void setTotalTokens(int totalTokens) {
            this.totalTokens = totalTokens;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EmbeddingData {

        private float[] embedding;
        private int index;
        private String object;

        public float[] getEmbedding() {
            return embedding;
        }

        public void setEmbedding(float[] embedding) {
            this.embedding = embedding;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }
    }
}

