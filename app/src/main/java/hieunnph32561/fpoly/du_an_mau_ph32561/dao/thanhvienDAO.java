package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class thanhvienDAO {

    private Dbhelper dBhelper;

    // Constructor để khởi tạo trợ giúp cơ sở dữ liệu
    public thanhvienDAO(Context context) {
        dBhelper = new Dbhelper(context);
    }

    // Phương thức để lấy danh sách tất cả thành viên từ cơ sở dữ liệu
    public ArrayList<Thanhvien> getALLTV(String sql, String... selectionArgs) {
        ArrayList<Thanhvien> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase(); // Khởi tạo cơ sở dữ liệu đọc

        Cursor cursor = database.rawQuery(sql, selectionArgs);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Thanhvien tv = new Thanhvien(
                    cursor.getInt(cursor.getColumnIndex("maTV")),
                    cursor.getString(cursor.getColumnIndex("hoTen")),
                    cursor.getString(cursor.getColumnIndex("namSinh"))
            );
            list.add(tv);
        }
        cursor.close(); // Đóng con trỏ khi hoàn thành công việc
        return list; // Trả về danh sách thành viên
    }

    public long add(Thanhvien tv) {
        SQLiteDatabase database = dBhelper.getWritableDatabase(); // Khởi tạo cơ sở dữ liệu ghi
        ContentValues values = new ContentValues();
        values.put("hoTen,", tv.getHoten());
        values.put("namSinh", tv.getNamsinh());
        return database.insert("THANHVIEN", null, values); // Trả về ID của hàng được chèn
    }

    public long udt(Thanhvien tv) {
        SQLiteDatabase database = dBhelper.getWritableDatabase(); // Khởi tạo cơ sở dữ liệu ghi
        ContentValues values = new ContentValues();
        values.put("maTV", tv.getMatv());
        values.put("hoTen,", tv.getHoten());
        values.put("namSinh", tv.getNamsinh());
        return database.update("THANHVIEN", values, "maTV=?", new String[]{
                String.valueOf(tv.getMatv())
        });
    }

    // Phương thức để xóa một thành viên khỏi cơ sở dữ liệu
    public long delete(int mtv) {
        SQLiteDatabase database = dBhelper.getWritableDatabase(); // Khởi tạo cơ sở dữ liệu ghi
        long check = database.delete("THANHVIEN", "maTV=?", new String[]{
                String.valueOf(mtv)
        });
        return check; // Trả về số hàng bị xóa
    }

    // Phương thức để lấy danh sách tất cả thành viên từ cơ sở dữ liệu (được đơn giản hóa)
    public ArrayList<Thanhvien> getAll() {
        // Chuỗi SQL truy vấn để lấy tất cả dữ liệu từ bảng LOAISACH
        String sql = "SELECT * FROM THANHVIEN";
        return getALLTV(sql);
    }

    // Lấy thông tin của một loại sách dựa trên mã loại
    public Thanhvien getID(String id) {
        String sql = "select * from THANHVIEN where MATV=?";
        ArrayList<Thanhvien> list = getALLTV(sql, id);

        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            // Trả về một giá trị LoaiSach mặc định hoặc tạo một đối tượng mới tùy ý
            return new Thanhvien();
        }
    }
}
