package com.example.wordchaingame;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private Cursor cursor;

    public WordAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_word_adapter, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            String word = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_WORD));
            holder.wordTextView.setText(word);
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        public TextView wordTextView;

        public WordViewHolder(View itemView) {
            super(itemView);
            wordTextView = itemView.findViewById(R.id.word_text);
        }
    }
}
