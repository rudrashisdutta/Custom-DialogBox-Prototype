package com.rudrashisdutta.layouttrialapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.rudrashisdutta.layouttrialapp.databinding.DialogBinding;


/*public class CustomDialog extends AlertDialog {
    DialogBinding binding;
    AlertDialog alertDialog;
    protected CustomDialog(Context context) {
        super(context);
        binding = DialogBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alertDialog = builder.create();
        CardView cardView = binding.dialogBox;
        binding.getRoot().removeView(cardView);
        alertDialog.setView(cardView);
        alertDialog.show();

        binding.dialogNegative.setOnClickListener((v) ->{
            alertDialog.cancel();
        });

    }

}*/
public class CustomDialog extends AlertDialog {
    DialogBinding binding;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    Context context;
    EditText input;
    CardView cardView;

    protected CustomDialog(Context context) {
        super(context,R.style.AlertDia);
        this.context = context;
        builder = new AlertDialog.Builder(context);
        binding = DialogBinding.inflate(getLayoutInflater());
        alertDialog = builder.create();
        cardView = binding.dialogBox;
        binding.getRoot().removeView(cardView);
        binding.dialogNegative.setOnClickListener((v) ->{
            alertDialog.cancel();
        });
        binding.dialogPositive.setVisibility(View.GONE);
        binding.dialogInoutField.setVisibility(View.GONE);
        this.input = binding.dialogInoutField;
    }

    public EditText getInput() {
        return input;
    }
    public void setInput(String hint, @Nullable Drawable drawable) {
        if(input.getVisibility() == View.GONE){
            input.setVisibility(View.VISIBLE);
        }
        input.setHint(hint);
        if(drawable != null){
            input.setBackground(drawable);
        }
    }
    public void setInput(String hint, String preText,@Nullable Drawable drawable) {
        this.input = binding.dialogInoutField;
        input.setText(preText);
        input.setHint(hint);
        if(drawable != null){
            input.setBackground(drawable);
        }
    }

    public void customise(Runnable runnable){
        runnable.run();
    }

    public void setCustomTitle(String title){
        binding.dialogTitleField.setText(title);
    }

    public void setPositiveButton(String aContinue, Runnable runnable,@Nullable  String errorMessage) {
        binding.dialogPositive.setText(aContinue);
        alertDialog = builder.create();
        binding.dialogPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!getInput().getText().toString().isEmpty()) || (input.getVisibility()==View.GONE)) {
                    performPositiveButtonClick(runnable);
                }
                else{
                    Context context1 = assignContext(context);
                    context1.setTheme(R.style.AlertDia);
                    Toast.makeText(context1, String.format(context.getString(R.string.toast_message),errorMessage),Toast.LENGTH_SHORT).show();
                }
            }

            private void performPositiveButtonClick(Runnable runnable){
                alertDialog.cancel();
                runnable.run();
            }

            private Context assignContext(Context c){
                return c;
            }
        });
        if(binding.dialogPositive.getVisibility() == View.GONE){
            binding.dialogPositive.setVisibility(View.VISIBLE);
        }
    }

    public void setNegativeButton(String aContinue, @Nullable Runnable runnable) {
        binding.dialogNegative.setText(aContinue);
        alertDialog = builder.create();
        if(runnable!=null) {
            binding.dialogNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    performNegativeButtonClick(runnable);
                }

                private void performNegativeButtonClick(Runnable runnable) {
                    alertDialog.cancel();
                    runnable.run();
                }
            });
        }
    }

    public AlertDialog createCustomDialog(){
        alertDialog.setView(cardView);
        return alertDialog;
    }


}