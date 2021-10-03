package com.cookandroid.moamoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Board extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

}