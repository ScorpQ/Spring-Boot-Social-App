package group.artifact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import group.artifact.entities.Post;

public interface PostRepository extends JpaRepository<Post, String> {

    // ========================================
    // JPA bu methodu otomatik oluşturuyor. Custom bir 'findyBy'. Nasıl olduğunu
    // araştır.
    // ========================================

    List<Post> findByUserId(Long userId);

}
