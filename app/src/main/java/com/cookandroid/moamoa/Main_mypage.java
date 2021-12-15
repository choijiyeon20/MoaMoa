package com.cookandroid.moamoa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Main_mypage extends Fragment {
    private ViewGroup view;
    private String MoaMoaUserID;
    private TextView mypageName;

    //네비바 외의 프레그먼트와 연결할 때 꼭 필요한 newlnstnce() 메소드
    public static Main_mypage newlnstnce(){
        return new Main_mypage();
    }

    MainActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    //이 메소드가 호출될떄는 프래그먼트가 엑티비티위에 올라와있는거니깐 getActivity메소드로 엑티비티참조가능
        activity = (MainActivity) getActivity();
        MoaMoaUserID = activity.getMoaMoaUser();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    //이제 더이상 엑티비티 참초가안됨
        activity = null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.main_mypage, container, false);

        mypageName = view.findViewById(R.id.mypage_name);
        mypageName.setText(MoaMoaUserID);



        //로그인 유저값을 가져오는 코드

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
                Intent change = new Intent(activity, mypage_change_password.class);
                change.putExtra("id", MoaMoaUserID);
                startActivity(change);
            }
        });

        @SuppressLint("WrongViewCast") ImageButton button5 = (ImageButton) view.findViewById(R.id.out);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent withdraw = new Intent(activity, mypage_withdraw.class);
                withdraw.putExtra("id", MoaMoaUserID);
                startActivity(withdraw);
            }
        });
        return view;
    }
}
