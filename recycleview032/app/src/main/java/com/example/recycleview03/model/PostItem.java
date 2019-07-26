package com.example.recycleview03.model;


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
    private boolean isLike;
    private int likeCount;
    private String userName;
    private String postText;
    private String imgUrl;

}
