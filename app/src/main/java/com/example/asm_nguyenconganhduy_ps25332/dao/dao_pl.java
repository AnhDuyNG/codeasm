package com.example.asm_nguyenconganhduy_ps25332.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asm_nguyenconganhduy_ps25332.database.db_helper;
import com.example.asm_nguyenconganhduy_ps25332.model.phanloai;

import java.util.ArrayList;

public class dao_pl {
    private db_helper sqlite;
    public dao_pl(Context context){
        sqlite = new db_helper(context);
    }
    public ArrayList<phanloai> getAll(){
        ArrayList<phanloai> list =new ArrayList<>();
        SQLiteDatabase database = sqlite.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select* from Phanloai",null); // null vi khong co doi so
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(new phanloai(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            cursor.moveToNext();
        }
        database.close();
        cursor.close();
        return list;
    };

    public String getNameById(int maloai){
        SQLiteDatabase database = sqlite.getReadableDatabase();
        String sql = "Select * from where Maloai="+maloai;
        Cursor cursor = database.rawQuery(sql,null); // null vi khong co doi so
        cursor.moveToFirst();

        phanloai pl1= (new phanloai(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
        database.close();
        cursor.close();
        String ten = pl1.getTenloai();
        return  ten;
    }
    public ArrayList<phanloai> getAll(String trangthai){
        ArrayList<phanloai> list =new ArrayList<>();
        SQLiteDatabase database = sqlite.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select* from Phanloai where Trangthai='"+trangthai+"'",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(new phanloai(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            cursor.moveToNext();
        }
        database.close();
        cursor.close();
        return list;
    };
    public boolean insert(phanloai phanloai){
        try{
            SQLiteDatabase database =sqlite.getWritableDatabase();
            ContentValues contentValues =new ContentValues();
            contentValues.put("Tenloai",phanloai.getTenloai());
            contentValues.put("Trangthai",phanloai.getTrangthai());
            long values = database.insert("PhanLoai",null,contentValues);

            return (values>0);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    };
    public boolean update(phanloai phanloai){
        try{
            SQLiteDatabase database =sqlite.getWritableDatabase();
            ContentValues contentValues =new ContentValues();
            contentValues.put("Tenloai",phanloai.getTenloai());
            contentValues.put("Trangthai",phanloai.getTrangthai());
            long values = database.update("PhanLoai",contentValues,"Maloai=?",
                    new String[]{String.valueOf(phanloai.getMaLoai())});

            return (values>0);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }


    };
    public boolean delete(int maloai){
        try {
            SQLiteDatabase database =sqlite.getWritableDatabase();
            database.delete("PhanLoai","MaLoai=?",new String[]{String.valueOf(maloai)});
            return true;
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    };
}
