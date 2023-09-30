package hieunnph32561.fpoly.du_an_mau_ph32561.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    Thanhvien thanhvien;
    Sach sach;

    public adapter_phieumuon(Context context, ArrayList<Phieumuon> list, hieunnph32561.fpoly.du_an_mau_ph32561.dao.phieumuonDAO phieumuonDAO) {
        this.context = context;
        this.list = list;
        this.phieumuonDAO = phieumuonDAO;
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
        dao = new thanhvienDAO(context.getApplicationContext());
        daoo=new sachDAO(context.getApplicationContext());

        Phieumuon phieumuon=list.get(position);

        thanhvien=dao.getID(phieumuon.getMatv());
        sach=daoo.getID(String.valueOf(phieumuon.getMasach()));


        holder.txtmaphieu.setText(String.valueOf(list.get(position).getMapm()));
        holder.txtthanhvien.setText(String.valueOf(thanhvien.getHoten()));
        holder.txttensach.setText(String.valueOf(sach.getTenSach()));
        holder.txttienthue.setText(String.valueOf(list.get(position).getTienthue()));
        holder.txtngaythue.setText(list.get(position).getNgay()); // Ngày mượn
        holder.txttrangthai.setText(String.valueOf(list.get(position).getTrasach()));
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
