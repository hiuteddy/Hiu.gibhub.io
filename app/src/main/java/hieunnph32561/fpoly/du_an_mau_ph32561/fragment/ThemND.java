package hieunnph32561.fpoly.du_an_mau_ph32561.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.thuthuDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thuthu;

public class ThemND extends Fragment {
    private EditText txttendn, txthoten, txtmk, txtxacnhan;
    private Button btnsave, btnhuy;
    thuthuDAO thuthuDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_themnguoidung, container, false);
        txttendn = view.findViewById(R.id.ed_tendangnhap_themtt);
        txthoten = view.findViewById(R.id.ed_hoten_themtt);
        txtmk = view.findViewById(R.id.ed_matkhau_themtt);
        txtxacnhan = view.findViewById(R.id.ed_matkhaumoi_xacnhan);
        btnsave = view.findViewById(R.id.btn_save_themtt);
        btnhuy = view.findViewById(R.id.btn_huy_themtt);
        thuthuDAO=new thuthuDAO(getContext());

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate() > 0) {
                    String txttdn = txttendn.getText().toString();
                    String txtht = txthoten.getText().toString();
                    String txtmatkhau = txtmk.getText().toString();
                    Thuthu thuthu = new Thuthu();
                    thuthu.setMatt(txttdn);
                    thuthu.setHoten(txtht);
                    thuthu.setMatkhau(txtmatkhau);
                        if(thuthuDAO.insert(thuthu)>0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                        }

                }
            }


        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txthoten.setText("");
                txttendn.setText("");
                txtmk.setText("");
                txtxacnhan.setText("");
            }
        });

        return view;
    }

    private int validate() {
        int check = 1;
        if (txthoten.length() == 0 || txttendn.length() == 0 || txtmk.length() == 0 || txtxacnhan.length() == 0) {
            Toast.makeText(getContext(), "Vui lòng không để trống ô nhập!", Toast.LENGTH_SHORT).show();
             check = -1;
        } else {
            String matkhauht = txtmk.getText().toString();
            String xacnhanmk = txtxacnhan.getText().toString();
            if (!matkhauht.equals(xacnhanmk)) {
                Toast.makeText(getContext(), "Mật khẩu lặp lại không đúng", Toast.LENGTH_SHORT).show();

                check = -1;
            }
        }
        return check;

    }
}
