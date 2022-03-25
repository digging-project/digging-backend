package com.example.digging.domain.network.response;

import com.example.digging.domain.entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecentDiggingResponse {
    private String resultCode;
    private String type;
    private Integer postId;
    private String title;
    private String url;
    private String content;
    private Integer totalImgNum;
    private ArrayList<ImgsApiResponse> imgs;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isLike;
    private ArrayList<String> tags;
}
