package com.example.recycleviewex_02.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostItem {
    public String username;
    public String postImgUrl;
    public String postText;
    public  int postLikeCount;
    boolean isUserLike;



}
