package com.dcinside.comment;

import com.dcinside.post.Post;
import com.dcinside.gallery.GalleryService;
import com.dcinside.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/comment/{postId}")
    public ResponseEntity<String> addComment(@PathVariable("postId") Integer postId, @RequestBody Comment comment) {
        Post post = this.postService.getPostById(postId);
        this.commentService.addComment(comment, post);
        return ResponseEntity.ok("comment added successfully");
    }

    @PutMapping("/comment/{postId}/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable("commentId") Integer commentId, @RequestBody Comment comment) {
        Comment oc = this.commentService.getCommentById(commentId);
        this.commentService.updateComment(oc, comment);
        return ResponseEntity.ok("comment updated successfully");
    }

    @DeleteMapping("/comment/{postId}/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") Integer commentId){
        Comment comment = this.commentService.getCommentById(commentId);
        this.commentService.deleteComment(comment);
        return ResponseEntity.ok("comment deleted successfully");
    }
}
