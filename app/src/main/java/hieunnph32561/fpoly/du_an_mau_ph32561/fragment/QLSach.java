package hieunnph32561.fpoly.du_an_mau_ph32561.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    ArrayList<Loaisach> listLS = new ArrayList<>();
    ArrayList<Sach> list = new ArrayList<>();

    ArrayList<Sach> listtk = new ArrayList<>();

    EditText btntimkiem;
    ImageView imgsx, imgsxtang;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_ql_sach, container, false);
        rcvSach = view.findViewById(R.id.rcls);
        btntimkiem = view.findViewById(R.id.editTimkiem);
        imgsx = view.findViewById(R.id.imgsapxepxuong);
        imgsxtang = view.findViewById(R.id.imgsapxeplen);

        loaiSachDAO = new loaisachDAO(getContext());
        sachDAO = new sachDAO(getContext());


       list = sachDAO.getAll();

        sachAdapter = new adapter_sach(getContext(), list);
        rcvSach.setAdapter(sachAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvSach.setLayoutManager(layoutManager);

        imgsxtang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<Sach> comparator = new Comparator<Sach>() {
                    @Override
                    public int compare(Sach o1, Sach o2) {

                        return o1.getGiaThue() - o2.getGiaThue();
                    }
                };
                Collections.sort(list, comparator);
                sachAdapter.notifyDataSetChanged();
            }
        });
        imgsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<Sach> comparator = new Comparator<Sach>() {
                    @Override
                    public int compare(Sach o1, Sach o2) {

                        return o2.getTenSach() .compareTo( o1.getTenSach());
                    }
                };
                Collections.sort(list, comparator);
                sachAdapter.notifyDataSetChanged();
            }
        });

        btntimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listtk = sachDAO.getAll();
                list.clear();
                for (Sach sach : listtk) {
                    int namXuatBan = sach.getNamXb();
                    if (String.valueOf(namXuatBan).contains(s.toString()) ) {
                        list.add(sach);
                    }
                }
                sachAdapter.notifyDataSetChanged();
            }

//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                listtk = sachDAO.getAll();
//                list.clear();
//                String namNhapStr = s.toString();
//
//                if (namNhapStr.isEmpty()) {
//                    list.addAll(listtk); // Hiển thị toàn bộ danh sách khi không có năm nhập
//                } else {
//                    int namNhap = Integer.parseInt(namNhapStr);
//
//                    if (namNhap >= 2000 && namNhap <= 2020) {
//                        for (Sach sach : listtk) {
//                            int namXuatBan = sach.getNamXb();
//                            if (namXuatBan >= 2000 && namXuatBan <= 2020) {
//                                list.add(sach);
//                            }
//                        }
//                    } else {
//                        for (Sach sach : listtk) {
//                            int namXuatBan = sach.getNamXb();
//                            if (namXuatBan == namNhap) {
//                                list.add(sach);
//                                break; // Chỉ hiển thị duy nhất năm nhập nếu không nằm trong khoảng
//                            }
//                        }
//                    }
//                }
//
//                sachAdapter.notifyDataSetChanged();
          //  }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



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
        EditText edtNamXb = view1.findViewById(R.id.editnamxb);



        Spinner spnLoaiSach = view1.findViewById(R.id.spiner);
        Button btnXacnhan = view1.findViewById(R.id.buttonAddsach);
        Button btnHuy = view1.findViewById(R.id.buttonhuysach);

        listLS = loaiSachDAO.getAll();
        ArrayAdapter arrayAdapte = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, listLS);
        spnLoaiSach.setAdapter(arrayAdapte);

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTenSach.getText().toString().isEmpty() || edtGia.getText().toString().isEmpty() || edtGia.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int gia = Integer.parseInt(edtGia.getText().toString());
                    String tenSach = edtTenSach.getText().toString();
                    int xb = Integer.parseInt(edtNamXb.getText().toString());


                    Loaisach loaiSach = (Loaisach) spnLoaiSach.getSelectedItem();
                    int maLoai = loaiSach.getMaLoai(); // Giả sử bạn có một phương thức getMaLoai() để lấy mã loại sách

                    Sach sach = new Sach(maLoai, tenSach, gia, maLoai,xb);


                    if (sachDAO.insert(sach) > 0) {
                        Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(sachDAO.getAll());
                        sachAdapter.notifyDataSetChanged();
                        dialog.dismiss(); // Đóng hộp thoại sau khi thêm sách thành công
                    } else {
                        Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    // Xử lý khi xảy ra lỗi chuyển đổi từ chuỗi sang số
                    Toast.makeText(getContext(), "Lỗi: Giá và nhà xuất bản phải là số nguyên", Toast.LENGTH_SHORT).show();
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
