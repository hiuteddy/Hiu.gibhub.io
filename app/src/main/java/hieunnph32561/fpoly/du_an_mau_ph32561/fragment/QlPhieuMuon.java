package hieunnph32561.fpoly.du_an_mau_ph32561.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
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
import java.util.Collections;
import java.util.Comparator;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.PhieuMuonClick;
import hieunnph32561.fpoly.du_an_mau_ph32561.adapter.adapter_phieumuon;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.phieumuonDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.sachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.thanhvienDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Phieumuon;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class QlPhieuMuon extends Fragment {

    RecyclerView recyclerView;
    adapter_phieumuon phieumuonn;
    FloatingActionButton flbtn;
    ArrayList<Phieumuon> lista = new ArrayList<>();
    ArrayList<Sach> sachList = new ArrayList<>();
    ArrayList<Thanhvien> vienList = new ArrayList<>();
    sachDAO sachDao;
    thanhvienDAO thanhVienDao;
    phieumuonDAO dao;
    long millis = System.currentTimeMillis();
    java.sql.Date date = new java.sql.Date(millis);

    ImageView sxtang;

    private int matv,mas,tien;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_ql_phieumuon, container, false);

        recyclerView = view.findViewById(R.id.rclpm);
        flbtn = view.findViewById(R.id.floatadd);
        sxtang=view.findViewById(R.id.imalen);

        dao = new phieumuonDAO(getContext());
        lista = dao.getAll();
        phieumuonn = new adapter_phieumuon(getContext(), lista, dao);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phieumuonn);


        sachDao = new sachDAO(getContext());
        thanhVienDao = new thanhvienDAO(getContext());

        flbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showaddpm();
            }
        });

        sxtang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hamsxtang();
            }
        });


        phieumuonn.setPhieuMuonClick(new PhieuMuonClick() {
            @Override
            public void onClick(Phieumuon phieumuonm) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View view1 = inflater.inflate(R.layout.dialog_updatepm, null);

                // Khởi tạo các thành phần giao diện
                Spinner spnts = view1.findViewById(R.id.spinertensachs);
                Spinner spnttv = view1.findViewById(R.id.spinertentvs);
                TextView txt = view1.findViewById(R.id.txtngays);
                TextView txttt = view1.findViewById(R.id.edittienthuepms);
                CheckBox cbk = view1.findViewById(R.id.cbktts);
                Button btnudt = view1.findViewById(R.id.btntpms);
                Button btnhuy = view1.findViewById(R.id.btnhuypms);

                // Thiết lập giao diện
                builder.setView(view1);
                AlertDialog dialog = builder.create();
                dialog.show();

                // Khởi tạo các đối tượng DAO và danh sách
                sachDao = new sachDAO(getContext());
                thanhVienDao = new thanhvienDAO(getContext());
                sachList = (ArrayList<Sach>) sachDao.getAll();
                vienList = (ArrayList<Thanhvien>) thanhVienDao.getAll();

                // Khởi tạo các Adapter và gán cho Spinner
                ArrayAdapter<Sach> adapter_sach = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, sachList);
                ArrayAdapter<Thanhvien> adapter_tv = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, vienList);
                spnts.setAdapter(adapter_sach);
                spnttv.setAdapter(adapter_tv);

                // Thiết lập sự kiện khi mục được chọn trong Spinner spnttv
                spnttv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        txt.setText("Ngày thuê: " + sdf.format(phieumuonm.getNgay()));
                        matv=vienList.get(position).getMatv();
                        Toast.makeText(getContext(), "Chọn: " + vienList.get(position).getHoten(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                // Thiết lập sự kiện khi mục được chọn trong Spinner spnts
                spnts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        txttt.setText("Giá: " + sachList.get(position).getGiaThue());
                        mas=sachList.get(position).getMaSach();
                        tien=sachList.get(position).getGiaThue();
                        Toast.makeText(getContext(), "Chọn: " + sachList.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                int vt_sach = -1;
                int vt_thanhvien = -1;
                for (int i = 0; i < vienList.size(); i++) {
                    if (phieumuonm.getMatv() == (vienList.get(i).getMatv())) {
                        vt_thanhvien = i;
                        break;
                    }

                }
                spnttv.setSelection(vt_thanhvien);

                for (int i = 0; i < sachList.size(); i++) {
                    if (phieumuonm.getMasach() == (sachList.get(i).getMaSach())) {
                        vt_sach = i;
                        break;
                    }

                }
                spnts.setSelection(vt_sach);
                // Thiết lập sự kiện khi nhấn nút "Thêm"
                btnudt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Lấy các đối tượng được chọn trong Spinner
                        Thanhvien vien = (Thanhvien) spnttv.getSelectedItem();
                        Sach sach = (Sach) spnts.getSelectedItem();

                        phieumuonm.setMasach(sach.getMaSach());
                        phieumuonm.setMatv(vien.getMatv());
                        phieumuonm.setNgay(java.sql.Date.valueOf(String.valueOf(date)));

                        // Gán giá trị Trasach dựa trên trạng thái CheckBox
                        if (cbk.isChecked()) {
                            phieumuonm.setTrasach(1);
                        } else {
                            phieumuonm.setTrasach(0);
                        }

                        // Lấy thông tin giá thuê từ đối tượng Sach
                        Sach sach2 = sachDao.getID(phieumuonm.getMasach() + "");
                        phieumuonm.setTienthue(sach2.getGiaThue());


                        // Thêm phiếu mượn vào cơ sở dữ liệu
                        if (dao.update(phieumuonm) > 0) {
                            Toast.makeText(getContext(), "Update phiếu mượn thành công", Toast.LENGTH_SHORT).show();
                            lista.clear();
                            lista.addAll(dao.getAll());
                            phieumuonn.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "Update phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });

                // Thiết lập sự kiện khi nhấn nút "Hủy"
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        return view;
    }

    private void hamsxtang() {
        // Tạo một Comparator để sắp xếp dựa trên mã sách
        Comparator<Phieumuon> comparator = new Comparator<Phieumuon>() {
            @Override
            public int compare(Phieumuon pm1, Phieumuon pm2) {
                // Lấy tên sách tương ứng với mã sách từ bảng sách
                Sach sach1 = sachDao.getID(String.valueOf(pm1.getMasach()));
                Sach sach2 = sachDao.getID(String.valueOf(pm2.getMasach()));

                return sach1.getTenSach().compareTo(sach2.getTenSach());
            }
        };

        // Sắp xếp danh sách phiếu mượn
        Collections.sort(lista, comparator);

        // Cập nhật RecyclerView hoặc danh sách đã sắp xếp
        phieumuonn.notifyDataSetChanged();
    }


    public void showaddpm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_thempm, null);

        // Khởi tạo các thành phần giao diện
        Spinner spnts = view.findViewById(R.id.spinertensach);
        Spinner spnttv = view.findViewById(R.id.spinertentv);
        TextView txt = view.findViewById(R.id.txtngay);
        TextView txttt = view.findViewById(R.id.edittienthuepm);
        CheckBox cbk = view.findViewById(R.id.cbktt);
        Button btnadd = view.findViewById(R.id.btntpm);
        Button btnhuy = view.findViewById(R.id.btnhuypm);

        // Thiết lập giao diện
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Khởi tạo các đối tượng DAO và danh sách
        sachDao = new sachDAO(getContext());
        thanhVienDao = new thanhvienDAO(getContext());
        sachList = (ArrayList<Sach>) sachDao.getAll();
        vienList = (ArrayList<Thanhvien>) thanhVienDao.getAll();

        // Khởi tạo các Adapter và gán cho Spinner
        ArrayAdapter<Sach> adapter_sach = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, sachList);
        ArrayAdapter<Thanhvien> adapter_tv = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, vienList);
        spnts.setAdapter(adapter_sach);
        spnttv.setAdapter(adapter_tv);

        // Thiết lập sự kiện khi mục được chọn trong Spinner spnttv
        spnttv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt.setText("Ngày thuê: " + date);
                matv=vienList.get(position).getMatv();

                Toast.makeText(getContext(), "Chọn: " + vienList.get(position).getHoten(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Thiết lập sự kiện khi mục được chọn trong Spinner spnts
        spnts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txttt.setText("Giá: " + sachList.get(position).getGiaThue());
                mas=sachList.get(position).getMaSach();
                tien=sachList.get(position).getGiaThue();
                Toast.makeText(getContext(), "Chọn: " + sachList.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Thiết lập sự kiện khi nhấn nút "Thêm"
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy các đối tượng được chọn trong Spinner
                Thanhvien vien = (Thanhvien) spnttv.getSelectedItem();
                Sach sach = (Sach) spnts.getSelectedItem();

                // Tạo đối tượng Phieumuon và gán các giá trị
                Phieumuon phieuMuon = new Phieumuon();
                phieuMuon.setMasach(sach.getMaSach());
                phieuMuon.setMatv(vien.getMatv());
                phieuMuon.setNgay(java.sql.Date.valueOf(String.valueOf(date)));

                // Gán giá trị Trasach dựa trên trạng thái CheckBox
                if (cbk.isChecked()) {
                    phieuMuon.setTrasach(1);

                } else {
                    phieuMuon.setTrasach(0);

                }

                // Lấy thông tin giá thuê từ đối tượng Sach
                Sach sach1 = sachDao.getID(phieuMuon.getMasach() + "");
                phieuMuon.setTienthue(sach1.getGiaThue());

                // Thêm phiếu mượn vào cơ sở dữ liệu
                if (dao.insert(phieuMuon) > 0) {
                    Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
                    lista.clear();
                    lista.addAll(dao.getAll());
                    phieumuonn.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });

        // Thiết lập sự kiện khi nhấn nút "Hủy"
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }




}




















