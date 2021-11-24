package com.cookandroid.moamoa;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Post_comment_Adaptor extends BaseAdapter {
    private ImageView icon;
    private TextView idText;
    private TextView contentText;
    private TextView dateText;
    private ImageView moreView;

    private ArrayList<Post_comment_Item> post_comment_items = new ArrayList<Post_comment_Item>();

    /* 댓글 리스트 뷰*/
    public Post_comment_Adaptor(){
        //생성자
    }

    @Override
    public int getCount() {
        // 아이템이 담긴 리스트 size 리턴
        return post_comment_items.size();
    }


    //지정한 위치에 있는 데이터 리턴
    @Override
    public Object getItem(int i) {
        return post_comment_items.get(i);
    }

    //지정한 위치에 있는 데이터와 관계된 아이템의 ID를 리턴
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        // item 목록 Layout을 inflate하여 convertView 참조 휙득
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.post_comment_item, parent, false);
        }

        //위젯 참조 휙득
        icon = (ImageView) convertView.findViewById(R.id.post_comment_MyImage);
        idText = (TextView) convertView.findViewById(R.id.post_comment_id);
        contentText = (TextView) convertView.findViewById(R.id.post_comment_content);
        dateText = (TextView) convertView.findViewById(R.id.post_comment_date);
        moreView = (ImageView) convertView.findViewById(R.id.post_comment_more);

        Post_comment_Item commentItem = post_comment_items.get(position);

        //데이터 반영
        icon.setImageResource(commentItem.getIconDrawable());
        idText.setText(commentItem.getIdStr());
        contentText.setText(commentItem.getContentStr());
        dateText.setText(commentItem.getDateStr());
        moreView.setImageResource(commentItem.getMoreDrawable());
        return convertView;
    }

    // 아이템 데이터 추가를 위한 함수
    public void addItem(int icon, String Id, String content, String date, int more){
        Post_comment_Item item = new Post_comment_Item();

        item.setIconDrawable(icon);
        item.setIdStr(Id);
        item.setContentStr(content);
        item.setDateStr(date);
        item.setMoreDrawable(more);

        // 입력받은 아이템 리스트에 추가
        post_comment_items.add(item);
    }
}
