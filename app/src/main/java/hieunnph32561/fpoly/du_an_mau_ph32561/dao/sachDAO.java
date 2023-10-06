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
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class sachDAO {

    private Dbhelper dBhelper;

    // Constructor để khởi tạo trợ giúp cơ sở dữ liệu
    public sachDAO(Context context) {
        dBhelper = new Dbhelper(context);
    }

    // Phương thức để chèn một bản ghi sách vào cơ sở dữ liệu
    public long insert(Sach s) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", s.getTenSach());
        values.put("GIATHUE", s.getGiaThue());
        values.put("MALOAI", s.getMaLoai());
        return database.insert("SACH", null, values); // Trả về ID của hàng được chèn
    }

    // Phương thức để cập nhật một bản ghi sách trong cơ sở dữ liệu
    public int upate(Sach s) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", s.getTenSach());
        values.put("GIATHUE", s.getGiaThue());
        values.put("MALOAI", s.getMaLoai());
        return database.update("SACH", values, "MASACH=?", new String[]{s.getMaSach() + ""});
    }

    // Phương thức để xóa một bản ghi sách khỏi cơ sở dữ liệu
    public long delete(int mssp) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        long check = database.delete("SACH", "MASACH=?", new String[]{String.valueOf(mssp)});
        return check; // Trả về số hàng bị xóa
    }

    // Phương thức để lấy danh sách tất cả sách từ cơ sở dữ liệu
    public ArrayList<Sach> getALLSACH(String sql, String... selectionArgs) {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, selectionArgs);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Sach s = new Sach(
                    cursor.getInt(cursor.getColumnIndex("MASACH")),
                    cursor.getString(cursor.getColumnIndex("TENSACH")),
                    cursor.getInt(cursor.getColumnIndex("GIATHUE")),
                    cursor.getInt(cursor.getColumnIndex("MALOAI"))
                    );
            list.add(s);
        }
        cursor.close(); // Đóng con trỏ khi hoàn thành công việc
        return list; //
    }

    // Phương thức để lấy danh sách tất cả sách từ cơ sở dữ liệu (được đơn giản hóa)
    public ArrayList<Sach> getAll() {
        String sql = "SELECT * FROM SACH";
        return (ArrayList<Sach>) getALLSACH(sql); // Gọi getALLSACH với một truy vấn SQL đã được định nghĩa trước
    }

    // Phương thức để lấy một cuốn sách cụ thể bằng ID từ cơ sở dữ liệu
    public Sach getID(String id){
        String sql = "select * from SACH where MASACH=?";
        ArrayList<Sach> list = getALLSACH(sql, id);

        if (!list.isEmpty()) {
            return list.get(0);
        }
        else {
            // Trả về một giá trị LoaiSach mặc định hoặc tạo một đối tượng mới tùy ý
            return new Sach();
        }
    }
}
