package com.example.wallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    List<Product> products = new ArrayList<>();
    protected static Database database;
    List<Product> product_SQLite;
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager manager;
    ProductAdapter adapter;

    FirebaseFirestore db;

    ProgressDialog progressDialog;

    ImageButton imgAdd_ListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List Book");
        //
        progressDialog = new ProgressDialog(this);
        //
        imgAdd_ListActivity = findViewById(R.id.imgAdd_ListActivity);
        //
        mRecycleView = findViewById(R.id.recycVIew);
        mRecycleView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(manager);
        //
        db = FirebaseFirestore.getInstance();
        showData();
        // SQLite
        product_SQLite = new ArrayList<>();
        database = new Database(this, "Product2.Sqlite",null, 1);
        String sql = "CREATE TABLE IF NOT EXISTS Product( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title VARCHAR(50), author VARCHAR(50), description VARCHAR(50))";
        database.queryData(sql);

        getAllData();
        // SQLite - End

        imgAdd_ListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, MainActivity.class));
                finish();
            }
        });
    }
    private void showData() {
        progressDialog.setTitle("Loading data ...!!!");
        progressDialog.show();
        db.collection("documents")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        // Delet 1 dong:
                        products.clear();

                        progressDialog.dismiss();
                        for (DocumentSnapshot doc: task.getResult()) {
                            Product p = new Product(doc.getString("id"), doc.getString("title"), doc.getString("author"),doc.getString("description"));
                            products.add(p);
                        }
                        adapter = new ProductAdapter(ListActivity.this, products);
//                        adapter.notifyDataSetChanged();
                        mRecycleView.setAdapter(adapter);
                        Toast.makeText(ListActivity.this, "Succeed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(ListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void deleteData(int index){
        progressDialog.setTitle("Delete Data to Firestore");
        progressDialog.show();
        db.collection("documents").document(products.get(index).getId())
                .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ListActivity.this, "Succeed!!", Toast.LENGTH_SHORT).show();
                showData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(ListActivity.this, "Fail!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // sreach : b1- tao menu giao dien -> b2: seacrh -> b3:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { // nhấn search
                searchData(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) { // call when even is single letter
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void searchData(String s) {
        progressDialog.setTitle("Searching...");
        progressDialog.show();

        db.collection("documents").whereEqualTo("search", s.toLowerCase())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                progressDialog.dismiss();
                products.clear();
                for (DocumentSnapshot doc: task.getResult()) {
                    Product model = new Product(doc.getString("id"), doc.getString("title"), doc.getString("author"), doc.getString("description"));
                    products.add(model);
                }
                adapter = new ProductAdapter(ListActivity.this, products);
                mRecycleView.setAdapter(adapter);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(ListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // SQLite - Lay data
    private void getAllData() {
        // Đọc dữ liệu
        Cursor cursor = database.querySelectData("SELECT * FROM Product");
        product_SQLite.clear();
        while (cursor.moveToNext()) {
            String id = String.valueOf(cursor.getInt(0));
            String title = cursor.getString(1);
            String author = cursor.getString(1);
            String description = cursor.getString(1);
            product_SQLite.add(new Product(id, title, author, description));
        }
    }

}