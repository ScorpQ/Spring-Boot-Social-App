package group.artifact.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class PostCreateRequest {
    // Post create ru
    //
    String id;
    @Lob
    @Column(columnDefinition = "text")
    String text;
    String title;
    Long userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}