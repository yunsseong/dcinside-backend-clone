package com.dcinside.comment;

import com.dcinside.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Post post;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
}
