package com.example.ex0729_viewer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Post {
    private int id;
    private String uploader;
    private String text;
    private int likes;
    private String create;
    private String update;
    private String image;
}
