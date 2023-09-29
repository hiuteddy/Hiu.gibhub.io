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
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.adapter_loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.adapter_sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.loaisachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.sachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;

public class QlLoaiSach extends Fragment {

    private List<Sach> list = new ArrayList<>();
    private List<Loaisach> loaiSachList = new ArrayList<>();
    private loaisachDAO loaiSachDao;
    private sachDAO sachDao;
    private adapter_sach adapter;
    private FloatingActionButton button;
    private RecyclerView recyclerView;
    private EditText  ten, gia, ten_ed, gia_ed;
    private Button them, huy, sua, huy_edit;
    private Spinner spinner_sach, spinner_sach_ed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_ql_sach, container, false);
        sachDao = new sachDAO(getContext());
        loaiSachDao = new loaisachDAO(getContext());
        list = sachDao.getAll();
        adapter = new adapter_sach(getContext(), (ArrayList<Sach>) list);
        recyclerView = view.findViewById(R.id.rcls);
        button = view.findViewById(R.id.floatads);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLog();
            }
        });
//        adapter.setSachClick(new SachClick() {
//            @Override
//            public void onClick(Sach sach) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                View view2 = LayoutInflater.from(getContext()).inflate(R.layout.dia_log_sach_edit, null);
//                builder.setView(view2);
//                Dialog dialog = builder.create();
//                dialog.show();
//                ten_ed = view2.findViewById(R.id.ed_tensach_edit);
//                gia_ed = view2.findViewById(R.id.ed_giathuesach_edit);
//                ten_ed.setText(sach.getTenSach());
//                gia_ed.setText(sach.getGiaThue() + "");
//                spinner_sach_ed = view2.findViewById(R.id.spn_loaisach_edit);
//                sua = view2.findViewById(R.id.btn_save_sach_edit);
//                huy_edit = view2.findViewById(R.id.btn_huy_sach_edit);
//                loaiSachList = loaiSachDao.getAll();
//                ArrayAdapter arrayAdapte = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, loaiSachList);
//                spinner_sach_ed.setAdapter(arrayAdapte);
//                int position = -1;
//                for (int i = 0; i < loaiSachList.size(); i++) {
//                    if (sach.getMaLoai() == (loaiSachList.get(i).getMaLoai())) {
//                        position = i;
//                        break;
//                    }
//
//                }
//                spinner_sach_ed.setSelection(position);
//                sua.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (valiDateSua() > 0) {
//                            LoaiSach loaiSach = (LoaiSach) spinner_sach_ed.getSelectedItem();
//                            sach.setTenSach(ten_ed.getText().toString());
//                            sach.setGiaThue(Integer.parseInt(gia_ed.getText().toString()));
//                            sach.setMaLoai(loaiSach.getMaLoai());
//                            if (sachDao.upate(sach) > 0) {
//                                Toast.makeText(getContext(), "Sửa sách thành công", Toast.LENGTH_SHORT).show();
//                                list.clear();
//                                list.addAll(sachDao.getAll());
//                                adapter.notifyDataSetChanged();
//                                ten_ed.setText("");
//                                gia_ed.setText("");
//                            } else {
//                                Toast.makeText(getContext(), "Sửa sách thất bại", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
//                huy_edit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//            }
//        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void openDiaLog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_themsach, null);
        builder.setView(view1);
        Dialog dialog = builder.create();
        dialog.show();
        ten = view1.findViewById(R.id.editts);
        gia = view1.findViewById(R.id.editgiasach);
        spinner_sach = view1.findViewById(R.id.spiner);
        them = view1.findViewById(R.id.buttonAddsach);
        huy = view1.findViewById(R.id.buttonhuysach);
        loaiSachList = loaiSachDao.getAll();
        ArrayAdapter arrayAdapte = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, loaiSachList);
        spinner_sach.setAdapter(arrayAdapte);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valiDateThem() > 0) {
                    if(loaiSachList.size()>0){
                        Sach sach = new Sach();
                        Loaisach loaiSach = (Loaisach) spinner_sach.getSelectedItem();
                        sach.setTenSach(ten.getText().toString());
                        sach.setGiaThue(Integer.parseInt(gia.getText().toString()));
                        sach.setMaLoai(Integer.parseInt(String.valueOf(loaiSach.getMaLoai())));
                        if (sachDao.insert(sach) > 0) {
                            Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(sachDao.getAll());
                            adapter.notifyDataSetChanged();
                            ten.setText("");
                            gia.setText("");
                        } else {
                            Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(), "Chưa có loại sách bạn phải thêm loại sách trước", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private int valiDateThem() {
        int check = 1;
        if (ten.getText().length() == 0 || gia.getText().length() == 0) {
            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    private int valiDateSua() {
        int check = 1;
        if (ten_ed.getText().length() == 0 || gia_ed.getText().length() == 0) {
            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}