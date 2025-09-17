package com.hackernews.hackernewsClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackernews.pojo.CommentPojo;
import com.hackernews.pojo.StoryPojo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;


public class HackerNewsAPIClient {
    private final String baseUrl = "https://hacker-news.firebaseio.com/v0";
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    private String get(String endpoint) throws IOException {
        Request request = new Request.Builder().url(baseUrl + endpoint).build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code: " + response.code());
            return response.body().string();
        }
    }

    public int[] getTopStories() throws IOException {
        return mapper.readValue(get("/topstories.json"), int[].class);
    }

    public StoryPojo getStory(int storyId) throws IOException {
        return mapper.readValue(get("/item/" + storyId + ".json"), StoryPojo.class);
    }

    public CommentPojo getComment(int commentId) throws IOException {
        return mapper.readValue(get("/item/" + commentId + ".json"), CommentPojo.class);
    }
}
