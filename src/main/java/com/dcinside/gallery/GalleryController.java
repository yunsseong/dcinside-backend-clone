package com.dcinside.gallery;

import com.dcinside.post.Post;
import com.dcinside.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;
    private final PostService postService;

    @GetMapping("/gallery/list")
    public List<Gallery> getGalleryList() {
        return this.galleryService.getGalleryList();
    }

    @PostMapping("/gallery")
    public ResponseEntity<String> addGallery(@RequestBody Gallery gallery){
        this.galleryService.saveGallery(gallery);
        return ResponseEntity.ok("Gallery added successfully");
    }
    @PutMapping("/gallery/{engName}")
    public ResponseEntity<String> updateGalleryName(@PathVariable String engName, @RequestBody Gallery gallery) {
        Gallery og = this.galleryService.getGalleryByEngName(engName);
        this.galleryService.updateGallery(og, gallery);
        return ResponseEntity.ok("Gallery updated successfully");
    }
    @DeleteMapping("/gallery/{engName}")
    public ResponseEntity<String> deleteGallery(@PathVariable String engName) {
        Gallery gallery = this.galleryService.getGalleryByEngName(engName);
        this.galleryService.deleteGallery(gallery);
        return ResponseEntity.ok("Gallery deleted successfully");
    }
}
