package group.artifact.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class CommentCreateRequest {

    @Lob
    @Column(columnDefinition = "text")
    private String text;
    private String postId;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
