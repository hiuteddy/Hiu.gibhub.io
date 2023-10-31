package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Top;


public class thongkeDAO {
    private Context context;


    private Dbhelper dbhelper;

    public thongkeDAO(Context context) {
        dbhelper = new Dbhelper(context);
        this.context=context;
    }

    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = "select maSach, count(maSach) as soLuong from PHIEUMUON group by maSach order by soLuong desc limit 10";
        List<Top> list = new ArrayList<Top>();
        sachDAO sachDao = new sachDAO(context);
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(sqlTop, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Top top = new Top();
            Sach sach = sachDao.getID(cursor.getString(cursor.getColumnIndex("maSach")));
            top.tensach = sach.getTenSach();
            top.soluong = Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong")));

            list.add(top);
        }

        // Đóng con trỏ Cursor để giải phóng tài nguyên
        cursor.close();

        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanThu = "SELECT SUM(TIENTHUE) AS doanhThu FROM PHIEUMUON WHERE ngay BETWEEN ? AND ?";
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(sqlDoanThu, new String[]{tuNgay, denNgay});

        int doanhThu = 0; // Khởi tạo tổng doanh thu

        if (cursor.moveToFirst()) {
            do {
                // Lấy giá trị tổng doanh thu từ Cursor
                doanhThu = cursor.getInt(cursor.getColumnIndex("doanhThu"));
            } while (cursor.moveToNext());
        }

        // Đóng con trỏ Cursor và tài nguyên cơ sở dữ liệu
        cursor.close();
        database.close();

        return doanhThu;
    }


}
