package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Phieumuon;

public class phieumuonDAO {
    private Dbhelper dbhelper;

    public phieumuonDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }



    public ArrayList<Phieumuon> getDSPhieuMuon() {
        ArrayList<Phieumuon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON",null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            do{
                list.add(new Phieumuon(cursor.getInt(0)
                        ,cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6)

                      ));
            }while (cursor.moveToNext());
        }
        return list;
    }


//    public String getTenSachByMaSach(int maSach) {
//        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT TENSACH FROM SACH WHERE MASACH = ?", new String[] { String.valueOf(maSach) });
//
//        String tenSach = null;
//        if (cursor.moveToFirst()) {
//            tenSach = cursor.getString(0);
//        }
//
//        cursor.close();
//        return tenSach;
//    }
//    public String getTenTVByMaTV(int maTV) {
//        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT HOTEN FROM THANHVIEN WHERE MATV = ?", new String[] { String.valueOf(maTV) });
//
//        String tenTV = null;
//        if (cursor.moveToFirst()) {
//            tenTV = cursor.getString(0);
//        }
//
//        cursor.close();
//        return tenTV;
//    }
    public boolean themPhieuMuon(Phieumuon phieuMuon){
        SQLiteDatabase sqLiteDatabase=dbhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
//        contentValues.put("MAPM",phieuMuon.getMaPm());
        contentValues.put("MATV",phieuMuon.getMatv());
        contentValues.put("MATT",phieuMuon.getMatt());
        contentValues.put("MASACH",phieuMuon.getMasach());
        contentValues.put("NGAY",phieuMuon.getNgay());
        contentValues.put("TRASACH",phieuMuon.getTrasach());
        contentValues.put("TIENTHUE",phieuMuon.getTienthue());

        long check=sqLiteDatabase.insert("PHIEUMUON",null,contentValues);
        if(check==-1){
            return false;
        }
        return true;
    }

}
