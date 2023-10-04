package hieunnph32561.fpoly.du_an_mau_ph32561.Fragment;

import android.app.AlertDialog;
import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.adapter_thanhvien;

import hieunnph32561.fpoly.du_an_mau_ph32561.dao.thanhvienDAO;

import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class QLThanhVien extends Fragment {

    private RecyclerView recyclerView;
    private List<Thanhvien> list;
    adapter_thanhvien adapterThanhvien;
    thanhvienDAO tvdao;
    private Context context;

    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_ql_thanhvien, container, false);

        recyclerView = view.findViewById(R.id.rcltv);
        context = getContext();
        tvdao = new thanhvienDAO(context);
        list = tvdao.getAll();
        adapterThanhvien = new adapter_thanhvien(context, (ArrayList<Thanhvien>) list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterThanhvien);

        floatingActionButton = view.findViewById(R.id.floataddtv);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them();
            }
        });

        return view;
    }

    private void them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_themtv, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
       // EditText edtmtv = view.findViewById(R.id.ed_matv);
        EditText edtTentv = view.findViewById(R.id.ed_hotentv);
        EditText edtns = view.findViewById(R.id.ed_namsinh);

        Button btnXacnhan = view.findViewById(R.id.btn_save_thanhvien);
        Button btnHuy = view.findViewById(R.id.btn_huy_thanhvien);

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   String edtmtvv = edtmtv.getText().toString();
                String edtTentvvv = edtTentv.getText().toString();
                String edtnss = edtns.getText().toString();
              //  int tvv = Integer.parseInt(edtmtvv);
                Thanhvien tv = new Thanhvien( edtTentvvv, edtnss);
                if (edtTentvvv.length() == 0) {
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (tvdao.add(tv) > 0) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        list = tvdao.getAll();
                        adapterThanhvien = new adapter_thanhvien(context, (ArrayList<Thanhvien>) list);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapterThanhvien);
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

}
