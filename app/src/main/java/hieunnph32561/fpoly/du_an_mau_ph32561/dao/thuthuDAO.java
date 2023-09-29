package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;

public class thuthuDAO {
    private Dbhelper dbhelper;


    public thuthuDAO(Context context) {
        this.dbhelper = new Dbhelper(context);

    }

    public boolean login(String username, String password) {
        SQLiteDatabase database = dbhelper.getReadableDatabase(); // thêm this.dBhelper

        // Chuỗi câu lệnh SQL để truy vấn người dùng dựa trên tên đăng nhập và mật khẩu
        String query = "SELECT * FROM THUTHU WHERE MATT = ? AND MATKHAU = ?";

        // Thực thi câu lệnh SQL với tham số truyền vào
        Cursor cursor = database.rawQuery(query, new String[]{username, password});

        // Kiểm tra xem có dữ liệu trả về từ truy vấn không
        boolean loggedIn = cursor.moveToFirst();

        // Đóng con trỏ Cursor sau khi sử dụng
        cursor.close();

        // Trả về kết quả đăng nhập
        return loggedIn;
    }
}