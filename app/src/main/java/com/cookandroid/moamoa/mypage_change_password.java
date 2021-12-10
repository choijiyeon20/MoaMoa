package com.cookandroid.moamoa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class mypage_change_password extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_change_password);

        Button backbtn = (Button) findViewById(R.id.change_back);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
//    private View view;

    //네비바 외의 프레그먼트와 연결할 때 꼭 필요한 newlnstnce() 메소드
//    public static mypage_change_password newlnstnce(){
//        return new mypage_change_password();
//    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.mypage_change_password, container, false);
//
//        //FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//
//        Button Backbtn = (Button) view.findViewById(R.id.change_back);
//        Backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((MainActivity)getActivity()).replaceFragment(Main_mypage.newlnstnce());
//                //activity.onFragmentChange(1);
//                /* 액티비티일 때 띄우기
//                Intent intent = new Intent(getActivity(), Board.class);
//                startActivity(intent);*/
//            }
//        });
//        return view;
//    }
}