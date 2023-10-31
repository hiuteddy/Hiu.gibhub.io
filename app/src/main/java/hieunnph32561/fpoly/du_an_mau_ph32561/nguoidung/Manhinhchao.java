package hieunnph32561.fpoly.du_an_mau_ph32561.nguoidung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import hieunnph32561.fpoly.du_an_mau_ph32561.fragment.Trang_chu;
import hieunnph32561.fpoly.du_an_mau_ph32561.R;

public class Manhinhchao extends AppCompatActivity {
    Button btndn;
    EditText edtdn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);
        btndn = findViewById(R.id.button);
//        edtdn = findViewById(R.id.edtnhap);
//        btndn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (edtdn.getText().toString().equals("ph32561")) {
//                    startActivity(new Intent(Manhinhchao.this, Trang_chu.class));
//
//                } else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(Manhinhchao.this);
//                    builder.setMessage("Mã sinh viên bạn nhập đã sai");
//
//                    builder.setNegativeButton("nhập lại", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // Hủy thao tác xóa
//                            dialog.dismiss();
//                        }
//                    });
//                    // Hiển thị hộp thoại xác nhận xóa
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                }
//
//            }
//        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Manhinhchao.this, Trang_chu.class);
                startActivity(intent);
            }
        },3000);

    }


}