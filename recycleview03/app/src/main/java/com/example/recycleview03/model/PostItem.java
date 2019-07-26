package com.example.recycleview03.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostItem {
    private  boolean isLikeUser;
    private int likeCount;
    private String userName;
    private String postText;
    private String imgUrl;

}
