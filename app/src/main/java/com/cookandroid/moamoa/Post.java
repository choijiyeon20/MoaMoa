package com.cookandroid.moamoa;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Post extends Fragment {
    private View view;

    private TextView mTextTitle;
    private TextView mTextContent;
    private TextView mTextIngredient;
    private TextView mTextDate;
    String mJsonString;
    Bundle bundle = getArguments();

    private static String IP_ADDRESS = "10.0.2.2";
    private static String TAG = "phptest";


    public static Post newlnstnce(){
        return new Post();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.activity_post, container, false);

        mTextTitle = (TextView) view.findViewById(R.id.post_title);
        mTextContent = (TextView) view.findViewById(R.id.post_content);
        mTextIngredient = (TextView) view.findViewById(R.id.post_ingredient);
        mTextDate = (TextView) view.findViewById(R.id.post_date);

        String postcode = getArguments().getString("listcode");

        GetData task = new GetData();
        task.execute( "http://" + IP_ADDRESS + "/post_query.php", postcode);

        ImageButton Bbutton = (ImageButton) view.findViewById(R.id.backBoardImageButton);
        Bbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Board.newlnstnce());
            }
        });

        ImageButton Cbutton = (ImageButton) view.findViewById(R.id.commentGoButton);
        Cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Post_comment_Activity.newlnstnce());
            }
        });
        ImageButton Rbutton = (ImageButton) view.findViewById(R.id.reviewGoButton);
        Rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Post_review_Activity.newlnstnce());
            }
        });

        return view;
    }

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
            Log.d(TAG, "response - " + result);

            if (result == null){
                Toast.makeText(getActivity(), errorString,Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(), result,Toast.LENGTH_SHORT).show();
                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = "country=" + params[1];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.v(TAG, "response code - " + responseStatusCode);

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

        String TAG_JSON="webnautes";
        String TAG_TITLE = "title";
        String TAG_CONTENT = "content";
        String TAG_INGREDIENT ="ingredient";
        String TAG_DATE ="date";


        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){


                JSONObject item = jsonArray.getJSONObject(i);

                String title = item.getString(TAG_TITLE);
                String content = item.getString(TAG_CONTENT);
                String ingredient = item.getString(TAG_INGREDIENT);
                String date = item.getString(TAG_DATE);
                Log.d(TAG, "받은 값 : "+title);
                Toast.makeText(getActivity(), "받은 값 : "+title,Toast.LENGTH_SHORT).show();

                mTextTitle.setText(title);
                mTextContent.setText(content);
                mTextIngredient.setText(ingredient);
                mTextDate.setText(date);
                Toast.makeText(getActivity(), "세팅된값 : "+mTextTitle,Toast.LENGTH_SHORT).show();
            }



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}
