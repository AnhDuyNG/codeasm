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

import com.example.asm_nguyenconganhduy_ps25332.phanloai_activity;
import com.example.asm_nguyenconganhduy_ps25332.R;
import com.example.asm_nguyenconganhduy_ps25332.dao.dao_pl;
import com.example.asm_nguyenconganhduy_ps25332.model.phanloai;

import java.util.ArrayList;

public class phanloaiadapter extends RecyclerView.Adapter<phanloaiadapter.PhanLoaiViewholder> {
    dao_pl dao_pl;
    Context context;
    ArrayList<phanloai> list;
    public phanloaiadapter(Context context, ArrayList<phanloai> list){
        this.context = context;
        this.list = list;
        dao_pl = new dao_pl(context);
    }
    @NonNull
    @Override
    public PhanLoaiViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.recycleview_pl_item,parent,false);
        PhanLoaiViewholder viewholder = new PhanLoaiViewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhanLoaiViewholder holder, int position) {
        phanloai pl = list.get(position);
        holder.txtTenloai.setText(list.get(position).getTenloai());
        holder.txtTrangthai.setText(list.get(position).getTrangthai());
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
                        if(dao_pl.delete(pl.getMaLoai())){
                            Toast.makeText(context, "Delete thành công ", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(dao_pl.getAll());
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
                openDialogUpdate(pl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PhanLoaiViewholder extends RecyclerView.ViewHolder{
        TextView txtTenloai,txtTrangthai,txt1,txt2;
        ImageButton imgedit,imgdelete;
        CardView cardView;
        public PhanLoaiViewholder(View view){
            super(view);
            txtTenloai = view.findViewById(R.id.textedit_tenloai);
            txtTrangthai = view.findViewById(R.id.textedit_trangthai);
            txt1=view.findViewById(R.id.text_tenloai);
            txt2=view.findViewById(R.id.text_trangthai);
            imgedit = view.findViewById(R.id.btn_updatetext);
            imgdelete = view.findViewById(R.id.btn_deletetext);
        }
    }
    private void openDialogUpdate(phanloai pl){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater =((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thempl,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        Spinner spinner = view.findViewById(R.id.spinner_status_update);
        EditText txtname = view.findViewById(R.id.text_type_update);
        Button btnupdate = view.findViewById(R.id.btn_updatePhanLoai);
        Button btncancel = view.findViewById(R.id.btn_canclePhanLoai);
        // tao du lieu spinner
        String[] status= {"Receipt","Payment"} ;
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,status);
        spinner.setAdapter(spnAdapter);
        // gan gia tri tai vi tri position
        txtname.setText(pl.getTenloai());
        for(int i = 0; i < status.length ; i++){
            if(pl.getTrangthai().equalsIgnoreCase(status[i])){
                spinner.setSelection(i);
            }
        }
        // update du lieu
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pl.setTenloai(txtname.getText().toString());
                pl.setTrangthai((String)spinner.getSelectedItem());
                if(dao_pl.update(pl)){
                    Toast.makeText(context, "Update thành công ", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(dao_pl.getAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                }else{
                    Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
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
