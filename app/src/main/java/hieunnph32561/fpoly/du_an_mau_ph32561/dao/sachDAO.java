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
            // login.setTendangnhap(cursor.getString(0));
            list.add(login);
        }
        return list;
    }

    public ArrayList<Sach> getAll() {
        String sql = "select * from SACH";
        return (ArrayList<Sach>) getALLSACH(sql);
    }

    public Sach getID(String id) {
        String sql = "select * from SACH where MASACH=?";
        List<Sach> list = getALLSACH(sql, id);
        return list.get(0);
    }


}














