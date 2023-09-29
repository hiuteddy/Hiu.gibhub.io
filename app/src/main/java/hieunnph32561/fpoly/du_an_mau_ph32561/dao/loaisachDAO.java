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

public class loaisachDAO {
    private Dbhelper mySQLite;
    private SQLiteDatabase db;
    public loaisachDAO(Context context){
        mySQLite=new Dbhelper(context);
        db=mySQLite.getWritableDatabase();
    }
    public boolean insertLoaiSach(Loaisach ls){
        //Tao contentvalues
        ContentValues contentValues = new ContentValues();
        contentValues.put("THELOAI", ls.getTenLoai());
        long check =  db.insert("LOAISACH", null,contentValues);
        if (check == -1){
            return false;
        }else {
            return true;
        }
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
    public Loaisach getID(int maLoai) {
        String sql = "SELECT * FROM LOAISACH WHERE MALOAI=?";
        List<Loaisach> list = getDaTa(sql, String.valueOf(maLoai));

        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            // Trả về một giá trị Loaisach mặc định hoặc tạo một đối tượng mới tùy ý
            return new Loaisach();
        }
    }

}
