package com.dcinside.post;

import com.dcinside.gallery.Gallery;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    public Post getPostById(Integer postId) {
        Optional<Post> op = this.postRepository.findById(postId);
        if (op.isPresent()) {
            return op.get();
        }
        throw new EntityNotFoundException("can not found post given postId");
    }

    public List<Post> getPostList() {
        return this.postRepository.findAll();
    }

    public List<Post> getPostsByGallery(Gallery gallery){
        return this.postRepository.findAllByGallery(gallery);
    }

    public Post getPostByGalleryAndId(Gallery gallery, Integer postId){
        Optional<Post> op = this.postRepository.findByGalleryAndId(gallery, postId);
        if(op.isPresent()){
            return op.get();
        }
        throw new EntityNotFoundException("can not found post given id.");
    }

    public void savePost(Post post, Gallery gallery) {
        post.setGallery(gallery);
        post.setCreateDate(LocalDateTime.now());
        this.postRepository.save(post);
    }

    public void updatePost(Post op, Post post){
        op.setSubject(post.getSubject());
        op.setContent(post.getContent());
        op.setModifiedDate(LocalDateTime.now());
        this.postRepository.save(op);
    }

    public void deletePost(Post post) {
        this.postRepository.delete(post);
    }
}