package com.example.asm_nguyenconganhduy_ps25332.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.asm_nguyenconganhduy_ps25332.fragment.frag_gd_thu;
import com.example.asm_nguyenconganhduy_ps25332.fragment.frag_thu;

public class thuadapter extends FragmentStateAdapter {

    public thuadapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position ==0){
            return new frag_thu();
        }else{
            return new frag_gd_thu();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
