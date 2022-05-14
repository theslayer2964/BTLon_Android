package com.example.wallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    EditText edtTitleM, edtDescriptionM , edtAuthorM;
    Button btnSaveM, btnListM;
    //ProgressDialod
    ProgressDialog progressDialog;
    //FIrestore
    FirebaseFirestore db;
    // update - b2 (b1: ListActivity )
    String data_id, data_title, data_description , data_author;
    int data_thuTU;
    TextView txtListBook;
    // SQLite
    ListActivity listActivity;
    List<Product> product_SQLite = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ActionBar && Title ???? AndroidX
        ActionBar actionBar = getSupportActionBar();
        listActivity = new ListActivity();
        mapping();
        // update - b3: (b2 ở trên, khai báo hui !!! )
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            actionBar.setTitle("Update");
            btnSaveM.setText("UPDATE");
            data_id = bundle.getString("data_id");
            data_title = bundle.getString("data_title");
            data_author = bundle.getString("data_author");
            data_description = bundle.getString("data_descrip");
            data_thuTU = bundle.getInt("data_thuTU");

            edtTitleM.setText(data_title);
            edtDescriptionM.setText(data_description);
            edtAuthorM.setText(data_author);
            txtListBook.setText("UPDATE BOOK");
        }
        else{
            actionBar.setTitle("Add data");
            btnSaveM.setText("SAVE");
        }

        progressDialog = new ProgressDialog(this);

        db = FirebaseFirestore.getInstance();
        getAllData();
        btnSaveM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 != null){
                    String id = data_id;
                    String title = edtTitleM.getText().toString().trim();
                    String author = edtAuthorM.getText().toString().trim();
                    String description = edtDescriptionM.getText().toString().trim();
                    updateData(id, title, description , author);
                    listActivity.database.queryData
                            ("UPDATE PRODUCT SET title = '"+title+"',author = '"+author+"', description = '"
                                    +description+"' where id = '"+data_thuTU+"'");
                    Toast.makeText(MainActivity.this, "Update goi SQL", Toast.LENGTH_SHORT).show();
                }
                else{
                    String title = edtTitleM.getText().toString();
                    String author = edtAuthorM.getText().toString();
                    String description = edtDescriptionM.getText().toString();
                    createData(title, description, author);
                    listActivity.database.queryData
                            ("INSERT INTO Product VALUES(null,'" +title+"" + "','"+author+"','"+description+"')");
                    Toast.makeText(MainActivity.this, "Do SQL", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnListM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });
    }

    private void updateData(String id, String title, String description , String author) {
        progressDialog.setTitle("Updating Data to Firestore");
        progressDialog.show();
        db.collection("documents").document(id)
                .update("title", title, "search", title.toLowerCase(),"author",author
                        ,"description", description)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Updating..", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mapping() {
        edtDescriptionM = findViewById(R.id.edtDescriptionM);
        edtTitleM = findViewById(R.id.edtTitleM);
        btnSaveM = findViewById(R.id.btnSaveM);
        btnListM = findViewById(R.id.btnList);
        edtAuthorM = findViewById(R.id.edtAuthorM);
        txtListBook = findViewById(R.id.txtListBook);
    }

    private void createData(String title, String description, String author) {
        progressDialog.setTitle("Adding Data to Firestore");
        progressDialog.show();
        String id = UUID.randomUUID().toString();
        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("title", title);
        // lấy data để search
        doc.put("search", title.toLowerCase());
        doc.put("author", author);
        doc.put("description", description);

        db.collection("documents").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Add successed", Toast.LENGTH_SHORT).show();

                        // SQLite - End
                        edtAuthorM.setText("");
                        edtDescriptionM.setText("");
                        edtTitleM.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void getAllData() {
        // Đọc dữ liệu
        Cursor cursor = listActivity.database.querySelectData("SELECT * FROM Product");
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