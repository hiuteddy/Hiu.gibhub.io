package hieunnph32561.fpoly.du_an_mau_ph32561.nguoidung;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
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
import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thuthu;
import hieunnph32561.fpoly.du_an_mau_ph32561.nguoidung.Dang_Nhap;

public class Doimatkhau extends Fragment {
    private thuthuDAO thuThuDao;
    private EditText edPassOld, edpassNew, edPassRepeat;
    private Button btnLua, btnHuy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_doi_matkhau, container, false);

        thuThuDao = new thuthuDAO(getContext());
        edPassOld = view.findViewById(R.id.edit_matkhauhientai);
        edpassNew = view.findViewById(R.id.edit_matkhaumoi);
        edPassRepeat = view.findViewById(R.id.edit_xacnhan);
        btnLua = view.findViewById(R.id.btnsave);
        btnHuy = view.findViewById(R.id.btnhuyy);
        btnLua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("username", "");
                if (valiDate() > 0) {
                    Thuthu thuThu = thuThuDao.getID(user);
                    thuThu.setMatkhau(edpassNew.getText().toString());
                    thuThuDao.upate(thuThu);
                    if (thuThuDao.upate(thuThu) > 0) {
                        Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edpassNew.setText("");
                        edPassRepeat.setText("");
                    } else {
                        Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edpassNew.setText("");
                edPassRepeat.setText("");
            }
        });
        return view;
    }

    private int valiDate() {
        int check = 1;
        if (edPassOld.getText().toString().isEmpty() || edpassNew.getText().toString().isEmpty() || edPassRepeat.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Bạn không được để trống", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("password", "");
            String passNew = edpassNew.getText().toString();
            String passRepeat = edPassRepeat.getText().toString();
            if (!passOld.equals(edPassOld.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!passNew.equals(passRepeat)) {
                Toast.makeText(getContext(), "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }



    }
