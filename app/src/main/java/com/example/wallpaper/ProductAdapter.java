package com.example.wallpaper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ViewHolder> {
    ListActivity listView;
    List<Product> models;

    public ProductAdapter(ListActivity listView, List<Product> models) {
        this.listView = listView;
        this.models = models;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent inten2 = new Intent(listView, TrangChiTiet.class);
                Product product = models.get(position);
                inten2.putExtra("s" ,(Serializable) product);
                listView.startActivity(inten2);
            }
            @Override
            public void onLongItemClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listView);
                String[] options = {"Update", "Delete"};
//                builder.setTitle("Option")
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 0){ // update - b1 (b2: Main)
                            String id = models.get(position).getId();
                            String title = models.get(position).getTitle();
                            String description = models.get(position).getDescription();
                            String author = models.get(position).getAuthor();

                            Intent intent = new Intent(listView , MainActivity.class);
                            intent.putExtra("data_id", id);
                            intent.putExtra("data_title", title);
                            intent.putExtra("data_author", author);
                            intent.putExtra("data_descrip", description);
                            intent.putExtra("data_thuTU", position);
                            listView.startActivity(intent);
//                            mainActivity.updateProduct_SQL(product_sqlist.get(i).getId());
                        }
                        if(i == 1){// delete
                            listView.deleteData(position);
                            // SQL Delete
                            listView.database.queryData("DELETE FROM Product where id = '"+i+"'");
                        }
                    }
                }).create().show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product model = models.get(position);
        holder.txtTitle.setText(model.getTitle());
        holder.txtDescription.setText(model.getDescription());
        holder.txtAuthor.setText(model.getAuthor());
    }
    @Override
    public int getItemCount() {
        return models.size();
    }
}
