package com.dcinside;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "gallery", cascade = CascadeType.REMOVE)
    private List<Post> postList;
}
