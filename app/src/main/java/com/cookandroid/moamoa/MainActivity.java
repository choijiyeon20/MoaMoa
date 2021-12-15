package com.cookandroid.moamoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    //↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-하단 네비바 구성
    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;

    public FragmentManager getfm() {
        return fm;
    }

    private Fragment m_home;
    private Fragment m_Search;
    private Fragment m_favorite;
    private Fragment m_notice;
    private Fragment m_mypage;
    //↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-하단 네비바 구성
    private String MoaMoaUser;
    protected String getMoaMoaUser() {
        return MoaMoaUser;
    }
    // 로그인 한 유저의 아이디 저장, 값 가져오기.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 프래그먼트 생성
        m_home = new Main_home();
        m_Search = new Main_search();
        m_favorite = new Main_favorite();
        m_notice = new Main_notice();
        m_mypage = new Main_mypage();

        // 로그인 액티비티 관련 코드
        Intent receive_id = getIntent();
        MoaMoaUser = receive_id.getStringExtra("UserId");

        MoAMoA_Login loginActivity = (MoAMoA_Login)MoAMoA_Login.LoginActivity;
        //MoaMoaUser = loginActivity.getUserId();  // 로그인 정보 받아옴
        loginActivity.finish();



        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.main_home_frame, ).commit();

        //↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-하단 네비바 구성
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_bar_home:
                        setMain(0);
                        break;
                    case R.id.action_bar_search:
                        setMain(1);
                        break;
                    case R.id.action_bar_favorite:
                        setMain(2);
                        break;
                    case R.id.action_bar_notice:
                        setMain(3);
                        break;
                    case R.id.action_bar_mypage:
                        setMain(4);
                        break;
                }

                return true;
            }
        });

        m_home = new Main_home();
        m_Search = new Main_search();
        m_favorite = new Main_favorite();
        m_notice = new Main_notice();
        m_mypage = new Main_mypage();

        setMain(0); // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택

        //↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-하단 네비바 구성
        
    }

    //↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-↓-하단 네비바 구성
    // 프래그먼트 교체가 일어나는 실행문이다
    private void setMain(int n){

        Bundle UserID = new Bundle();
        UserID.putString("id", getMoaMoaUser());
        // 유저 정보를 받아오는 코드

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.main_home_frame, m_home);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_home_frame, m_Search);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_home_frame, m_favorite);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_home_frame, m_notice);
                ft.commit();
                break;
            case 4:
                m_mypage.setArguments(UserID);
                ft.replace(R.id.main_home_frame, m_mypage);
                ft.commit();
                break;
        }
    }
    //↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-↑-하단 네비바 구성


    //네비 바가 아닌 프레그먼트 내부에서 다른 프래그먼트 전환 시 이용할 메소드 정의
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_home_frame, fragment);
        fragmentTransaction.commit();
    }


}