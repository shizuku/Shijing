package com.shizuku.poetry.shijing.ui.contents;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shizuku.poetry.shijing.R;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private List<ContentItem> contents;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View contentView;
        TextView tx;
        ImageView img;

        public ViewHolder(View view) {
            super(view);
            contentView = view;
            tx = view.findViewById(R.id.content_text);
            img = view.findViewById(R.id.content_image);
        }
    }

    public ContentAdapter(List<ContentItem> strings) {
        contents = strings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();

                String x = contents.get(position).getItem();
                int l = contents.get(position).getLevel();

                if (l == 0) {
                    Intent i = new Intent(v.getContext(), ContentsActivity.class);
                    i.putExtra("level", 1);
                    i.putExtra("item", x);
                    v.getContext().startActivity(i);
                } else if (l == 1) {
                    Intent i = new Intent(v.getContext(), ContentsActivity.class);
                    i.putExtra("level", 2);
                    i.putExtra("item", x);
                    v.getContext().startActivity(i);
                } else {
                    Intent i = new Intent("com.shizuku.poetry.shijing.POET_VIEW");
                    i.putExtra("id", 0);
                    i.putExtra("title",x);
                    v.getContext().startActivity(i);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String c = contents.get(position).getItem();
        holder.tx.setText(c);
        holder.img.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}
