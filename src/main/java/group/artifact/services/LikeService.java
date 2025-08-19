package group.artifact.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import group.artifact.entities.Like;
import group.artifact.entities.User;
import group.artifact.entities.Post;
import group.artifact.dto.LikeResponse;
import group.artifact.dto.LikeCreateRequest;
import group.artifact.repository.LikeRepository;
import group.artifact.repository.PostRepository;

@Service
public class LikeService {
    LikeRepository likeRepository;
    PostRepository postRepository;

    UserService userService;
    PostService postService;

    LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<String> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }
        list = likeRepository.findAll();
        return list.stream().map(LikeResponse::new).toList();
    }

    public Like getOneLikeById(Long LikeId) {
        return likeRepository.findById(LikeId).orElse(null);
    }

    public Like likeThePost(LikeCreateRequest request) {
        User user = userService.getUser(request.getUserId());
        Post post = postService.getPost(request.getPostId());
        List<Like> like = likeRepository.findByUserIdAndPostId(user.getId(), post.getId());

        if (like.size() > 0) {
            return null;
        }

        if (user != null && post != null) {
            post.setLikeCounts(post.getLikeCounts() + 1);
            postService.updatePost(post.getId(), post);

            Like likeToSave = new Like();
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        } else
            return null;
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
