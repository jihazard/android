package com.example.yoonstagram_0803.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostItem {
    /*id	uploader	text	likes	image	create	update*/

    private Long id;
    private String uploader;
    private String text;
    private int likes;
    private String image;
    private String create;
    private String update;

}
