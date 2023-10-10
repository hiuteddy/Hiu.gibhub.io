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
                @SuppressLint("Range") Date ngay = sdf.parse(cursor.getString(cursor.getColumnIndex("NGAY")));

           @SuppressLint("Range") Phieumuon pm = new Phieumuon(
                        cursor.getInt(cursor.getColumnIndex("MAPM")),
                        cursor.getInt(cursor.getColumnIndex("MATV")),
                        cursor.getString(cursor.getColumnIndex("MATT")),
                        cursor.getInt(cursor.getColumnIndex("MASACH")),
                        ngay,
                        cursor.getInt(cursor.getColumnIndex("TRASACH")),
                        cursor.getInt(cursor.getColumnIndex("TIENTHUE"))
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
        contentValues.put("MATV", phieuMuon.getMatv());
        contentValues.put("MATT", phieuMuon.getMatt());
        contentValues.put("MASACH", phieuMuon.getMasach());
        contentValues.put("NGAY", sdf.format(phieuMuon.getNgay()));
        contentValues.put("TRASACH", phieuMuon.getTrasach());
        contentValues.put("TIENTHUE", phieuMuon.getTienthue());

        return sqLiteDatabase.insert("PHIEUMUON", null, contentValues);
    }

    public int update(Phieumuon phieuMuon) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MATV", phieuMuon.getMatv());
        contentValues.put("MATT", phieuMuon.getMatt());
        contentValues.put("MASACH", phieuMuon.getMasach());
        contentValues.put("NGAY", sdf.format(phieuMuon.getNgay()));
        contentValues.put("TRASACH", phieuMuon.getTrasach());
        contentValues.put("TIENTHUE", phieuMuon.getTienthue());

        return sqLiteDatabase.update("PHIEUMUON", contentValues, "MAPM=?", new String[]{phieuMuon.getMapm() + ""});

    }

    public long delete(String mpm) {
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        long check = database.delete("PHIEUMUON", "MAPM=?", new String[]{mpm});
        return check;
    }


}
