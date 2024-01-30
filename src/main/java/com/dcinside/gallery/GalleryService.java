package com.dcinside.gallery;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GalleryService {
    private final GalleryRepository galleryRepository;

    public List<Gallery> getGalleryList(){
        return galleryRepository.findAll();
    }

    public Gallery getGalleryByEngName(String engName){
        Optional<Gallery> og = galleryRepository.findByEngName(engName);
        if(og.isPresent()){
            return og.get();
        }
        throw new EntityNotFoundException("Can not find any gallery given english name");
    }

    public Gallery getGalleryById(Integer id){
        Optional<Gallery> og = galleryRepository.findById(id);
        if(og.isPresent()){
            return og.get();
        }
        throw new EntityNotFoundException("Can not find any gallery given english name");
    }

    public void saveGallery(Gallery gallery){
        gallery.setCreateDate(LocalDateTime.now());
        galleryRepository.save(gallery);
    }

    public void updateGallery(Gallery og, Gallery gallery) {
        og.setKorName(gallery.getKorName());
        og.setEngName(gallery.getEngName());
        og.setModifiedDate(LocalDateTime.now());
        galleryRepository.save(og);
    }

    public void deleteGallery(Gallery gallery){
        galleryRepository.delete(gallery);
    }
}
