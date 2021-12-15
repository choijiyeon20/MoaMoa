package com.cookandroid.moamoa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Board extends Fragment {
    private View view;

    //리스트뷰 값 설정
    /* DB사용으로 actor 코드사용 안함
    ArrayList<Actor> actors;
    ListView listview;
    private static Board_postList_Adaptor boardpostListAdaptor;    
     */
    
    //네비바 외의 프레그먼트와 연결할 때 꼭 필요한 newlnstnce() 메소드
    public static Board newlnstnce(){
        return new Board();
    }

    //DB
    private static String TAG = "php_Board";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DATE = "date";
    private static final String TAG_CONTENT = "content";
    private static final String TAG_POST_CODE = "post_code";

    ArrayList<HashMap<String, String>> mArrayList;
    ListView mlistView;
    String mJsonString;

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

        mlistView = (ListView) view.findViewById(R.id.board_list_view);

        ImageButton wbutton = (ImageButton) view.findViewById(R.id.writeImageButton);
        wbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle listsize = new Bundle();
                listsize.putString("listsize", String.valueOf(mlistView.getCount()));
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Write write = new Write();
                write.setArguments(listsize);
                transaction.replace(R.id.main_home_frame, write);
                transaction.commit();
            }
        });

        //스피너 코드
        Spinner tagSpinner = (Spinner) view.findViewById(R.id.board_spinner_order);
        ArrayAdapter tagAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.board_spinner_array, android.R.layout.simple_spinner_item);
        tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagSpinner.setAdapter(tagAdapter);

        //게시글 리스트 뷰 코드
        /* DB사용으로 actor 코드사용 안함
        actors = new ArrayList<>();
        actors.add(new Actor("테스트", "테스트 리스트 뷰 입니다.", "2021-10-10",R.drawable.ic_baseline_dinner_dining_24));
        actors.add(new Actor("두번째테스트", "두번째테스트 리스트 뷰 입니다.", "2021-10-14",R.drawable.ic_baseline_dinner_dining_24));
        actors.add(new Actor("세번째테스트", "세번째테스트 리스트 뷰 입니다.", "2021-10-18",R.drawable.ic_baseline_dinner_dining_24));
        actors.add(new Actor("네번째테스트", "네번째테스트 리스트 뷰 입니다.", "2021-10-22",R.drawable.ic_baseline_dinner_dining_24));
        actors.add(new Actor("다섯번째테스트", "다섯번째테스트 리스트 뷰 입니다.", "2021-10-26",R.drawable.ic_baseline_dinner_dining_24));

        listview = (ListView) view.findViewById(R.id.board_list_view);
        boardpostListAdaptor = new Board_postList_Adaptor(getContext(),actors);
        listview.setAdapter(boardpostListAdaptor);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
         */
        
        
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


        //DB

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText((MainActivity)getContext(), i + "번째입니다" + mlistView.getCount(),Toast.LENGTH_LONG).show();

                Object o = (Object) adapterView.getAdapter().getItem(i);
                Bundle listcode = new Bundle();

                String res = o.toString(); 
                String result = res.substring(res.indexOf("post_code"),res.indexOf(", title")); //post_code=코드 이름

                listcode.putString("listcode",result.substring(10));

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Post post = new Post();
                post.setArguments(listcode);
                transaction.replace(R.id.main_home_frame, post);
                transaction.commit();
            }
        });


        mArrayList = new ArrayList<>();

        GetData task = new GetData();
        task.execute("http://10.0.2.2/post_list_getjson.php");
        //----------------------

        return view;
    }

    //리스트뷰 사용 클래스 Actor 정의
    /* DB사용으로 actor 코드사용 안함
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
    
     */


    //DB GetData
    private class GetData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show((MainActivity)getActivity(),
                    "Please Wait", null, true, true);


        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            progressDialog.dismiss();

            Log.d(TAG, "response  - " + result);

            if (result == null){
                Toast.makeText(getActivity(), errorString,Toast.LENGTH_SHORT).show();
            }
            else {

                mJsonString = result;
                showResult();
            }
        }



        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            
            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }

    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String title= item.getString(TAG_TITLE);
                String date = item.getString(TAG_DATE);
                String content = item.getString(TAG_CONTENT);
                String post_code = item.getString(TAG_POST_CODE);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_TITLE, title);
                hashMap.put(TAG_DATE, date);
                hashMap.put(TAG_CONTENT, content);
                hashMap.put(TAG_POST_CODE, post_code);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    (MainActivity)getActivity(), mArrayList, R.layout.board_list_item,
                    new String[]{TAG_TITLE, TAG_DATE, TAG_CONTENT, TAG_POST_CODE},
                    new int[]{R.id.board_list_title, R.id.board_list_date, R.id.board_list_contents, R.id.board_list_code}
            );

            mlistView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
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