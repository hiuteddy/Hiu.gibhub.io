package hieunnph32561.fpoly.du_an_mau_ph32561.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.List;
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

    private List<Sach> sachList = new ArrayList<>();
    private List<Thanhvien> vienList = new ArrayList<>();
    private sachDAO sachDao;
    private thanhvienDAO thanhVienDao;
    int matv,mas,tien;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_ql_phieumuon, container, false);

        recyclerView = view.findViewById(R.id.rclpm);
        flbtn = view.findViewById(R.id.floatadd);

        dao = new phieumuonDAO(getContext());
        lista = dao.getDSPhieuMuon();
        phieumuon = new adapter_phieumuon(getContext(), lista, dao);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phieumuon);


        sachDao = new sachDAO(getContext());
        thanhVienDao = new thanhvienDAO(getContext());

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

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
        TextView txt = view.findViewById(R.id.txtngay);
     //   TextView txttt = view.findViewById(R.id.edittienthuepm);
        CheckBox cbk = view.findViewById(R.id.cbktt);
        Button btnadd = view.findViewById(R.id.btntpm);
        Button btnhuy = view.findViewById(R.id.btnhuypm);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = sdf.format(calendar.getTime());
        txt.setText(currentDate);

        sachDao = new sachDAO(getContext());
        thanhVienDao = new thanhvienDAO(getContext());
        sachList = (ArrayList<Sach>) sachDao.getAll();
        vienList = (ArrayList<Thanhvien>) thanhVienDao.getAll();
        ArrayAdapter adapter_sach = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, sachList );
        ArrayAdapter adapter_tv = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, vienList);
        spnts.setAdapter(adapter_sach);
        spnttv.setAdapter(adapter_tv);
        spnttv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 matv = vienList.get(position).getMatv();
                Toast.makeText(getContext(), "Chọn: " + vienList.get(position).getHoten(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 mas = sachList.get(position).getMaSach();
                 tien = sachList.get(position).getGiaThue();
                Toast.makeText(getContext(), "Chọn: " + sachList.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phieumuon phieuMuon = new Phieumuon();
                Thanhvien vien = (Thanhvien) spnttv.getSelectedItem();
                Sach sach = (Sach) spnts.getSelectedItem();
                phieuMuon.setMasach(sach.getMaSach());
                phieuMuon.setMatv(vien.getMatv());
                if (cbk.isChecked()) {
                    phieuMuon.setTrasach(1);
                } else {
                    phieuMuon.setTrasach(0);
                }
                Sach sach1 = sachDao.getID(phieuMuon.getMasach() + "");
                phieuMuon.setTienthue(sach1.getGiaThue());
                phieuMuon.setNgay(currentDate); // Sử dụng ngày hiện tại
                if (dao.insert(phieuMuon) > 0) {
                    Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
                    lista.clear();
                    lista.addAll(dao.getDSPhieuMuon());
                    phieumuon.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
                }
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

