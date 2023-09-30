package hieunnph32561.fpoly.du_an_mau_ph32561.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.thanhvienDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class adapter_thanhvien extends RecyclerView.Adapter<adapter_thanhvien.ViewHolder> {
    private Context context;
    private ArrayList<Thanhvien> list;

    thanhvienDAO dao;

    public adapter_thanhvien(Context context, ArrayList<Thanhvien> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        dao=new thanhvienDAO(context.getApplicationContext());

        Thanhvien thanhVien = list.get(position);

        holder.tvMaTV.setText("Mã thành viên: " + thanhVien.getMatv());
        holder.tvHoTen.setText("Họ tên: " + thanhVien.getHoten());
        holder.tvNamSinh.setText("Năm sinh: " + thanhVien.getNamsinh());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setTitle("Bạn có muốn xóa không")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (dao.delete(thanhVien.getMatv())>0) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(dao.getAll());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaTV, tvHoTen, tvNamSinh;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaTV = itemView.findViewById(R.id.tv_maTV);
            tvHoTen = itemView.findViewById(R.id.tv_hotenTV);
            tvNamSinh = itemView.findViewById(R.id.tv_namsinh);
            imageView=itemView.findViewById(R.id.img_delete_thanhvien);
        }
    }
}
