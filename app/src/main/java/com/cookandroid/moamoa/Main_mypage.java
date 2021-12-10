package com.cookandroid.moamoa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Main_mypage extends Fragment {
    private View view;

    //네비바 외의 프레그먼트와 연결할 때 꼭 필요한 newlnstnce() 메소드
    public static Main_mypage newlnstnce(){
        return new Main_mypage();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_mypage, container, false);

        //FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

        @SuppressLint("WrongViewCast") ImageButton button = (ImageButton) view.findViewById(R.id.mypage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Mypage_modify.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/


            }
        });

        @SuppressLint("WrongViewCast") ImageButton button1 = (ImageButton) view.findViewById(R.id.orderlist);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(order.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/


            }
        });

        @SuppressLint("WrongViewCast") ImageButton button2 = (ImageButton) view.findViewById(R.id.basket);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(basket.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/


            }
        });

        @SuppressLint("WrongViewCast") ImageButton button3 = (ImageButton) view.findViewById(R.id.delivery);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(delivery.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/


            }
        });

        @SuppressLint("WrongViewCast") ImageButton button4 = (ImageButton) view.findViewById(R.id.change);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(change_password.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/


            }
        });

        @SuppressLint("WrongViewCast") ImageButton button5 = (ImageButton) view.findViewById(R.id.out);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(out.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/


            }
        });






        return view;
    }

}
