package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thuthu;

public class thuthuDAO {
    private Dbhelper dBhelper;


    public thuthuDAO(Context context) {
        this.dBhelper = new Dbhelper(context);

    }

    public ArrayList<Thuthu> getALLTT(String sql, String... selectionArgs) {
        ArrayList<Thuthu> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, selectionArgs);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Thuthu tt = new Thuthu(
                    cursor.getString(cursor.getColumnIndex("MATT")),
                    cursor.getString(cursor.getColumnIndex("HOTEN")),
                    cursor.getString(cursor.getColumnIndex("MATKHAU"))
            );
            list.add(tt);
        }
        cursor.close(); // Đóng con trỏ khi hoàn thành công việc
        return list; //
    }
    public ArrayList<Thuthu> getAll() {
        String sql = "SELECT * FROM THUTHU";
        return (ArrayList<Thuthu>) getALLTT(sql);
    }


    public Thuthu getID(String id) {
        String sql = "select * from THUTHU where MATT=?";
        ArrayList<Thuthu> list = getALLTT(sql, id);

        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return new Thuthu();
        }
    }

    public long insert(Thuthu s) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MATT", s.getMatt());
        values.put("HOTEN", s.getHoten());
        values.put("MATKHAU", s.getMatkhau());
        return database.insert("THUTHU", null, values); // Trả về ID của hàng được chèn
    }

    public int upate(Thuthu s) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTEN", s.getHoten());
        values.put("MATKHAU", s.getMatkhau());
        return database.update("THUTHU", values, "MATT=?", new String[]{s.getMatt() + ""});
    }


    public int checkLogin(String id, String pass) {
        String sql = "select * from THUTHU where MATT=? and MATKHAU=?";
        List<Thuthu> list = getALLTT(sql, id, pass);
        if (list.size() == 0) {
            return -1;
        } else {
            return 1;
        }
    }
}