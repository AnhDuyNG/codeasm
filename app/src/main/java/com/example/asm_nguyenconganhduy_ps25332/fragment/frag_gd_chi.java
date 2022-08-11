package com.example.asm_nguyenconganhduy_ps25332.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_nguyenconganhduy_ps25332.adapter.giaodichadapter;
import com.example.asm_nguyenconganhduy_ps25332.dao.dao_gd;
import com.example.asm_nguyenconganhduy_ps25332.R;
import com.example.asm_nguyenconganhduy_ps25332.adapter.giaodichadapter;
import com.example.asm_nguyenconganhduy_ps25332.dao.dao_gd;
import com.example.asm_nguyenconganhduy_ps25332.model.giaodich;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class frag_gd_chi extends Fragment {
    RecyclerView recyclerView;
    dao_gd dao_gd;
    ArrayList<giaodich> list = new ArrayList<>();
    giaodichadapter adapter;
    FloatingActionButton floatbtn;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_giaodich,container,false);
        floatbtn = view.findViewById(R.id.Btn_FloatGD);
        recyclerView = view.findViewById(R.id.recycle_GD);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        dao_gd = new dao_gd(getContext());
        list = dao_gd.getAll("Payment");
        adapter = new giaodichadapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();
            }
        });
        return view;
    }
    private void openDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themgd,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText txtstatus = view.findViewById(R.id.textedit_status_GD);
        EditText txtdate = view.findViewById(R.id.textedit_date_GD);
        EditText txtmoney = view.findViewById(R.id.textedit_money_GD);
        EditText txtname = view.findViewById(R.id.text_type_GD);
        EditText txtMaloai = view.findViewById(R.id.textedit_maloai_GD);
        Button btnadd = view.findViewById(R.id.btn_addGD);
        Button btncancel = view.findViewById(R.id.btn_cancleGD);

        // them du lieu
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(dao_gd.insert(new giaodich(
                            txtname.getText().toString(),
                            sdf.parse(txtdate.getText().toString()),
                            Float.parseFloat(txtmoney.getText().toString()),
                            txtstatus.getText().toString(),
                            Integer.parseInt(txtMaloai.getText().toString()))
                    )){
                        Toast.makeText(getContext(), "Thêm thành công ", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(dao_gd.getAll("Receipt"));
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
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
