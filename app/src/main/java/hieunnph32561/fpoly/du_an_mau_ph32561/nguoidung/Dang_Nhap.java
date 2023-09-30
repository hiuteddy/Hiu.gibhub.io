package hieunnph32561.fpoly.du_an_mau_ph32561.nguoidung;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import hieunnph32561.fpoly.du_an_mau_ph32561.Fragment.Trang_chu;
import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.thuthuDAO;

public class Dang_Nhap extends AppCompatActivity {
    EditText edtuser, edtpass;
    Button btnsig;
    CheckBox checkBox;
    thuthuDAO dao;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        edtuser = findViewById(R.id.editUsername);
        edtpass = findViewById(R.id.editPassword);
        btnsig = findViewById(R.id.btnLogin);
        checkBox = findViewById(R.id.checkBox);

        dao = new thuthuDAO(this); // Khởi tạo dao ở đây
        sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);

        String savedUsername = sharedPreferences.getString("Mã tt", "");
        String savedPassword = sharedPreferences.getString("Mật khẩu", "");
        boolean savePasswordChecked = sharedPreferences.getBoolean("Lưu mật khẩu", false);

        edtuser.setText(savedUsername);
        edtpass.setText(savedPassword);
        checkBox.setChecked(savePasswordChecked);

        btnsig.setOnClickListener(v -> {
            String username = edtuser.getText().toString();
            String password = edtpass.getText().toString();
            boolean loggedIn = dao.login(username, password);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Mã tt", loggedIn ? username : "");
            editor.putString("Mật khẩu", loggedIn && checkBox.isChecked() ? password : "");
            editor.putBoolean("Lưu mật khẩu", loggedIn && checkBox.isChecked());
            editor.apply();

            if (loggedIn) {
                Intent intent = new Intent(Dang_Nhap.this, Trang_chu.class);
                startActivity(intent);

            } else {
                Toast.makeText(Dang_Nhap.this, "Tài khoản hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
