package com.anemon.ykssayac.Filters;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;


public class MinMaxFilter implements InputFilter {
    private int min;
    private int max;
    EditText editText;

    public MinMaxFilter(int min, int max, EditText editText) {
        this.min = min;
        this.max = max;
        this.editText=editText;
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
            else {
                editText.setText(String.valueOf(max));
                editText.setSelection(editText.getText().toString().length());
            }
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
