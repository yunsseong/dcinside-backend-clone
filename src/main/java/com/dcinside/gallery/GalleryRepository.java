package com.dcinside.gallery;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
    Optional<Gallery> findByKorName(String name);
    Optional<Gallery> findByEngName(String engName);
}
