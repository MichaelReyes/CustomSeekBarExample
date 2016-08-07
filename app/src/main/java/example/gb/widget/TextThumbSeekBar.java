package example.gb.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;

import example.gb.seekbarexample.R;

/**
 * Created by Bryan-PC on 07/08/2016.
 */
public class TextThumbSeekBar extends SeekBar {

    private int mThumbSize;
    private TextPaint mTextPaint;

    public TextThumbSeekBar(Context context) {
        this(context, null);
    }

    public TextThumbSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.seekBarStyle);
    }

    public TextThumbSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mThumbSize = getResources().getDimensionPixelSize(R.dimen.thumb_size);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.thumb_text_size));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.RIGHT);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String progressText = String.valueOf(getProgress());
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(progressText, 0, progressText.length(), bounds);

        int leftPadding = getPaddingLeft() - getThumbOffset();
        int rightPadding = getPaddingRight() - getThumbOffset();
        int width = getWidth() - leftPadding - rightPadding;
        float progressRatio = (float) getProgress() / getMax();
        float thumbOffset = mThumbSize * (.5f - progressRatio);
        float thumbX = progressRatio * width + leftPadding + thumbOffset + 20;
        float thumbY = (getHeight() / 2f + bounds.height() / 2f) / 2;

        Log.v("leftPadding" , " " + leftPadding);
        Log.v("rightPadding" , " " + rightPadding);
        Log.v("width" , " " + width);
        Log.v("progressRatio" , " " + progressRatio);
        Log.v("thumbOffset" , " " + thumbOffset);
        Log.v("thumbX" , " " + thumbX);
        Log.v("thumbY" , " " + thumbY);

        canvas.drawText(progressText, thumbX, thumbY, mTextPaint);
    }

}
