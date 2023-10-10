package hieunnph32561.fpoly.du_an_mau_ph32561.nguoidung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;

public class Manhinhchao extends AppCompatActivity {
    Button btndn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);
        btndn=findViewById(R.id.button);
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Manhinhchao.this, Dang_Nhap.class));

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Manhinhchao.this,Dang_Nhap.class));
            }
        },1000);
    }
}