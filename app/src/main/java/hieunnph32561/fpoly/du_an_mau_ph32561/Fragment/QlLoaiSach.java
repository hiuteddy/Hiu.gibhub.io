package hieunnph32561.fpoly.du_an_mau_ph32561.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.adapter_loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.adapter_sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.loaisachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.sachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;

public class QlLoaiSach extends Fragment {

    RecyclerView rcvLoaiSach;
    ArrayList<Loaisach> list;
    loaisachDAO loaiSachDAO;
    adapter_loaisach loaiSachAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_ql_loaisach, container, false);
        rcvLoaiSach = view.findViewById(R.id.rclls);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcvLoaiSach.setLayoutManager(gridLayoutManager);
        loaiSachDAO = new loaisachDAO(getContext());
        loadData();

        FloatingActionButton fabAddLoaiSach = view.findViewById(R.id.floatadls);
        fabAddLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemLoaiSach();
            }
        });
        return view;
    }
    public  void ThemLoaiSach(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_loaisach, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        EditText edtTenLS = view.findViewById(R.id.ed_tenloaisach);
        Button btnXacnhan = view.findViewById(R.id.btn_save_loaisach);
        Button btnHuy =view.findViewById(R.id.btn_huy_loaisach);

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenls = edtTenLS.getText().toString();
                Loaisach ls = new Loaisach(tenls);
                if (edtTenLS.length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (loaiSachDAO.insertLoaiSach(ls)) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    public void loadData(){
        list = (ArrayList<Loaisach>) loaiSachDAO.getAll();
        loaiSachAdapter = new adapter_loaisach(getContext(),list, loaiSachDAO);
        rcvLoaiSach.setAdapter(loaiSachAdapter);
    }
}