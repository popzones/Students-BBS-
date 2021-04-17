package com.saybiu.domain;

import java.io.Serializable;

public class ReplyPost implements Serializable {
    private Integer replyId;
    private Integer replyPostId;
    private Integer replyUserId;
    private String replyPostTitle;
    private Integer postWriterId;
    private String replyUserNickname;
    private String replyPostContent;
    private String replyPostUrl;

    public ReplyPost() {
    }

    public ReplyPost(Integer replyId, Integer replyPostId, Integer replyUserId, String replyPostTitle, Integer postWriterId, String replyUserNickname, String replyPostContent, String replyPostUrl) {
        this.replyId = replyId;
        this.replyPostId = replyPostId;
        this.replyUserId = replyUserId;
        this.replyPostTitle = replyPostTitle;
        this.postWriterId = postWriterId;
        this.replyUserNickname = replyUserNickname;
        this.replyPostContent = replyPostContent;
        this.replyPostUrl = replyPostUrl;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getReplyPostId() {
        return replyPostId;
    }

    public void setReplyPostId(Integer replyPostId) {
        this.replyPostId = replyPostId;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyPostTitle() {
        return replyPostTitle;
    }

    public void setReplyPostTitle(String replyPostTitle) {
        this.replyPostTitle = replyPostTitle;
    }

    public Integer getPostWriterId() {
        return postWriterId;
    }

    public void setPostWriterId(Integer postWriterId) {
        this.postWriterId = postWriterId;
    }

    public String getReplyUserNickname() {
        return replyUserNickname;
    }

    public void setReplyUserNickname(String replyUserNickname) {
        this.replyUserNickname = replyUserNickname;
    }

    public String getReplyPostContent() {
        return replyPostContent;
    }

    public void setReplyPostContent(String replyPostContent) {
        this.replyPostContent = replyPostContent;
    }

    public String getReplyPostUrl() {
        return replyPostUrl;
    }

    public void setReplyPostUrl(String replyPostUrl) {
        this.replyPostUrl = replyPostUrl;
    }

    @Override
    public String toString() {
        return "ReplyPost{" +
                "replyId=" + replyId +
                ", replyPostId=" + replyPostId +
                ", replyUserId=" + replyUserId +
                ", replyPostTitle='" + replyPostTitle + '\'' +
                ", postWriterId=" + postWriterId +
                ", replyUserNickname='" + replyUserNickname + '\'' +
                ", replyPostContent='" + replyPostContent + '\'' +
                ", replyPostUrl='" + replyPostUrl + '\'' +
                '}';
    }
}
