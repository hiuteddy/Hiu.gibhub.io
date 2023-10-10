package hieunnph32561.fpoly.du_an_mau_ph32561.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.R;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.loaisachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.dao.sachDAO;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class adapter_sach extends RecyclerView.Adapter<adapter_sach.ViewHodelsanpham> {

     Context context;
     ArrayList<Sach> list;
     sachDAO dao;
    loaisachDAO daoo;
    Loaisach loaisach;
    ArrayList<Loaisach> listLS = new ArrayList<>();



    public adapter_sach(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
        dao = new sachDAO(context);
        daoo = new loaisachDAO(context);

    }

    @NonNull
    @Override
    public ViewHodelsanpham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.item_sach, parent, false);
        return new ViewHodelsanpham(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodelsanpham holder, @SuppressLint("RecyclerView") int position) {
        Sach sach = list.get(position);

// Lấy thông tin thể loại sách từ bảng Loaisach dựa trên khóa ngoại maLoai
        loaisach = daoo.getID(String.valueOf(sach.getMaLoai()));

        holder.Masach.setText("" + sach.getMaSach());
        holder.tensach.setText("" + sach.getTenSach());
        holder.theloai.setText( loaisach.getTenLoai()); // Lấy tên thể loại sách từ đối tượng Loaisach
        holder.giasach.setText("" + sach.getGiaThue());


        holder.txtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                // Lấy đối tượng khóa học tương ứng
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xóa khóa học khỏi cơ sở dữ liệu
                        if (dao.delete(sach.getMaSach()) > 0) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(dao.getAll());
                            notifyDataSetChanged(); // Cập nhật lại dữ liệu trên RecyclerView
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hủy thao tác xóa
                        dialog.dismiss();
                    }
                });
                // Hiển thị hộp thoại xác nhận xóa
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_updates, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();

                EditText edtTenSach = view1.findViewById(R.id.edittsu);
                EditText edtGia = view1.findViewById(R.id.editgiasachu);
                Spinner spnLoaiSach = view1.findViewById(R.id.spineru);
                Button btnXacnhan = view1.findViewById(R.id.buttonAddsachu);
                Button btnHuy = view1.findViewById(R.id.buttonhuysachu);

                listLS = daoo.getAll();
                ArrayAdapter arrayAdapte = new ArrayAdapter(context, android.R.layout.simple_list_item_1, listLS);
                spnLoaiSach.setAdapter(arrayAdapte);

                edtTenSach.setText(list.get(position).getTenSach());
                edtGia.setText(String.valueOf(list.get(position).getGiaThue()));


                btnXacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Sach sach = list.get(position);


                        Loaisach lsach = (Loaisach) spnLoaiSach.getSelectedItem();
                        sach.setMaLoai(lsach.getMaLoai());
                        sach.setTenSach(edtTenSach.getText().toString());
                        sach.setGiaThue(Integer.parseInt((edtGia.getText().toString())));

                        // Thêm phiếu mượn vào cơ sở dữ liệu
                        if (dao.upate(sach) > 0) {
                            Toast.makeText(context, "Update sách thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(dao.getAll());
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "update sách thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHodelsanpham extends RecyclerView.ViewHolder {
        TextView Masach, tensach, giasach, theloai;
        ImageView  txtdelete;

        public ViewHodelsanpham(@NonNull View itemView) {
            super(itemView);
            Masach = itemView.findViewById(R.id.txtmasach);
            tensach = itemView.findViewById(R.id.txttensach);
            giasach = itemView.findViewById(R.id.txtgiasach);
            theloai = itemView.findViewById(R.id.txttheloai);
            txtdelete = itemView.findViewById(R.id.imageViewdl);


        }
    }

}