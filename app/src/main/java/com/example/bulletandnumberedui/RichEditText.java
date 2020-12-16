package com.example.bulletandnumberedui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

/**
 * Created by Sunil on 28/09/2020.
 */
public class RichEditText extends AppCompatEditText {
    CharSequence unicodeChar;
    int formatMode = 0;
    Context mContext;
    int currentIndex = 0;
    int newLineIndex = 0;
    int previousMode = 0;

    public RichEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public RichEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public RichEditText(Context context) {
        this(context, null, R.attr.editTextStyle);
    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (getFormatMode() == 2) {
            if (lengthAfter > lengthBefore) {
                if (text.toString().length() == 1) {
                    newLineIndex = ++currentIndex;
                    text = "   " + newLineIndex + ". " + text;
                    setText(text);
                    setSelection(Objects.requireNonNull(getText()).length());
                }
                if (text.toString().endsWith("\n")) {
                    newLineIndex = ++currentIndex;
                    int lastIndexOfn = text.toString().lastIndexOf("\n");
                    String subString = text.toString().substring(0, lastIndexOfn);
                    String result = subString.concat("\n   " + newLineIndex + ". ");
                    setText(result);
                    setSelection(Objects.requireNonNull(getText()).length());
                }
            }
        } else if (getFormatMode() == 1 || getFormatMode() == 3) {
            if (lengthAfter > lengthBefore) {
                if (text.toString().length() == 1) {
                    text = "   " + unicodeChar + " " + text;
                    setText(text);
                    setSelection(Objects.requireNonNull(getText()).length());
                }
                if (text.toString().endsWith("\n")) {
                    int lastIndexOfn = text.toString().lastIndexOf("\n");
                    String subString = text.toString().substring(0, lastIndexOfn);
                    String result = subString.concat("\n   " + unicodeChar + " ");
                    setText(result);
                    setSelection(Objects.requireNonNull(getText()).length());
                }
            }
        }

        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    public int getFormatMode() {
        return formatMode;
    }

    public void setFormatMode(int formatMode) {
        this.formatMode = formatMode;
        if (formatMode == 2) {
            previousMode++;
        }
        // To reset number when number format is selected for second time onwards
        if (previousMode > 1) {
            newLineIndex = 0;
            currentIndex = 0;
        }
        // Setting different bullet for different mode
        if (formatMode == 1) {
            unicodeChar = "\u25CF";
        } else if (formatMode == 3) {
            unicodeChar = "\u2023";
        }

    }
}