package com.example.bulletandnumberedui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by Sunil on 28/09/2020.
 */
public class CustomEditTextWithNumber extends androidx.appcompat.widget.AppCompatEditText {
   // boolean isNumberFormat;
    Context mContext;
    int currentIndex = 0;
    int newLineIndex = 0;


    public CustomEditTextWithNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public CustomEditTextWithNumber(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public CustomEditTextWithNumber(Context context) {
        this(context, null, R.attr.editTextStyle);
    }



    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
       // if (isNumberFormat()) {
            if (lengthAfter > lengthBefore) {
                if (text.toString().length() == 1) {
                    newLineIndex = ++currentIndex;
                    text = " " + newLineIndex + ". " + text;
                    setText(text);
                    setSelection(Objects.requireNonNull(getText()).length());
                }
                if (text.toString().endsWith("\n")) {
                    newLineIndex = ++currentIndex;
                    int lastIndexOfn = text.toString().lastIndexOf("\n");
                    String subString = text.toString().substring(0, lastIndexOfn);
                    String result = subString.concat("\n " + newLineIndex + ". ");
                    setText(result);
                    setSelection(Objects.requireNonNull(getText()).length());
                }
            }
      //  }else {
            if (lengthAfter > lengthBefore) {
                if (text.toString().length() == 1) {
                    text = "• " + text;
                    setText(text);
                    setSelection(Objects.requireNonNull(getText()).length());
                }
                if (text.toString().endsWith("\n")) {

                    text = text.toString().replace("\n", "\n• ");
                    text = text.toString().replace("• •", "•");
                    setText(text);
                    setSelection(Objects.requireNonNull(getText()).length());
                }
            }
       // }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
  /*  public boolean isNumberFormat() {
        return isNumberFormat;
    }

    public void setNumberFormat(boolean numberFormat) {
        isNumberFormat = numberFormat;
    }*/

}