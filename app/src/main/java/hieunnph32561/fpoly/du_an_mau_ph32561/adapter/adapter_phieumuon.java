package hieunnph32561.fpoly.du_an_mau_ph32561.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.phieumuonDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.sachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.thanhvienDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Phieumuon;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class adapter_phieumuon extends RecyclerView.Adapter<adapter_phieumuon.Viewhodelpm> {

    private Context context;
    private ArrayList<Phieumuon> list;


    phieumuonDAO phieumuonDAO;
    thanhvienDAO dao;
    sachDAO daoo;

    Sach sach;
    Thanhvien thanhvien;

    private PhieuMuonClick phieuMuonClick;


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



    public adapter_phieumuon(Context context, ArrayList<Phieumuon> list, hieunnph32561.fpoly.du_an_mau_ph32561.dao.phieumuonDAO phieumuonDAO) {
        this.context = context;
        this.list = list;
        this.phieumuonDAO = phieumuonDAO;
    }

    public void setPhieuMuonClick(PhieuMuonClick phieuMuonClick) {
        this.phieuMuonClick = phieuMuonClick;
    }

    @NonNull
    @Override
    public adapter_phieumuon.Viewhodelpm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phieumuon, parent, false);
        return new Viewhodelpm(view);
    }

    // Trong adapter_phieumuon.java


    @Override
    public void onBindViewHolder(@NonNull adapter_phieumuon.Viewhodelpm holder, int position) {
        // Tạo đối tượng thanhvienDAO để tương tác với bảng thành viên trong cơ sở dữ liệu
        dao = new thanhvienDAO(context.getApplicationContext());

// Tạo đối tượng sachDAO để tương tác với bảng sách trong cơ sở dữ liệu
        daoo = new sachDAO(context.getApplicationContext());

// Lấy đối tượng Phieumuon từ danh sách tại vị trí (position) cụ thể
        Phieumuon phieumuon = list.get(position);


// Lấy thông tin thành viên (thanhvien) dựa trên mã thành viên (MATV) của phiếu mượn
        thanhvien = dao.getID(String.valueOf(phieumuon.getMatv()));
        sach = daoo.getID(String.valueOf(phieumuon.getMasach()));

        holder.txtmaphieu.setText(String.valueOf(list.get(position).getMapm()));
        holder.txtthanhvien.setText(String.valueOf(thanhvien.getHoten()));
        holder.txttensach.setText(String.valueOf(sach.getTenSach()));
        holder.txttienthue.setText(String.valueOf(sach.getGiaThue()));
        holder.txtngaythue.setText(sdf.format(phieumuon.getNgay()));
        int trangthai = list.get(position).getTrasach();

// Kiểm tra và hiển thị trạng thái dựa trên giá trị trangthai: 1 - đã trả, 0 - chưa trả
        if (trangthai == 1) {
            holder.txttrangthai.setText("Đã trả"); // Hiển thị "Đã trả" nếu trạng thái là 1
        } else {
            holder.txttrangthai.setText("Chưa trả");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phieuMuonClick.onClick(list.get(position));
            }
        });
        holder.imvdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setTitle("Bạn có muốn xóa không")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (phieumuonDAO.delete(String.valueOf(phieumuon.getMapm())) > 0) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(phieumuonDAO.getAll());
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("CANNEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewhodelpm extends RecyclerView.ViewHolder {

        TextView txtmaphieu, txtthanhvien, txttensach, txttienthue, txtngaythue, txttrangthai;
        ImageView imvdl;

        public Viewhodelpm(@NonNull View itemView) {
            super(itemView);

            txtmaphieu = itemView.findViewById(R.id.txtmaphieu);
            txtthanhvien = itemView.findViewById(R.id.txtthanhvien);
            txttensach = itemView.findViewById(R.id.txttensach);
            txttienthue = itemView.findViewById(R.id.txttienthue);
            txtngaythue = itemView.findViewById(R.id.txtngaythue);
            txttrangthai = itemView.findViewById(R.id.txttrangthai);
            imvdl = itemView.findViewById(R.id.imvdlpm);


        }
    }
}