package com.cookandroid.moamoa;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
// 리스트 뷰 어답터
/* DB 사용으로 어답터(actor)사용 안함
public class Board_postList_Adaptor extends ArrayAdapter implements AdapterView.OnItemClickListener {
    private Context context;
    private List list;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    class ViewHolder{
        public TextView tv_title;
        public TextView tv_contents;
        public TextView tv_date;
        public ImageView tv_titie_img;
    }
    public Board_postList_Adaptor(Context context, ArrayList list){
        super(context, 0, list);
        this.context = context;
        this.list= list;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.board_list_item, parent,false);
        }

        viewHolder = new ViewHolder();
        viewHolder.tv_title = (TextView) convertView.findViewById(R.id.board_list_title);
        viewHolder.tv_contents = (TextView) convertView.findViewById(R.id.board_list_contents);
        viewHolder.tv_date = (TextView) convertView.findViewById(R.id.board_list_date);
        viewHolder.tv_titie_img = (ImageView) convertView.findViewById(R.id.board_list_imageView);

        final Board.Actor actor =  (Board.Actor) list.get(position);
        viewHolder.tv_title.setText(actor.getTitle());
        viewHolder.tv_contents.setText(actor.getContents());
        viewHolder.tv_date.setText(actor.getDate());
        viewHolder.tv_titie_img.setImageResource(actor.getTitle_img());
        return convertView;
    }

}

 */
