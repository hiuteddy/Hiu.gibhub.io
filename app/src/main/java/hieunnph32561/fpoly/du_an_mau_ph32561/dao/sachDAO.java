package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;

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
        values.put("THELOAI", s.getMaLoai());
        return database.insert("SACH", null, values); // Trả về ID của hàng được chèn
    }

    // Phương thức để cập nhật một bản ghi sách trong cơ sở dữ liệu
    public int upate(Sach s) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", s.getTenSach());
        values.put("GIATHUE", s.getGiaThue());
        values.put("THELOAI", s.getMaLoai());
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
        Cursor cursor = database.rawQuery("SELECT * FROM SACH", null);

        while (cursor.moveToNext()) {
            Sach login = new Sach(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3));
            // Thêm sách vào danh sách
            list.add(login);
        }
        return list; // Trả về danh sách sách
    }

    // Phương thức để lấy danh sách tất cả sách từ cơ sở dữ liệu (được đơn giản hóa)
    public ArrayList<Sach> getAll() {
        String sql = "SELECT * FROM SACH";
        return (ArrayList<Sach>) getALLSACH(sql); // Gọi getALLSACH với một truy vấn SQL đã được định nghĩa trước
    }

    // Phương thức để lấy một cuốn sách cụ thể bằng ID từ cơ sở dữ liệu
    public Sach getID(String id) {
        String sql = "SELECT * FROM SACH WHERE MASACH=?";
        List<Sach> list = getALLSACH(sql, id);
        return list.get(0); // Trả về cuốn sách đầu tiên trong danh sách (nên chỉ có một cuốn)
    }
}
