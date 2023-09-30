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

    public sachDAO(Context context) {
        dBhelper = new Dbhelper(context);
    }
        public long insert(Sach s){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TENSACH",s.getTenSach());
        values.put("GIATHUE",s.getGiaThue());
        values.put("THELOAI",s.getMaLoai());
        return database.insert("SACH",null,values);
    }
    public int upate(Sach s) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", s.getTenSach());
        values.put("GIATHUE", s.getGiaThue());
        values.put("THELOAI", s.getMaLoai());
        return database.update("SACH", values, "MASACH=?", new String[]{s.getMaSach() + ""});
    }
    public long delete(int mssp){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        long check=database.delete("SACH","MASACH=?",new String[]{
                String.valueOf(mssp)
        });
        return  check;
    }
    // Phương thức này trả về danh sách tất cả sách trong bảng SACH từ cơ sở dữ liệu
    public ArrayList<Sach> getALLSACH(String sql, String... selectionArgs) {
        ArrayList<Sach> list = new ArrayList<>();

        // Mở cơ sở dữ liệu để đọc
        SQLiteDatabase database = dBhelper.getReadableDatabase();

        // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng SACH
        Cursor cursor = database.rawQuery("SELECT * FROM SACH", null);

        // Duyệt qua dữ liệu trả về từ truy vấn và tạo đối tượng Sach tương ứng
        while (cursor.moveToNext()) {
            // Lấy giá trị của các cột trong bảng SACH
            int maSach = cursor.getInt(0);
            String tenSach = cursor.getString(1);
            int giaThue = cursor.getInt(2);
            int maLoai = cursor.getInt(3);

            // Tạo đối tượng Sach từ dữ liệu trên
            Sach sach = new Sach(maSach, tenSach, giaThue, maLoai);

            // Thêm đối tượng Sach vào danh sách
            list.add(sach);
        }

        // Đóng con trỏ Cursor để giải phóng tài nguyên
        cursor.close();

        return list;
    }

    // Lấy danh sách tất cả sách từ cơ sở dữ liệu
    public ArrayList<Sach> getAll() {
        String sql = "SELECT * FROM SACH";
        return (ArrayList<Sach>) getALLSACH(sql);
    }

    // Lấy thông tin của một cuốn sách dựa trên mã sách (MASACH)
    public Sach getID(String id) {
        String sql = "SELECT * FROM SACH WHERE MASACH=?";

        // Gọi hàm getALLSACH với tham số truyền vào là MASACH cần tìm
        List<Sach> list = getALLSACH(sql, id);

        // Lấy phần tử đầu tiên (nếu có) từ danh sách kết quả
        return list.get(0);
    }


}














