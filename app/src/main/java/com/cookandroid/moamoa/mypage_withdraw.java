package com.cookandroid.moamoa;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class mypage_withdraw extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";
    private static String TAG = "phptest";

    private String userId;
    private String yes;
    private mypage_withdraw Mypage_withdraw;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_withdraw);

        Button backBtn = (Button) findViewById(R.id.withdraw_back);
        Button withdrawBtn = (Button) findViewById(R.id.withdraw_button);

        Mypage_withdraw = this;

        Intent receive_id = getIntent();
        userId = receive_id.getStringExtra("id");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage();
//                if (yes.equals("yes")) {
//                    //restart(getApplicationContext());
//                    finish();
//                }
                //finish();
            }
        });
    }


    public void showMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("경고");
        builder.setMessage("정말 탈퇴하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mypage_withdraw.DeleteData task = new mypage_withdraw.DeleteData();
                task.execute("http://" + IP_ADDRESS + "/withdraw.php", userId);
//                if (yes.equals("yes")) {
//                    android.os.Process.killProcess(android.os.Process.myPid());
//                }
                //finish();
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "취소하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    class DeleteData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(mypage_withdraw.this,
                    "Please Wait", null, true, true);
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

            progressDialog.dismiss();
            if (result.equals("정상적으로 삭제되었어요.")) {
                //android.os.Process.killProcess(android.os.Process.myPid());
                //yes = "yes";
                //Mypage_withdraw.finish();
//                Intent loginActivity = new Intent(getApplicationContext(), MoAMoA_Login.class);
//                loginActivity.putExtra("withdraw", "yes");
//                startActivity(loginActivity);
//                restart(getApplicationContext());
            }
        }

        protected String doInBackground(String... params) {
            String id = (String)params[1];

            String serverURL = (String)params[0];
            String postParameters = "id=" + id;

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
            }
            catch (Exception e) {
                Log.d(TAG, "SelectData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }
    }




    // 재시작 하는 코드. 새로고침 느낌임.
//    private void restart(Context context) {
//        PackageManager packageManager = context.getPackageManager();
//        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
//        ComponentName componentName = intent.getComponent();
//        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
//        context.startActivity(mainIntent);
//        Runtime.getRuntime().exit(0);
//    }

    //private View view;

    //네비바 외의 프레그먼트와 연결할 때 꼭 필요한 newlnstnce() 메소드
//    public static mypage_withdraw newlnstnce(){
//        return new mypage_withdraw();
//    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.mypage_withdraw, container, false);
//
//        //FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//
//        ImageButton button = (ImageButton) view.findViewById(R.id.outImageButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((MainActivity)getActivity()).replaceFragment(Main_mypage.newlnstnce());
//                //activity.onFragmentChange(1);
//                /* 액티비티일 때 띄우기
//                Intent intent = new Intent(getActivity(), Board.class);
//                startActivity(intent);*/
//
//
//            }
//        });
//        return view;
//    }

}