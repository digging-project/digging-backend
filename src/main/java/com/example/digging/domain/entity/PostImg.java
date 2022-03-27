package com.example.digging.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Accessors(chain = true)
@ToString(exclude = {"imgsList"})
public class PostImg implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Integer imgId;

    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postImg", cascade = CascadeType.ALL)
    private List<Imgs> imgsList;

    @OneToOne
    private Posts posts;

}
