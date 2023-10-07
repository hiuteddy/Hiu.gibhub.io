package hieunnph32561.fpoly.du_an_mau_ph32561.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.loaisachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.sachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;


public class adapter_loaisach extends RecyclerView.Adapter<adapter_loaisach.ViewHolder> {

    private Context context;
    private ArrayList<Loaisach> list;
    loaisachDAO dao;

    public adapter_loaisach(Context context, ArrayList<Loaisach> list, loaisachDAO dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loaisach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Loaisach loaisach = list.get(position);

        holder.maloai.setText(String.valueOf(loaisach.getMaLoai())); // Đảm bảo rằng bạn truy cập thuộc tính đúng

        holder.tenloai.setText(loaisach.getTenLoai()); // Đảm bảo rằng bạn truy cập thuộc tính đúng
        holder.txtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setTitle("Bạn có muốn xóa không")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (dao.delete(loaisach.getMaLoai()) > 0) {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view1 = inflater.inflate(R.layout.dialog_updatels, null);

                EditText edtls = view1.findViewById(R.id.ed_tenloaisachu);
                Button btnsave = view1.findViewById(R.id.btn_save_loaisachu);
                Button btnhuy = view1.findViewById(R.id.btn_huy_loaisachu);

                edtls.setText(list.get(position).getTenLoai());


                builder.setView(view1);
                AlertDialog dialog = builder.create();

                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ls = edtls.getText().toString();

                        loaisach.setTenLoai(ls);

                        long updateResult = dao.update(loaisach);

                        if (updateResult > 0) {
                            // If the update was successful, refresh the list and notify the adapter
                            list.clear();
                            list.addAll(dao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            // If the update failed, display an error message
                            Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                        }

                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });


                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView maloai, tenloai, giasach, Masach, theloai;
        ImageView txtdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maloai = itemView.findViewById(R.id.txtmaloai); // Đảm bảo id là đúng
            tenloai = itemView.findViewById(R.id.txttenloai); // Đảm bảo id là đúng
            giasach = itemView.findViewById(R.id.txtgiasach); // Đảm bảo id là đúng
            Masach = itemView.findViewById(R.id.txtmasach); // Đảm bảo id là đúng
            theloai = itemView.findViewById(R.id.txttheloai); // Đảm bảo id là đúng
            txtdelete = itemView.findViewById(R.id.imageViewdl); // Đảm bảo id là đúng
        }
    }
}