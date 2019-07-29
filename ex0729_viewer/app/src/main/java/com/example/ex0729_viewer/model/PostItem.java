package com.example.ex0729_viewer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostItem {
    private String userName;
    private String postText;
    private String imgUrl;

    private int likeCount;
    private boolean isLike;

}
