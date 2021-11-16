package com.cookandroid.moamoa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Post_review_Activity  extends Fragment {

    private View view;

    public static Post_review_Activity newlnstnce(){
        return new Post_review_Activity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.activity_post_review, null);

        ImageButton Bbutton = (ImageButton) view.findViewById(R.id.backPostImageButton);
        Bbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Post.newlnstnce());
            }
        });

        return view;
    }
}
