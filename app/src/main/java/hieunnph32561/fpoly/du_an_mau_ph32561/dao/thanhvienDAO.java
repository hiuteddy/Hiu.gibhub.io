package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
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
        Cursor cursor = database.rawQuery("SELECT * FROM THANHVIEN", null);

        while (cursor.moveToNext()) {
            Thanhvien tv = new Thanhvien(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            list.add(tv); // Thêm thành viên vào danh sách
        }
        cursor.close(); // Đóng con trỏ khi hoàn thành công việc
        return list; // Trả về danh sách thành viên
    }

    // Phương thức để thêm một thành viên vào cơ sở dữ liệu
    public long add(Thanhvien tv) {
        SQLiteDatabase database = dBhelper.getWritableDatabase(); // Khởi tạo cơ sở dữ liệu ghi
        ContentValues values = new ContentValues();
        values.put("MATV", tv.getMatv());
        values.put("HOTEN", tv.getHoten());
        values.put("NAMSINH", tv.getNamsinh());
        return database.insert("THANHVIEN", null, values); // Trả về ID của hàng được chèn
    }

    // Phương thức để cập nhật thông tin một thành viên trong cơ sở dữ liệu
    public long udt(Thanhvien tv) {
        SQLiteDatabase database = dBhelper.getWritableDatabase(); // Khởi tạo cơ sở dữ liệu ghi
        ContentValues values = new ContentValues();
        values.put("MATV", tv.getMatv());
        values.put("HOTEN", tv.getHoten());
        values.put("NAMSINH", tv.getNamsinh());
        return database.update("THANHVIEN", values, "MATV=?", new String[]{
                String.valueOf(tv.getMatv())
        });
    }

    // Phương thức để xóa một thành viên khỏi cơ sở dữ liệu
    public long delete(String mtv) {
        SQLiteDatabase database = dBhelper.getWritableDatabase(); // Khởi tạo cơ sở dữ liệu ghi
        long check = database.delete("THANHVIEN", "MATV=?", new String[]{
                String.valueOf(mtv)
        });
        return check; // Trả về số hàng bị xóa
    }

    // Phương thức để lấy danh sách tất cả thành viên từ cơ sở dữ liệu (được đơn giản hóa)
    public ArrayList<Thanhvien> getAll() {
        String sql = "SELECT * FROM THANHVIEN";
        return (ArrayList<Thanhvien>) getALLTV(sql); // Gọi getALLTV với một truy vấn SQL đã được định nghĩa trước
    }

    // Phương thức để lấy một thành viên cụ thể bằng ID từ cơ sở dữ liệu
    public Thanhvien getID(String id) {
        String sql = "SELECT * FROM THANHVIEN WHERE MATV=?";
        List<Thanhvien> list = getALLTV(sql, id);
        return list.get(0); // Trả về thành viên đầu tiên trong danh sách (nên chỉ có một thành viên)
    }
}
