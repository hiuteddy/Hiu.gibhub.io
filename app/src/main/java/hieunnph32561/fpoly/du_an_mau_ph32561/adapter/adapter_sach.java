package hieunnph32561.fpoly.du_an_mau_ph32561.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class adapter_sach extends RecyclerView.Adapter<adapter_sach.ViewHodelsanpham> {

    private Context context;
    private ArrayList<Sach> list;
    private sachDAO dao;
    private Loaisach loaisach;
    private loaisachDAO daoo;
    public adapter_sach(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
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
        dao = new sachDAO(context);
        daoo = new loaisachDAO(context);

// Lấy thông tin thể loại sách từ bảng Loaisach dựa trên khóa ngoại maLoai
        loaisach = daoo.getID(String.valueOf(sach.getMaLoai()));

        holder.Masach.setText("Mã sách: " + sach.getMaSach());
        holder.tensach.setText("Tên sách: " + sach.getTenSach());
        holder.theloai.setText("Loại sách: " + loaisach.getTenLoai()); // Lấy tên thể loại sách từ đối tượng Loaisach
        holder.giasach.setText("Giá thuê: " + sach.getGiaThue());


        holder.txtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                // Lấy đối tượng khóa học tương ứng
                Sach sp = list.get(position);
                // Xây dựng hộp thoại xác nhận xóa
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xóa khóa học khỏi cơ sở dữ liệu
                        dao = new sachDAO(context);
                        long result = dao.delete(sp.getMaSach());
                        if (result > 0) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            list.remove(position); // Xóa đối tượng khóa học khỏi danh sách
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


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHodelsanpham extends RecyclerView.ViewHolder {
        TextView Masach, tensach, giasach, theloai;
        ImageView  txtdelete;
        Spinner spinner;

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


