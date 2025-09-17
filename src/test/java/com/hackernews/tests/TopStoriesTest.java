package com.hackernews.tests;

import com.hackernews.pojo.CommentPojo;
import com.hackernews.pojo.StoryPojo;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TopStoriesTest extends BaseTest {

    @Test(description="Retrieve top stories")
    public void testRetrieveTopStories() throws Exception {
        int[] topStories = client.getTopStories();
        Assert.assertTrue(topStories.length > 0, "Top stories list is empty");
    }

    @Test(description="Retrieve top story details")
    public void testRetrieveCurrentTopStory() throws Exception {
        int[] topStories = client.getTopStories();
        StoryPojo story = client.getStory(topStories[0]);
        Assert.assertNotNull(story, "Story response should not be null");
        Assert.assertNotNull(story.getTitle(), "Top story title is null");
        Assert.assertEquals(story.getType(), "story", "Top story type mismatch");
    }

    @Test(description="Retrieve first comment of top story")
    public void testRetrieveTopStoryFirstComment() throws Exception {
        int[] topStories = client.getTopStories();
        StoryPojo story = client.getStory(topStories[0]);
        if(story.getKids() != null && !story.getKids().isEmpty()) {
            CommentPojo comment = client.getComment(story.getKids().get(0));
            Assert.assertNotNull(comment, "Comment response should not be null");
            Assert.assertEquals(comment.getType(), "comment", "Comment type mismatch");
        } else {
            System.out.println("No comments available for this story.");
        }
    }

    @Test(description="Invalid story ID handling")
    public void testEdgeCaseInvalidStoryId() throws Exception {
         StoryPojo story = client.getStory(-12345); // invalid id
         Assert.assertNull(story, "Story should be null for an invalid ID");
    }


    @Test(description="Find bug if response status code does not match")
    public void testEdgeCaseBugTest() {
        try {
            client.getStory(999999);
            Assert.fail("Request with invalid ID should fail with non-200 status code");
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("Unexpected response code"),
                    "Error message should indicate invalid response code");
        }
    }

    }
