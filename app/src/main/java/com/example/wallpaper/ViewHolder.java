package com.example.wallpaper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle, txtDescription, txtAuthor, txtId_SQLite_Model;
    View view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onLongItemClick(view, getAdapterPosition());
                return true;
            }
        });

        txtTitle = itemView.findViewById(R.id.txtTtile_Model);
        txtDescription = itemView.findViewById(R.id.txtDescript_Model);
        txtAuthor = itemView.findViewById(R.id.txtAuthor_Model);
        txtId_SQLite_Model = itemView.findViewById(R.id.txtId_SQLite_Model);
    }
    private ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onLongItemClick(View view, int position);
    }
    public void setOnClickListener(ClickListener clickListener){
        mClickListener = clickListener;
    }
}
