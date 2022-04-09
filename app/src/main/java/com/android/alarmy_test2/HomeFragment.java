package com.android.alarmy_test2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {
    FloatingActionButton fab_1,fab_2,fab_3;
    boolean anhien=false;
    TextView text_baothuc, text_baothucnhanh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        // fab
        fab_1=(FloatingActionButton)v.findViewById(R.id.fab_1);
        fab_2 = (FloatingActionButton)v.findViewById (R.id.fab_2);
        fab_3 = (FloatingActionButton)v.findViewById (R.id.fab_3);
        text_baothuc=(TextView)v.findViewById(R.id.text_baothuc);
        text_baothucnhanh=(TextView)v.findViewById(R.id.text_baothucnhanh);
        fab_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (anhien==false) {
                    HienFab();
                    anhien=true;
                } else {
                    AnFab();
                    anhien=false;
                }
            }
        });
        return v;
    }

    private void HienFab(){
        fab_2.show();
        fab_3.show();
        text_baothuc.setVisibility(View.VISIBLE);
        text_baothucnhanh.setVisibility(View.VISIBLE);
    }
    private void AnFab(){
        fab_2.hide();
        fab_3.hide();
        text_baothuc.setVisibility(View.INVISIBLE);
        text_baothucnhanh.setVisibility(View.INVISIBLE);
    }

}
