package com.cookandroid.moamoa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Post_comment_Activity extends AppCompatActivity {

    public static Post_comment_Activity newlnstnce(){
            return new Post_comment_Activity();
    }

    private ListView listview;
    private Post_comment_Adaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        ImageButton Backbutton = (ImageButton) findViewById(R.id.backPostImageButton);
        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //어뎁터 생성
        adaptor = new Post_comment_Adaptor();
        //리스트뷰 참조 및 어뎁터 달기
        listview = (ListView) findViewById(R.id.comment_listview);
        listview.setAdapter(adaptor);
        //listview.setOnItemClickListener(listener);

        adaptor.addItem(R.drawable.ic_baseline_person_outline_24, "가나다라", "첫번째 내용", "2021-10-10",R.drawable.ic_baseline_more_horiz_24);
        adaptor.addItem(R.drawable.ic_baseline_person_outline_24, "마바사아", "두번째 내용", "2021-10-20",R.drawable.ic_baseline_more_horiz_24);
        adaptor.addItem(R.drawable.ic_baseline_person_outline_24, "자차카타", "세번째 내용", "2021-10-30",R.drawable.ic_baseline_more_horiz_24);

        adaptor.notifyDataSetChanged();
    }
}
