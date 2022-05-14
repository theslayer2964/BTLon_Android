package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TrangChiTiet extends AppCompatActivity {
    private ImageButton btnBackTrangChu, btnDowloadTrangChu;
    private ImageView imgChiTietChiTiet;
    private TextView txtSoluongtaiTrangChu, txtTenChuSoHuuTrangChu, txtTenImgTrangChu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chi_tiet);
        mapping();

        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("s");
        txtTenImgTrangChu.setText(p.getTitle());
        txtTenChuSoHuuTrangChu.setText(p.getAuthor());
        txtSoluongtaiTrangChu.setText(p.getDescription());

        btnBackTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChiTiet.this, ListActivity.class);
                startActivity(intent);
            }
        });
        btnDowloadTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TrangChiTiet.this, "download", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mapping() {
        btnBackTrangChu = findViewById(R.id.btnBackTrangChu);
        btnDowloadTrangChu = findViewById(R.id.btnDowloadTrangChu);
        imgChiTietChiTiet = findViewById(R.id.imgChiTietChiTiet);
        txtSoluongtaiTrangChu = findViewById(R.id.txtSoluongtaiTrangChu);
        txtTenChuSoHuuTrangChu = findViewById(R.id.txtTenChuSoHuuTrangChu);
        txtTenImgTrangChu = findViewById(R.id.txtTenImgTrangChu);
    }
}