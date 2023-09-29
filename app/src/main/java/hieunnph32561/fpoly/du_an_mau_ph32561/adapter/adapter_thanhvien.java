package hieunnph32561.fpoly.du_an_mau_ph32561.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class adapter_thanhvien extends RecyclerView.Adapter<adapter_thanhvien.ViewHolder> {
    private Context context;
    private ArrayList<Thanhvien> list;

    public adapter_thanhvien(Context context, ArrayList<Thanhvien> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialogtv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Thanhvien thanhVien = list.get(position);

       holder.tvMaTV.setText("Mã thành viên: " + thanhVien.getMatv());
        holder.tvHoTen.setText("Họ tên: " + thanhVien.getHoten());
        holder.tvNamSinh.setText("Năm sinh: " + thanhVien.getNamsinh());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaTV, tvHoTen, tvNamSinh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaTV = itemView.findViewById(R.id.tv_maTV);
            tvHoTen = itemView.findViewById(R.id.tv_hotenTV);
            tvNamSinh = itemView.findViewById(R.id.tv_namsinh);
        }
    }
}
