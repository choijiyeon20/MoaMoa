package com.cookandroid.moamoa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Write extends Fragment {
    private View view;

    private static String IP_ADDRESS = "10.0.2.2";
    private static String TAG = "phptest";

    //네비바 외의 프레그먼트와 연결할 때 꼭 필요한 newlnstnce() 메소드
    public static Write newlnstnce(){
        return new Write();
    }

    //DB 변수 설정
    private EditText mEditTextTitle;
    private EditText mEditTextContent;
    private EditText mEditTextIngredient;
    Bundle bundle = getArguments(); // 게시글 코드를 위한 (값 전달을 위한)번들 -> 리스트뷰 아이템 총 개수를 나타냄
    //private TextView mTextViewResult; 확인용 메시지 출력 변수




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


        //스피너
        Spinner tagSpinner = (Spinner) view.findViewById(R.id.spinner_tag);
        ArrayAdapter tagAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array, android.R.layout.simple_spinner_item);
        tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagSpinner.setAdapter(tagAdapter);

        //DB 저장
        mEditTextTitle = (EditText) view.findViewById(R.id.write_title);
        mEditTextContent = (EditText) view.findViewById(R.id.write_content);
        mEditTextIngredient = (EditText) view.findViewById(R.id.write_ingredient);
        //mTextViewResult = (TextView) view.findViewById(R.id.resist_result);


        //mTextViewResult.setMovementMethod(new ScrollingMovementMethod());
        //글쓰기 버튼 클릭시 데이터 이동 ( 미완성 )
        Button wbutton = (Button) view.findViewById(R.id.writebutton);
        wbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mEditTextTitle.getText().toString();
                String content = mEditTextContent.getText().toString();
                String ingredient = mEditTextIngredient.getText().toString();
                String postcode = getArguments().getString("listsize");

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/post_write_insert.php", title, content, ingredient, postcode);

            }
        });
        return view;
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

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
            //mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
            if (result.equals("게시글이 작성되었습니다!")){
                ((MainActivity)getActivity()).replaceFragment(Board.newlnstnce());
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String title = (String)params[1];
            String content = (String)params[2];
            String ingredient = (String)params[3];
            String post_code = (String)params[4];

            String serverURL = (String)params[0];

            String postParameters = "title=" + title + "&content=" + content + "&ingredient=" + ingredient + "&post_code=" + post_code;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

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
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }
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