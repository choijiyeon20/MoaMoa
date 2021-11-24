package com.cookandroid.moamoa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Write extends Fragment {
    private View view;

    //네비바 외의 프레그먼트와 연결할 때 꼭 필요한 newlnstnce() 메소드
    public static Write newlnstnce(){
        return new Write();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.activity_write, null);

        ImageButton ibutton = (ImageButton) view.findViewById(R.id.backBoardImageButton);
        ibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Board.newlnstnce());
            }
        });

        //글쓰기 버튼 클릭시 데이터 이동 ( 미완성 )
        Button wbutton = (Button) view.findViewById(R.id.writebutton);
        wbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


        Spinner tagSpinner = (Spinner) view.findViewById(R.id.spinner_tag);
        ArrayAdapter tagAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array, android.R.layout.simple_spinner_item);
        tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagSpinner.setAdapter(tagAdapter);


        return view;
    }

    //액티비티일 때 버튼 설정모음(X)
    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ImageButton ibutton = (ImageButton) findViewById(R.id.backBoardImageButton);
        ibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Board.class);
                startActivity(intent);
                finish();
            }
        });
    }

 */
}