package com.example.digging.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Accessors(chain = true)
@ToString(exclude = {"postTagList"})
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="user_id")
    private User user;

    private Boolean isText;
    private Boolean isImg;
    private Boolean isLink;
    private Boolean isLike;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "posts", cascade = CascadeType.ALL)
    private PostImg postImg;

    @OneToOne(mappedBy = "posts", cascade = CascadeType.ALL)
    private PostLink postLink;

    @OneToOne(mappedBy = "posts", cascade = CascadeType.ALL)
    private PostText postText;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    private List<PostTag> postTagList;
}
