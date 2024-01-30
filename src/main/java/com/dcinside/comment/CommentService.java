package com.dcinside.comment;

import com.dcinside.post.Post;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;

    public List<Comment> getCommentList() {
        return this.commentRepository.findAll();
    }

    public Comment getCommentById(Integer commentId) {
        Optional<Comment> oc = this.commentRepository.findById(commentId);
        if (oc.isPresent()) {
            return oc.get();
        }
        throw new EntityNotFoundException("can not found comment given commentId");
    }

    public void addComment(Comment comment, Post post) {
        comment.setPost(post);
        comment.setCreateDate(LocalDateTime.now());
        this.commentRepository.save(comment);
    }

    public void updateComment(Comment oc, Comment comment) {
        oc.setContent(comment.getContent());
        oc.setModifiedDate(LocalDateTime.now());
        this.commentRepository.save(oc);
    }

    public void deleteComment(Comment comment) {
        this.commentRepository.delete(comment);
    }
}


