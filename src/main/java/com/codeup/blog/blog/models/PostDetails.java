package com.codeup.blog.blog.models;

import com.codeup.blog.blog.models.Post;

import javax.persistence.*;

@Entity
public class PostDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private boolean isAwesome;

    @Column(columnDefinition = "TEXT")
    private String historyOfPost;

    @Column(nullable = false)
    private String topicDescription;

    @OneToOne(mappedBy = "postDetails")
    private Post post;

    public PostDetails() {
    }

    public PostDetails(boolean isAwesome, String historyOfPost, String topicDescription) {
        this.isAwesome = isAwesome;
        this.historyOfPost = historyOfPost;
        this.topicDescription = topicDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAwesome() {
        return isAwesome;
    }

    public void setAwesome(boolean awesome) {
        isAwesome = awesome;
    }

    public String getHistoryOfPost() {
        return historyOfPost;
    }

    public void setHistoryOfPost(String historyOfPost) {
        this.historyOfPost = historyOfPost;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }
}
