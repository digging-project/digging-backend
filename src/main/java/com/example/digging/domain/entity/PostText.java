package com.example.digging.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Accessors(chain = true)
@ToString(exclude = {"posts"})
public class PostText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer textId;

    private String title;
    private String content;

    @ManyToOne
//    @JoinColumn(name = "post_id")
    private Posts posts;
}
