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
        Cursor cursor = database.rawQuery("SELECT * FROM LOAISACH", null);

        while (cursor.moveToNext()) {
            Loaisach ls = new Loaisach(
                    cursor.getInt(0),
                    cursor.getString(1));

            // login.setTendangnhap(cursor.getString(0));
            list.add(ls);
        }
        return list;
    }


    public List<Loaisach> getAll() {
        String sql = "select * from LOAISACH";
        return getALLSACH(sql);
    }

    public Loaisach getID(String id) {
        String sql = "select * from LOAISACH where MALOAI=?";
        List<Loaisach> list = getALLSACH(sql, id);
        return list.get(0);
    }

}
