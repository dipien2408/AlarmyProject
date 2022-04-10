package com.android.alarmy_test2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private FloatingActionButton mMainAddFab, mAddNormalFab, mAddFastFab;
    private TextView mAddNormalText, mAddFastText;

    private Animation mFabOpenAnim, mFabCloseAnim;

    private boolean isOpen;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mMainAddFab = v.findViewById(R.id.main_add_fab);
        mAddNormalFab = v.findViewById(R.id.add_normal_fab);
        mAddFastFab = v.findViewById(R.id.add_fast_fab);

        mAddNormalText = v.findViewById(R.id.add_normal_text);
        mAddFastText = v.findViewById(R.id.add_fast_text);

        mFabOpenAnim = AnimationUtils.loadAnimation(HomeFragment.this.getActivity(), R.anim.fab_open);
        mFabCloseAnim = AnimationUtils.loadAnimation(HomeFragment.this.getActivity(), R.anim.fab_close);

        isOpen = false;
        openClose();

        return v;
    }
    public void openClose() {

        mMainAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeFragment.this.getActivity(), "fab1", Toast.LENGTH_SHORT).show();
                if(isOpen){

                    mAddNormalFab.setAnimation(mFabCloseAnim);
                    mAddFastFab.setAnimation(mFabCloseAnim);

                    mAddNormalText.setVisibility(View.INVISIBLE);
                    mAddFastText.setVisibility(View.INVISIBLE);

                    isOpen = false;
                } else {

                    mAddNormalFab.setAnimation(mFabOpenAnim);
                    mAddFastFab.setAnimation(mFabOpenAnim);

                    mAddNormalText.setVisibility(View.VISIBLE);
                    mAddFastText.setVisibility(View.VISIBLE);

                    isOpen = true;
                }

            }
        });
    }
}
