package hieunnph32561.fpoly.du_an_mau_ph32561.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hieunnph32561.fpoly.du_an_mau_ph32561.database.Dbhelper;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Sach;
import hieunnph32561.fpoly.du_an_mau_ph32561.model.Thanhvien;

public class thanhvienDAO {

    private Dbhelper dBhelper;

    public thanhvienDAO(Context context) {
        dBhelper = new Dbhelper(context);
    }



    public ArrayList<Thanhvien> getALLSP() {
        ArrayList<Thanhvien> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase(); // thêm this.dBhelper
        Cursor cursor = database.rawQuery("SELECT * FROM THANHVIEN", null);

        while (cursor.moveToNext()) {
            Thanhvien tv = new Thanhvien(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));
            list.add(tv);
        }
        cursor.close(); // đóng con trỏ khi hoàn thành công việc
        return list;
    }
    public long add(Thanhvien tv){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("MATV",tv.getMatv());
        values.put("HOTEN",tv.getHoten());
        values.put("NAMSINH",tv.getNamsinh());
        return database.insert("THANHVIEN",null,values);
    }

    public long addsp(Thanhvien tv){
        SQLiteDatabase  database=dBhelper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("MATV",tv.getMatv());
        values.put("HOTEN",tv.getHoten());
        values.put("NAMSINH",tv.getNamsinh());
        return database.insert("THANHVIEN",null,values);
    }


    public long udt(Thanhvien tv){
        SQLiteDatabase  database=dBhelper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("MATV",tv.getMatv());
        values.put("HOTEN",tv.getHoten());
        values.put("NAMSINH",tv.getNamsinh());
        return database.update("THANHVIEN",values,"MATV=?",new String[]{
                String.valueOf(tv.getMatv())
        });


    }
    public long delete(int mtv){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        long check=database.delete("THANHVIEN","MATV=?",new String[]{
                String.valueOf(mtv)
        });
        return  check;
    }
}