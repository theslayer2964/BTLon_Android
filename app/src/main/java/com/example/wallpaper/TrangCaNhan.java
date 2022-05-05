package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TrangCaNhan extends AppCompatActivity {
    private ImageButton btnReturnTrangCaNhan, btnLogoutTrangCaNhan, btnUpdateTrangCaNhan;
    private ImageView btnThemTrangCaNhan;
    private TextView txtTenTrangCaNhan, txtNgaySinhTrangCaNhan;
    private ListView lvTrangCaNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);

        btnReturnTrangCaNhan = findViewById(R.id.btnReturnTrangCaNhan);
        btnLogoutTrangCaNhan = findViewById(R.id.btnLogoutTrangCaNhan);
        btnUpdateTrangCaNhan = findViewById(R.id.btnUpdateTrangCaNhan);
        btnThemTrangCaNhan = findViewById(R.id.btnThemTrangCaNhan);
        txtTenTrangCaNhan = findViewById(R.id.txtTenTrangCaNhan);
        txtNgaySinhTrangCaNhan = findViewById(R.id.txtNgaySinhTrangCaNhan);
        lvTrangCaNhan = findViewById(R.id.lvTrangCaNhan);

        btnReturnTrangCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangCaNhan.this, TrangChu.class);
                startActivity(intent);
            }
        });

    }
}