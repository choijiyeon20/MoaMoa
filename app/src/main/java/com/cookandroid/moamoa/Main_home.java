package com.cookandroid.moamoa;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Main_home extends Fragment {
    private View view;

    //네비바 외의 프레그먼트와 연결할 때 꼭 필요한 newlnstnce() 메소드
    public static Main_home newlnstnce(){
        return new Main_home();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.main_home, container, false);

        //FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();


        Button button = (Button) view.findViewById(R.id.btnBoard);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/


            }
        });
        Button button1 = (Button) view.findViewById(R.id.btnBoard2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/


            }
        });
        Button button2 = (Button) view.findViewById(R.id.btnBoard3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        Button button3 = (Button) view.findViewById(R.id.btnBoard4);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        Button button4 = (Button) view.findViewById(R.id.btnBoard5);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        ImageButton button5 = (ImageButton) view.findViewById(R.id.homing);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        Button button6 = (Button) view.findViewById(R.id.hobutton);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        ImageButton button7 = (ImageButton) view.findViewById(R.id.rank);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        Button button8 = (Button) view.findViewById(R.id.ranking);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        Button button9 = (Button) view.findViewById(R.id.recent);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        ImageButton button10 = (ImageButton) view.findViewById(R.id.reim);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        Button button11 = (Button) view.findViewById(R.id.tr);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        ImageButton button12 = (ImageButton) view.findViewById(R.id.good);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        ImageButton button13 = (ImageButton) view.findViewById(R.id.first);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        ImageButton button14 = (ImageButton) view.findViewById(R.id.second);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        ImageButton button15 = (ImageButton) view.findViewById(R.id.third);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        ImageButton button16 = (ImageButton) view.findViewById(R.id.mmh);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        ImageButton button17 = (ImageButton) view.findViewById(R.id.last);
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });
        ImageButton button18 = (ImageButton) view.findViewById(R.id.you);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Board.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });

        ImageButton button19 = (ImageButton) view.findViewById(R.id.mine);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(mine.newlnstnce());
                //activity.onFragmentChange(1);
                /* 액티비티일 때 띄우기
                Intent intent = new Intent(getActivity(), Board.class);
                startActivity(intent);*/
            }
        });

        return view;
    }
}
