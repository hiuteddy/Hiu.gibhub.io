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

    private Dbhelper mySQLite;
    private SQLiteDatabase db;
    public sachDAO(Context context){
        mySQLite=new Dbhelper(context);
        db=mySQLite.getWritableDatabase();
    }
    public long insert(Sach s){
        ContentValues values=new ContentValues();
        values.put("TENSACH",s.getTenSach());
        values.put("GIATHUE",s.getGiaThue());
        values.put("THELOAI",s.getMaLoai());
        return db.insert("SACH",null,values);
    }
    public int upate(Sach s){
        ContentValues values=new ContentValues();
        values.put("tenSach",s.getTenSach());
        values.put("giaThue",s.getGiaThue());
        values.put("maLoai",s.getMaLoai());
        return db.update("Sach",values,"maSach=?",new String[]{s.getMaSach()+""});
    }
    public int delete(int s){
        return db.delete("SACH","MASACH=?",new String[]{s+""});
    }
    public List<Sach> getDaTa(String sql, String...selectionArgs){
        List<Sach> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        if (c.getCount() > 0) {
            c.moveToNext();
            while (!c.isAfterLast()) {
                int a = c.getInt(0);
                String b = c.getString(1);
                int cc = c.getInt(2);
                int d = c.getInt(3);
                list.add(new Sach(a,b,cc,d));
                c.moveToNext();
            }
            c.close();
        }
        return list;
    }
    public ArrayList<Sach> getAll(){
        String sql="select * from SACH";
        return (ArrayList<Sach>) getDaTa(sql);
    }
    public Sach getID(String id){
        String sql="select * from SACH where MASACH=?";
        List<Sach> list=getDaTa(sql,id);
        return list.get(0);
    }


    }














