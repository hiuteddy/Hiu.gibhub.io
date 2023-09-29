package hieunnph32561.fpoly.du_an_mau_ph32561.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.adapter_phieumuon;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.phieumuonDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.sachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.thanhvienDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Phieumuon;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class QlPhieuMuon extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Phieumuon> lista = new ArrayList<>();
    phieumuonDAO dao;
    adapter_phieumuon phieumuon;

    FloatingActionButton flbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_ql_phieumuon, container, false);
        recyclerView = view.findViewById(R.id.rclpm);
        flbtn = view.findViewById(R.id.floatadd);

        dao = new phieumuonDAO(getContext());
        lista = dao.getDSPhieuMuon();
        phieumuon = new adapter_phieumuon( getContext(),lista,dao);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phieumuon);

        flbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showaddpm();
            }
        });

        return view; // Trả về view đã inflate
    }

    public void showaddpm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_thempm, null);

        Spinner spnts = view.findViewById(R.id.spinertensach);
        Spinner spnttv = view.findViewById(R.id.spinertentv);
        EditText txt = view.findViewById(R.id.txtngay);
        EditText txttt = view.findViewById(R.id.edittienthuepm);
        CheckBox cbk = view.findViewById(R.id.cbktt);
        Button btnadd = view.findViewById(R.id.btntpm);
        Button btnhuy = view.findViewById(R.id.btnhuypm);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnttv.getSelectedItem();
                int matv = (int) hsTV.get("MATV");

                HashMap<String, Object> hsSach = (HashMap<String, Object>) spnts.getSelectedItem();
                int masach = (int) hsSach.get("MASACH");

//                int tien = Integer.parseInt(txttt.getText().toString());
//                themphieumuon(matv, masach, tien);

                lista.clear();
                lista.addAll(dao.getDSPhieuMuon());
                phieumuon.notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }



}

