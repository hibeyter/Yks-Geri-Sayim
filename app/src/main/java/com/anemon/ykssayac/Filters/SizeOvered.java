package com.anemon.ykssayac.Filters;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.Toast;

public class SizeOvered implements InputFilter {

    private EditText meEdittext,compEdittext;
    private int maxInt;
    private Context context;
    public SizeOvered(EditText meEdittext, EditText compEdittext, int maxInt, Context context) {
        this.meEdittext = meEdittext;
        this.compEdittext = compEdittext;
        this.maxInt = maxInt;
        this.context=context;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            int remaining = maxInt;
            if (compEdittext.getText().toString().length() > 0) {
                remaining = maxInt - Integer.parseInt(compEdittext.getText().toString());
            }
            if (isOvered(remaining, input)) {
                return null;
            } else {
                if (remaining == 0) {
                    Toast.makeText(context, "Fazla soru giremezsin." , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Fazla soru giremezsin. Düzelttim:)" , Toast.LENGTH_SHORT).show();
                }
                meEdittext.setText(String.valueOf(remaining));
                meEdittext.setSelection(meEdittext.getText().toString().length());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean isOvered(int remaining,int input) {
        // if (input>remaining)  return false;else return true;
        return input <= remaining;
    }
}
