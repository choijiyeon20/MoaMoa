package com.cookandroid.moamoa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Board extends Fragment {
    private View view;

    //네비바 외의 프레그먼트와 연결할 때 꼭 필요한 newlnstnce() 메소드
    public static Board newlnstnce(){
        return new Board();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.activity_board, null);
        ///원래 :view  = inflater.inflate(R.layout.main_home, container, false);

        ImageButton hbutton = (ImageButton) view.findViewById(R.id.backHomeImageButton);
        hbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Main_home.newlnstnce());
            }
        });
        ImageButton wbutton = (ImageButton) view.findViewById(R.id.writeImageButton);
        wbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Write.newlnstnce());
            }
        });
        return view;
    }


    //↓↓↓↓↓ 홈 프래그먼트 띄우기(프래그먼트 저장) -> 에러 취소
//    private Main_home m_home;
//    private FragmentManager fm;
//    private FragmentTransaction ft;
//
//    public void replaceFragment(Fragment fragment){
//        fm = getSupportFragmentManager();
//        ft = fm.beginTransaction();
////        if (fragmentTransaction.equals(fragmentTransaction.replace(R.id.main_home_frame, fragment)) ){
////            fragmentTransaction.commit();
////        }
////        else{
//        ft.replace(R.id.main_home_frame, fragment);
//        ft.commit();
//        //}
//    }
    //↑↑↑↑↑

/* 액티비티일 때 버튼 설정모음(X)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        ///

        //홈가기 버튼 설정
        ImageButton hbutton = (ImageButton) findViewById(R.id.backHomeImageButton);
        hbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //replaceFragment(m_home);
                finish();
            }
        });
        ImageButton wbutton = (ImageButton) findViewById(R.id.writeImageButton);
        wbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Write.class);
                startActivity(intent);
                finish();
            }
        });

    }
*/
}