package com.example.asm_nguyenconganhduy_ps25332.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_nguyenconganhduy_ps25332.R;
import com.example.asm_nguyenconganhduy_ps25332.dao.dao_gd;
import com.example.asm_nguyenconganhduy_ps25332.dao.dao_pl;
import com.example.asm_nguyenconganhduy_ps25332.model.giaodich;
import com.example.asm_nguyenconganhduy_ps25332.model.phanloai;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class giaodichadapter extends RecyclerView.Adapter<giaodichadapter. GiaoDichViewholder> {
    dao_gd dao_gd;
    Context context;
    ArrayList<giaodich> list;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public giaodichadapter(Context context, ArrayList<giaodich> list){
        this.context = context;
        this.list = list;
        this.dao_gd = new dao_gd(context);
    }
    @NonNull
    @Override
    public  GiaoDichViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.recycleview_gd_item,parent,false);
        GiaoDichViewholder viewholder = new  GiaoDichViewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull  GiaoDichViewholder holder, int position) {
        giaodich gd = list.get(position);
        dao_pl dao = new dao_pl(context);
        holder.txtTenGD.setText(gd.getTieude());
        holder.txtNgay.setText(sdf.format(gd.getNgay()));
        holder.txtTien.setText(String.valueOf(gd.getTien()));
        holder.txtTrangthaiGD.setText(gd.getMo_Ta());
        holder.txtMaloai.setText(dao.getNameById(gd.getMaLoai()));
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_xoapl,null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                Button btndelete = view.findViewById(R.id.btn_deletePhanLoai);
                Button btncancel = view.findViewById(R.id.btn_canclePhanLoai);
                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dao_gd.delete(gd.getMaGD())){
                            Toast.makeText(context, "Delete thành công ", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(dao_gd.getAll());
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }else{

                        }
                    }
                });
                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        // update
        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate(gd);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GiaoDichViewholder extends RecyclerView.ViewHolder{
        TextView txtTenGD,txtTrangthaiGD,txtNgay,txtTien,txtMaloai;
        ImageButton imgedit,imgdelete;
        CardView cardView;
        public  GiaoDichViewholder(View view){
            super(view);
            txtTenGD = view.findViewById(R.id.textedit_tenloai_GD);
            txtNgay = view.findViewById(R.id.textedit_date_GD);
            txtTien = view.findViewById(R.id.textedit_money_GD);
            txtTrangthaiGD = view.findViewById(R.id.textedit_trangthai_GD);
            txtMaloai = view.findViewById(R.id.textedit_maloai_GD);
            imgedit = view.findViewById(R.id.btn_updatetext_GD);
            imgdelete = view.findViewById(R.id.btn_deletetext_GD);
        }
    }
    private void openDialogUpdate(giaodich gd){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater =((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thempl,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText txtstatus = view.findViewById(R.id.textedit_status_GD);
        EditText txtdate = view.findViewById(R.id.text_date_update_GD);
        EditText txtmoney = view.findViewById(R.id.text_money_update_GD);
        EditText txtname = view.findViewById(R.id.text_type_update_GD);
        EditText txtMaloai = view.findViewById(R.id.textedit_maloai_GD);
        Button btnupdate = view.findViewById(R.id.btn_updateGD);
        Button btncancel = view.findViewById(R.id.btn_cancleGD);

        // gan gia tri tai vi tri position
        txtname.setText(gd.getTieude());
        txtdate.setText(sdf.format(gd.getNgay()));
        txtmoney.setText(String.valueOf(gd.getTien()));
        txtMaloai.setText(String.valueOf(gd.getMaGD()));
        txtstatus.setText(gd.getMo_Ta());


        // update du lieu
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gd.setTieude(txtname.getText().toString());
                    gd.setNgay(sdf.parse(txtdate.getText().toString()));
                    gd.setTien(Float.parseFloat(txtmoney.getText().toString()));
                    gd.setMo_Ta(txtstatus.getText().toString());
                    gd.setMaLoai(Integer.parseInt(txtMaloai.getText().toString()));
                    if(dao_gd.update(gd)){
                        Toast.makeText(context, "Update thành công ", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(dao_gd.getAll());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }

            }
        });
        // cancel
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
