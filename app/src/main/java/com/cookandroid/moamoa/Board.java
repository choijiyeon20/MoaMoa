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
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Board extends Fragment {
    private View view;

    //리스트뷰 값 설정
    ArrayList<Actor> actors;
    ListView listview;
    private static CustomAdaptor customAdaptor;
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

        Button tbutton = (Button) view.findViewById(R.id.test_button);
        tbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Post.newlnstnce());
            }
        });

        //스피너 코드
        Spinner tagSpinner = (Spinner) view.findViewById(R.id.board_spinner_order);
        ArrayAdapter tagAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.board_spinner_array, android.R.layout.simple_spinner_item);
        tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagSpinner.setAdapter(tagAdapter);


        //게시글 리스트 뷰 코드
        actors = new ArrayList<>();
        actors.add(new Actor("테스트", "테스트 리스트 뷰 입니다.", "2021-10-10",R.drawable.ic_baseline_dinner_dining_24));
        actors.add(new Actor("두번째테스트", "두번째테스트 리스트 뷰 입니다.", "2021-10-14",R.drawable.ic_baseline_dinner_dining_24));
        actors.add(new Actor("세번째테스트", "세번째테스트 리스트 뷰 입니다.", "2021-10-18",R.drawable.ic_baseline_dinner_dining_24));
        actors.add(new Actor("네번째테스트", "네번째테스트 리스트 뷰 입니다.", "2021-10-22",R.drawable.ic_baseline_dinner_dining_24));
        actors.add(new Actor("다섯번째테스트", "다섯번째테스트 리스트 뷰 입니다.", "2021-10-26",R.drawable.ic_baseline_dinner_dining_24));

        listview = (ListView) view.findViewById(R.id.board_list_view);
        customAdaptor = new CustomAdaptor(getContext(),actors);
        listview.setAdapter(customAdaptor);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        //게시판 누르면 게시글로 이동
        /*
        FrameLayout post_click = (FrameLayout) view.findViewById(R.id.linearLayout6);
        post_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((MainActivity)getActivity()).replaceFragment(Post_comment_Activity.newlnstnce());
            }
        });
        */
        return view;
    }

    //리스트뷰 사용 클래스 Actor 정의
    class Actor{
        private String title;
        private String contents;
        private String date;
        private int title_img;

        public Actor(String title, String contents, String date,int title_img ){
            this.title = title;
            this.contents = contents;
            this.date = date;
            this.title_img = title_img;
        }

        public String getTitle() {
            return title;
        }

        public String getContents() {
            return contents;
        }

        public String getDate() {
            return date;
        }

        public int getTitle_img() {
            return title_img;
        }
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