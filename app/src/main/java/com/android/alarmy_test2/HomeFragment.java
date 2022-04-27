package com.android.alarmy_test2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.alarmy_test2.AddAlarm.AddAlarm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private FloatingActionButton mMainAddFab, mAddNormalFab, mAddFastFab;
    private TextView mAddNormalText, mAddFastText;
    private Animation mFabOpenAnim, mFabCloseAnim;
    private Button mDropdownBtn;
    private CardView mAlarmCard;
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

        mDropdownBtn = v.findViewById(R.id.outlinedButton);
        mAlarmCard = v.findViewById(R.id.alarm_card);
        isOpen = false;

        openClose();
        addAlarm();
        addFastAlarm();
        openDropdownMenu();

        return v;
    }
    public void openClose() {

        mMainAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    mMainAddFab.setImageResource(R.drawable.ic_baseline_add_24);
                    mAddNormalFab.setAnimation(mFabCloseAnim);
                    mAddFastFab.setAnimation(mFabCloseAnim);

                    mAddNormalText.setVisibility(View.INVISIBLE);
                    mAddFastText.setVisibility(View.INVISIBLE);

                    isOpen = false;
                } else {
                    mMainAddFab.setImageResource(R.drawable.ic_baseline_close_24);
                    mAddNormalFab.setAnimation(mFabOpenAnim);
                    mAddFastFab.setAnimation(mFabOpenAnim);

                    mAddNormalText.setVisibility(View.VISIBLE);
                    mAddFastText.setVisibility(View.VISIBLE);

                    isOpen = true;
                }

            }
        });
    }

    public void addAlarm() {
        mAddNormalFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddAlarm.class));
            }
        });
    }

    public void addFastAlarm() {
        mAddFastFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddFastAlarm(Gravity.CENTER);
            }
        });
    }

    private void openAddFastAlarm(int gravity) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_fast_alarm);

        Window window = dialog.getWindow();
        if(window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(true);

        dialog.show();
    }

    private void openDropdownMenu() {
        mDropdownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu();
            }
        });
    }

    private void showMenu() {
        PopupMenu popup = new PopupMenu(getContext(), this.mAlarmCard);
        popup.inflate(R.menu.alarm_cardview_dropdown);

        popup.show();
    }
}
