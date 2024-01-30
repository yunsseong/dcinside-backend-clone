package com.dcinside.post;
import com.dcinside.gallery.Gallery;
import com.dcinside.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByGallery(Gallery gallery);

    Optional<Post> findByGalleryAndId(Gallery gallery, Integer PostId);
}
