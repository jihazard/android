package com.example.fragmenttest;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends Fragment implements View.OnClickListener {

    private OnColorButtonListener onColorButtonListener;
    private static int RED = 0;
    private static int BLUE = 1;
    private static int GREEN = 2;

    public LeftFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        view.findViewById(R.id.left_red).setOnClickListener(this);
        view.findViewById(R.id.left_blue).setOnClickListener(this);
        view.findViewById(R.id.left_green).setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    onColorButtonListener = (OnColorButtonListener) context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left_red:
                onColorButtonListener.onColorClick(RED);
                break;
            case R.id.left_blue:
                onColorButtonListener.onColorClick(BLUE);
                break;
            case R.id.left_green:
                onColorButtonListener.onColorClick(GREEN);
                break;

        }

    }
}
