package com.rudrashisdutta.layouttrialapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Toast;

import com.rudrashisdutta.layouttrialapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Context context = this;
        binding.button.setOnClickListener(v -> {
            try {
                 CustomDialog customDialog = new CustomDialog(new ContextThemeWrapper(context, R.style.AlertDia));
                 customDialog.customise(() -> {
                     customDialog.setInput(String.format(getString(R.string.custom_dialog_input_hint),"Enter Here..."),null);
                     customDialog.setCustomTitle(String.format(getString(R.string.custom_dialog_title_text),"This is a custom title."));
                     customDialog.setNegativeButton(String.format(getString(R.string.custom_dialog_negative_text),"CANCEL"),null);
                     customDialog.setPositiveButton(String.format(getString(R.string.custom_dialog_positive_text), "OK"), (Runnable) () -> {
                         Toast.makeText(context,String.format(getString(R.string.toast_message),"It fucking works."),Toast.LENGTH_LONG).show();
                     },String.format(getString(R.string.toast_message),"Something went wrong!"));
                 });
                AlertDialog alertDialog = customDialog.createCustomDialog();
                alertDialog.show();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}