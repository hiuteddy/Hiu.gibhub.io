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



    public long insert(Loaisach s){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("THELOAI",s.getTenLoai());
        return database.insert("LOAISACH",null,values);
    }

    public long delete(int mssp){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        long check=database.delete("LOAISACH","MALOAI=?",new String[]{
                String.valueOf(mssp)
        });
        return  check;
    }


    public ArrayList<Loaisach> getALLSACH(String sql, String... selectionArgs) {
        ArrayList<Loaisach> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng LOAISACH
        Cursor cursor = database.rawQuery("SELECT * FROM LOAISACH", null);

        // Duyệt qua dữ liệu trả về từ truy vấn và tạo đối tượng Loaisach tương ứng
        while (cursor.moveToNext()) {
            // Lấy giá trị của cột MALOAI (cột 0)
            int maloai = cursor.getInt(0);
            String tenloai = cursor.getString(1);

            // Tạo đối tượng Loaisach từ dữ liệu trên
            Loaisach ls = new Loaisach(maloai, tenloai);

            // Thêm đối tượng Loaisach vào danh sách
            list.add(ls);
        }
        // Đóng con trỏ Cursor để giải phóng tài nguyên
        cursor.close();
        return list;
    }

    // Lấy danh sách tất cả các loại sách từ cơ sở dữ liệu
    public List<Loaisach> getAll() {
        // Chuỗi SQL truy vấn để lấy tất cả dữ liệu từ bảng LOAISACH
        String sql = "SELECT * FROM LOAISACH";
        return getALLSACH(sql);
    }

    // Lấy thông tin của một loại sách dựa trên mã loại
    public Loaisach getID(String id) {
        // Chuỗi SQL truy vấn để lấy dữ liệu của một loại sách dựa trên MALOAI
        String sql = "SELECT * FROM LOAISACH WHERE MALOAI=?";
        // Gọi hàm getALLSACH với tham số truyền vào là MALOAI cần tìm
        List<Loaisach> list = getALLSACH(sql, id);
        // Lấy phần tử đầu tiên (nếu có) từ danh sách kết quả
        return list.get(0);
    }

}