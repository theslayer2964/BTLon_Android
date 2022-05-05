package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class TrangChu extends AppCompatActivity {
    private ImageButton btnReturnTrangChu, btnProfileTrangCaNhan, btnSearchTrangChu;
    private EditText edttimkiem;
    private TextView txtAll, txtScenery, txtAnime, txtCubes;
    private ListView lvTrangChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        btnReturnTrangChu = findViewById(R.id.btnReturnTrangChu);
        btnProfileTrangCaNhan = findViewById(R.id.btnProfileTrangCaNhan);
        btnSearchTrangChu = findViewById(R.id.btnSearchTrangChu);
        edttimkiem = findViewById(R.id.edttimkiem);
        txtScenery = findViewById(R.id.txtScenery);
        txtAll = findViewById(R.id.txtAll);
        txtAnime = findViewById(R.id.txtAnime);
        txtCubes = findViewById(R.id.txtCubes);
        lvTrangChu = findViewById(R.id.lvTrangChu);
    }
}