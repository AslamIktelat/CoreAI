package com.core.ai.CoreAI.services;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VectorStoreService {
    @Autowired
    VectorStore vectorStore;

    public void add(List<Document> documents)
    {
        vectorStore.add(documents);
    }
    public List<Document> similaritySearch(String query)
    {
        return vectorStore.similaritySearch(SearchRequest.builder().query(query).build());
    }
}
