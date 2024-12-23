package com.example.wordchaingame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_member, parent, false);
        }

        User user = userList.get(position);

        TextView nameTextView = convertView.findViewById(R.id.textView);
        TextView idTextView = convertView.findViewById(R.id.textView2);
        TextView passwordTextView = convertView.findViewById(R.id.textView3);
        TextView hightscoreTextView = convertView.findViewById(R.id.textView4);

        nameTextView.setText(user.getName());
        idTextView.setText(user.getId());
        passwordTextView.setText(user.getPassword());
        hightscoreTextView.setText(user.getH_score());

        return convertView;
    }
    public View getView1(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_ranking, parent, false);
        }

        User user = userList.get(position);

        TextView nameTextView = convertView.findViewById(R.id.textView);
        TextView idTextView = convertView.findViewById(R.id.textView2);
        TextView hightscoreTextView = convertView.findViewById(R.id.textView4);

        nameTextView.setText(user.getName());
        idTextView.setText(user.getId());
        hightscoreTextView.setText(user.getH_score());

        return convertView;
    }
}
