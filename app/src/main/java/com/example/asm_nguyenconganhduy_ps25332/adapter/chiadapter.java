package com.example.asm_nguyenconganhduy_ps25332.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.asm_nguyenconganhduy_ps25332.fragment.frag_chi;
import com.example.asm_nguyenconganhduy_ps25332.fragment.frag_gd_chi;

public class chiadapter extends FragmentStateAdapter {

    public chiadapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position ==0){
            return new frag_chi();
        }else{
            return new frag_gd_chi();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

