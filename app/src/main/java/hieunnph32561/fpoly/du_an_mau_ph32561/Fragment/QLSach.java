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
    ArrayList<Sach> list;
    sachDAO sachDAO;
    adapter_sach sachAdapter;
    loaisachDAO loaiSachDAO;
    ArrayList<Loaisach> listLS;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_ql_loaisach, container, false);
        rcvSach = view.findViewById(R.id.rclls);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvSach.setLayoutManager(layoutManager);
        sachDAO = new sachDAO(getContext());
        loadData();
        FloatingActionButton fabAddSach = view.findViewById(R.id.floatadls);

        fabAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemSach();
            }
        });
        return view;
    }
    public void loadData(){
        list = (ArrayList<Sach>) sachDAO.getAll();
        sachAdapter = new adapter_sach( getContext(),list);
        rcvSach.setAdapter(sachAdapter);
    }
    public void ThemSach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_themsach, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText edtTenSach = view.findViewById(R.id.editts);
        EditText edtGia = view.findViewById(R.id.editgiasach);
        Spinner spnLoaiSach = view.findViewById(R.id.spiner);
        Button btnXacnhan = view.findViewById(R.id.buttonAddsach);
        Button btnHuy = view.findViewById(R.id.buttonhuysach);

        loaiSachDAO  = new loaisachDAO(getContext());
        listLS = (ArrayList<Loaisach>) loaiSachDAO.getAll();
        listLS = new ArrayList<>();
        listLS = (ArrayList<Loaisach>) loaiSachDAO.getAll();
        ArrayAdapter arrayAdapte = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, listLS);
        spnLoaiSach.setAdapter(arrayAdapte);
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTenSach.length() == 0 || edtGia.length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if(listLS.size()>0){
                        Sach sach = new Sach();
                        Loaisach loaiSach = (Loaisach) spnLoaiSach.getSelectedItem();
                        sach.setTenSach(String.valueOf(edtTenSach.getText()));
                        sach.setGiaThue(Integer.parseInt(edtGia.getText().toString()));
                        sach.setMaLoai(loaiSach.getMaLoai());
                        if (sachDAO.insert(sach)>0) {
                            Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                            loadData();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
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
