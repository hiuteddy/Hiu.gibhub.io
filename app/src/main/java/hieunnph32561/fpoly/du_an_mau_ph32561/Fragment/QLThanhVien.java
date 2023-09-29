package hieunnph32561.fpoly.du_an_mau_ph32561.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.adapter_thanhvien;

import hieunnph32561.fpoly.du_an_mau_ph32561.dao.thanhvienDAO;

import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class QLThanhVien extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Thanhvien> list;
      adapter_thanhvien adapterThanhvien;
     thanhvienDAO spdao;
    private Context context;

    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_ql_thanhvien, container, false);

        recyclerView = view.findViewById(R.id.rcltv);
        context = getContext();
        spdao = new thanhvienDAO(context);
        list = spdao.getALLSP();
        adapterThanhvien = new adapter_thanhvien(context, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterThanhvien);

        floatingActionButton=view.findViewById(R.id.floataddtv);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
