package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Loaisach;

public class loaisachDAO {
    private Dbhelper mySQLite;
    private SQLiteDatabase db;
    public loaisachDAO(Context context){
        mySQLite=new Dbhelper(context);
        db=mySQLite.getWritableDatabase();
    }
    public long insert(Loaisach loaisach ){
        ContentValues values=new ContentValues();
        values.put("THELOAI",loaisach.getTenLoai());
        return db.insert("LOAISACH",null,values);
    }
    public int upate(Loaisach l){
        ContentValues values=new ContentValues();
        values.put("tenLoai",l.getTenLoai());
        return db.update("LoaiSach",values,"maLoai=?",new String[]{l.getMaLoai()+""});
    }
    public int delete(int s){
        return db.delete("LOAISACH","MALOAI=?",new String[]{s+""});
    }
    public List<Loaisach> getDaTa(String sql, String...selectionArgs){
        List<Loaisach> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        if (c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                int a = c.getInt(0);
                String b = c.getString(1);
                list.add(new Loaisach(a,b));
                c.moveToNext();
            }
            c.close();
        }
        return list;
    }
    public List<Loaisach> getAll(){
        String sql="select * from LOAISACH";
        return getDaTa(sql);
    }
    public Loaisach getID(String id){
        String sql="select * from LOAISACH where MALOAI=?";
        List<Loaisach> list=getDaTa(sql,id);
        return list.get(0);
    }
}
