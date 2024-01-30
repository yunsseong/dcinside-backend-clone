package com.dcinside.gallery;

import com.dcinside.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String korName;

    @Column(length = 100)
    private String engName;

    private LocalDateTime createDate;

    private LocalDateTime modifiedDate;

    @JsonIgnore
    @OneToMany(mappedBy = "gallery", cascade = CascadeType.REMOVE)
    private List<Post> postList;

    @Builder
    public Gallery(Integer id, String korName, String engName, LocalDateTime createDate) {
        this.id = id;
        this.korName = korName;
        this.engName = engName;
        this.createDate = createDate;
    }
}
