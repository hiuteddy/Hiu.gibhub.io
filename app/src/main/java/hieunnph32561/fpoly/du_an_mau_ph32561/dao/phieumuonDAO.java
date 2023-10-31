package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Phieumuon;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;

public class phieumuonDAO {
    private Dbhelper dbhelper;

    public phieumuonDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public ArrayList<Phieumuon> getALLPM(String sql, String... selectionArgs) {
        ArrayList<Phieumuon> list = new ArrayList<>();
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, selectionArgs);

        while (cursor.moveToNext()) {
            try {
                @SuppressLint("Range") Date ngay = sdf.parse(cursor.getString(cursor.getColumnIndex("ngay")));

                @SuppressLint("Range") Phieumuon pm = new Phieumuon(
                        cursor.getInt(cursor.getColumnIndex("maPM")),
                        cursor.getInt(cursor.getColumnIndex("maTV")),
                        cursor.getString(cursor.getColumnIndex("maTT")),
                        cursor.getInt(cursor.getColumnIndex("maSach")),
                        ngay,
                        cursor.getInt(cursor.getColumnIndex("traSach")),
                        cursor.getInt(cursor.getColumnIndex("tienThue"))
                );

                list.add(pm);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return list;
    }


    public ArrayList<Phieumuon> getAll() {
        String sql = "SELECT * FROM PHIEUMUON";
        return (ArrayList<Phieumuon>) getALLPM(sql);
    }


    public long insert(Phieumuon phieuMuon) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTV", phieuMuon.getMatv());
        contentValues.put("maTT", phieuMuon.getMatt());
        contentValues.put("maSach", phieuMuon.getMasach());
        contentValues.put("ngay", sdf.format(phieuMuon.getNgay()));
        contentValues.put("traSach", phieuMuon.getTrasach());
        contentValues.put("tienThue", phieuMuon.getTienthue());

        return sqLiteDatabase.insert("PHIEUMUON", null, contentValues);
    }

    public int update(Phieumuon phieuMuon) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTV", phieuMuon.getMatv());
        contentValues.put("maTT", phieuMuon.getMatt());
        contentValues.put("maSach", phieuMuon.getMasach());
        contentValues.put("ngay", sdf.format(phieuMuon.getNgay()));
        contentValues.put("traSach", phieuMuon.getTrasach());
        contentValues.put("tienThue", phieuMuon.getTienthue());

        return sqLiteDatabase.update("PHIEUMUON", contentValues, "maPM=?", new String[]{phieuMuon.getMapm() + ""});

    }

    public long delete(String mpm) {
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        long check = database.delete("PHIEUMUON", "maPM=?", new String[]{mpm});
        return check;
    }


}
