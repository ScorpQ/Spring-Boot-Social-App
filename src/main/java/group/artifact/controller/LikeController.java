package group.artifact.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group.artifact.entities.Like;
import group.artifact.services.LikeService;
import group.artifact.dto.LikeCreateRequest;

@RestController
public class LikeController {

    public LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/likes")
    public Like likeThePost(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.likeThePost(likeCreateRequest);
    }

}