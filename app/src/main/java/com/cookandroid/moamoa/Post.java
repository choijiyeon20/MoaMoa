package com.cookandroid.moamoa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Post extends Fragment {
    private View view;
    public static Post newlnstnce(){
        return new Post();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.activity_post, container, false);


        ImageButton Bbutton = (ImageButton) view.findViewById(R.id.backBoardImageButton);
        Bbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Board.newlnstnce());
            }
        });

        ImageButton Cbutton = (ImageButton) view.findViewById(R.id.commentGoButton);
        Cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Post_comment_Activity.newlnstnce());
            }
        });

        return view;
    }
}
