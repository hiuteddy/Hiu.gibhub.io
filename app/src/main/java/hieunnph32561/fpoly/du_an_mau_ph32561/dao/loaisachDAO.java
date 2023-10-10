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
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Phieumuon;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;

public class loaisachDAO {
    private Dbhelper dBhelper;

    public loaisachDAO(Context context) {
        dBhelper = new Dbhelper(context);
    }


    public long insert(Loaisach s) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("THELOAI", s.getTenLoai());
        return database.insert("LOAISACH", null, values);
    }

    public long delete(int mssp) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        long check = database.delete("LOAISACH", "MALOAI=?", new String[]{
                String.valueOf(mssp)
        });
        return check;
    }

    public long update(Loaisach s) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("THELOAI", s.getTenLoai());
        return database.update("LOAISACH", values, "MALOAI=?", new String[]{
                String.valueOf(s.getMaLoai())
        });
    }

        public ArrayList<Loaisach> getALLSACH(String sql, String... selectionArgs) {
        ArrayList<Loaisach> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase();

        // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng LOAISACH
        Cursor cursor = database.rawQuery(sql, selectionArgs);

        // Duyệt qua dữ liệu trả về từ truy vấn và tạo đối tượng Loaisach tương ứng
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Loaisach ls = new Loaisach(
                    cursor.getInt(cursor.getColumnIndex("MALOAI")), // Lấy cột MALOAI
                    cursor.getString(cursor.getColumnIndex("THELOAI")) // Lấy cột THELOAI
            );
            // Thêm đối tượng Loaisach vào danh sách
            list.add(ls);
        }

        // Đóng con trỏ Cursor để giải phóng tài nguyên
        cursor.close();

        return list;
    }


    // Lấy danh sách tất cả các loại sách từ cơ sở dữ liệu
    public ArrayList<Loaisach> getAll() {
        // Chuỗi SQL truy vấn để lấy tất cả dữ liệu từ bảng LOAISACH
        String sql = "SELECT * FROM LOAISACH";
        return getALLSACH(sql);
    }

    // Lấy thông tin của một loại sách dựa trên mã loại
    public Loaisach getID(String id) {
        String sql = "select * from LOAISACH where MALOAI=?";
        ArrayList<Loaisach> list = getALLSACH(sql, id);

//        if (!list.isEmpty()) {
            return list.get(0);
//       } else {
//           // Trả về một giá trị LoaiSach mặc định hoặc tạo một đối tượng mới tùy ý
//           return new Loaisach();
//       }
    }


}