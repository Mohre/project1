package io.sharif.prj1.st90105991.st91106065;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

/**
 * Created by mohre on 8/4/2016 AD.
 */
public class gameFragment extends Fragment{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        int x = preferences.getInt("x", 5);
        int y = preferences.getInt("y", 5);
        newGame(x, y, view);
//        newGame(1, 1, view);


        Button l_btn = (Button) view.findViewById(R.id.l_btn);
        Button r_btn = (Button) view.findViewById(R.id.r_btn);
        Button u_btn = (Button) view.findViewById(R.id.u_btn);
        Button d_btn = (Button) view.findViewById(R.id.d_btn);

        l_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                View golang_left =  getView().findViewById(R.id.golang_left);
                LayoutParams params = ((LayoutParams) golang_left.getLayoutParams());
                if(params.weight > 0)
                    params.weight = params.weight - 1;
                golang_left.setLayoutParams(params);
            }
        });

        r_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                View golang_left =  getView().findViewById(R.id.golang_left);
                LayoutParams params = ((LayoutParams) golang_left.getLayoutParams());
                if(params.weight < 10)
                    params.weight = params.weight + 1;
                golang_left.setLayoutParams(params);
            }
        });

        u_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                View golang_top =  getView().findViewById(R.id.golang_top);
                LayoutParams params = ((LayoutParams) golang_top.getLayoutParams());
                if(params.weight > 0)
                    params.weight = params.weight - 1;
                golang_top.setLayoutParams(params);
            }
        });

        d_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                View golang_top = getView().findViewById(R.id.golang_top);
                LayoutParams params = ((LayoutParams) golang_top.getLayoutParams());
                if (params.weight < 10)
                    params.weight = params.weight + 1;
                golang_top.setLayoutParams(params);
            }
        });

        view.findViewById(R.id.gameMenu).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_save:
                                saveGame(getView());
                                return true;
                            case R.id.item_new:
                                newGame(5, 5, getView());
                                showToast(getView());
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.inflate(R.menu.popup);
                popupMenu.show();
            }
        });

        return view;
    }

    private void showToast(View view) {
        String newGameString = getString(R.string.new_game);
        SpannableString spannableString = new SpannableString(newGameString);
        spannableString.setSpan(new RainbowSpan(view.getContext()),0,newGameString.length(),0);
        Toast.makeText(view.getContext(), spannableString, Toast.LENGTH_LONG).show();
    }

    private void newGame(int x, int y, View view){
        View golang_left =  view.findViewById(R.id.golang_left);
        LayoutParams x_params = ((LayoutParams) golang_left.getLayoutParams());
        x_params.weight = x;
        golang_left.setLayoutParams(x_params);

        View golang_top =  view.findViewById(R.id.golang_top);
        LayoutParams y_params = ((LayoutParams) golang_top.getLayoutParams());
        y_params.weight = y;
        golang_top.setLayoutParams(y_params);
    }
    private void saveGame(View view) {
        float x = ((LayoutParams)view.findViewById(R.id.golang_left).getLayoutParams()).weight;
        float y = ((LayoutParams)view.findViewById(R.id.golang_top).getLayoutParams()).weight;
        saveGame((int)x, (int)y);
    }

    private void saveGame(int x, int y){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getView().getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("x", x);
        editor.putInt("y", y);
        editor.apply();
    }
}