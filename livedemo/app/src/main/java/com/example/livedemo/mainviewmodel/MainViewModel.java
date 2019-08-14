package com.example.livedemo.mainviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.livedemo.model.Blog;
import com.example.livedemo.repository.BlogRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private BlogRepository movieRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new BlogRepository(application);
    }

    public LiveData<List<Blog>> getAllBlog() {
        return movieRepository.getMutableLiveData();
    }


}