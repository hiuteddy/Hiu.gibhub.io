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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.adapter_sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.loaisachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.sachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;

public class QLSach extends Fragment {

    RecyclerView rcvSach;
    sachDAO sachDAO;
    loaisachDAO loaiSachDAO;
    adapter_sach sachAdapter;
    List<Loaisach> listLS = new ArrayList<>();
    List<Sach> list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_ql_sach, container, false);
        rcvSach = view.findViewById(R.id.rcls);

        loaiSachDAO = new loaisachDAO(getContext());
        sachDAO = new sachDAO(getContext());


        list = sachDAO.getAll();
        sachAdapter = new adapter_sach(getContext(), list);
        rcvSach.setAdapter(sachAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvSach.setLayoutManager(layoutManager);


        FloatingActionButton fabAddSach = view.findViewById(R.id.floatads);
        fabAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openDiaLog();
            }
        });
        return view;


    }


    private void openDiaLog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_themsach, null);
        builder.setView(view1);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edtTenSach = view1.findViewById(R.id.editts);
        EditText edtGia = view1.findViewById(R.id.editgiasach);
        Spinner spnLoaiSach = view1.findViewById(R.id.spiner);
        Button btnXacnhan = view1.findViewById(R.id.buttonAddsach);
        Button btnHuy = view1.findViewById(R.id.buttonhuysach);

        listLS = loaiSachDAO.getAll();
        ArrayAdapter arrayAdapte = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, listLS);
        spnLoaiSach.setAdapter(arrayAdapte);

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gia = Integer.parseInt(edtGia.getText().toString());
                String tenSach = edtTenSach.getText().toString();
                Loaisach loaiSach = (Loaisach) spnLoaiSach.getSelectedItem();
                int maLoai = loaiSach.getMaLoai(); // Giả sử bạn có một phương thức getMaLoai() để lấy mã loại sách

                Sach sach=new Sach(maLoai,tenSach,gia,maLoai);
                if (edtTenSach.getText().toString().isEmpty() || edtGia.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sachDAO.insert(sach) > 0) {
                    Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    list = sachDAO.getAll();
                    sachAdapter = new adapter_sach(getContext(), list);
                    rcvSach.setAdapter(sachAdapter);
                    sachAdapter.notifyDataSetChanged(); // Cập nhật giao diện
                    dialog.dismiss(); // Đóng hộp thoại sau khi thêm sách thành công
                } else {
                    Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

}
