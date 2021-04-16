package com.saybiu.domain;

import java.io.Serializable;

public class Comment implements Serializable {
    private Integer replyCommentId;
    private Integer replyId;
    private Integer replyCommentUserId;
    private String replyCommentUserNickname;
    private Integer replyUserId;
    private String replyUserNickname;
    private String replyCommentContent;

    public Integer getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(Integer replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getReplyCommentUserId() {
        return replyCommentUserId;
    }

    public void setReplyCommentUserId(Integer replyCommentUserId) {
        this.replyCommentUserId = replyCommentUserId;
    }

    public String getReplyCommentUserNickname() {
        return replyCommentUserNickname;
    }

    public void setReplyCommentUserNickname(String replyCommentUserNickname) {
        this.replyCommentUserNickname = replyCommentUserNickname;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserNickname() {
        return replyUserNickname;
    }

    public void setReplyUserNickname(String replyUserNickname) {
        this.replyUserNickname = replyUserNickname;
    }

    public String getReplyCommentContent() {
        return replyCommentContent;
    }

    public void setReplyCommentContent(String replyCommentContent) {
        this.replyCommentContent = replyCommentContent;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "replyCommentId=" + replyCommentId +
                ", replyId=" + replyId +
                ", replyCommentUserId=" + replyCommentUserId +
                ", replyCommentUserNickname='" + replyCommentUserNickname + '\'' +
                ", replyUserId=" + replyUserId +
                ", replyUserNickname='" + replyUserNickname + '\'' +
                ", replyCommentContent='" + replyCommentContent + '\'' +
                '}';
    }
}
