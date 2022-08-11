package com.example.asm_nguyenconganhduy_ps25332.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.asm_nguyenconganhduy_ps25332.R;
import com.example.asm_nguyenconganhduy_ps25332.adapter.chiadapter;
import com.example.asm_nguyenconganhduy_ps25332.adapter.thuadapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class frag_chi extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi,container,false);
        TabLayout tabLayout = view.findViewById(R.id.tab_thu);
        ViewPager2 pager2 = view.findViewById(R.id.viewpage_thu);
        chiadapter adapter = new chiadapter(getActivity());
        pager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, pager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    tab.setText("Loại chi");
                }else{
                    tab.setText("Khoản chi");
                }
            }
        }).attach();
        return view;
    }
}
