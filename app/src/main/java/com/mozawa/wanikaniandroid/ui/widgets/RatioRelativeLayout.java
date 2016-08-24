package com.mozawa.wanikaniandroid.ui.widgets;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class RatioRelativeLayout extends RelativeLayout {

    /**
     * A RelativeLayout where we can specify the height and width ratio.
     *
     * For reference:
     *   http://stackoverflow.com/a/29320794\
     */

    private static final double WIDTH_RATIO = 3;
    private static final double HEIGHT_RATIO = 4;

    public RatioRelativeLayout(Context context) {
        super(context);
    }

    public RatioRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = (int) (HEIGHT_RATIO / WIDTH_RATIO * widthSize);
        int newHeightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightSpec);
    }
}
