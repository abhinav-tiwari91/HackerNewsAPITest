package com.hackernews.tests;
import com.hackernews.hackernewsClient.HackerNewsAPIClient;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected HackerNewsAPIClient client;

    @BeforeClass
    public void setup() {
        client = new HackerNewsAPIClient();
    }
}

