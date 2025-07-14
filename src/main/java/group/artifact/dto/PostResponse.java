package group.artifact.dto;

import group.artifact.entities.Post;

public class PostResponse {

    String id;
    Long userId;
    String userName;
    String title;
    String text;

    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getName();
        this.title = entity.getTitle();
        this.text = entity.getText();
    }

}
