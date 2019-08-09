package com.example.recycleviewdemo;

import com.google.gson.annotations.SerializedName;

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
public class Sport {
    @SerializedName("id")
    private int id;
    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("info")
    private String mInfo;
    @SerializedName("subTitle")
    private String mSubTitle;
    @SerializedName("title")
    private String mTitle;



    public String getImageUrl() {
        return mImageUrl;
    }

    public String getInfo() {
        return mInfo;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public String getTitle() {
        return mTitle;
    }
}
