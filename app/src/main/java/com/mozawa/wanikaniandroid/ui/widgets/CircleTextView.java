package com.mozawa.wanikaniandroid.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleTextView extends RelativeLayout {

    private static final int CIRCLE_NO_ITEMS = R.drawable.circle_bg_no_items;
    private static final int CIRCLE_ITEMS_EXIST = R.drawable.circle_bg_items_exist;

    @BindView(R.id.circleImageView)
    ImageView circleImageView;
    @BindView(R.id.circleTextView)
    TextView circleTextView;
    @BindView(R.id.labelTextView)
    TextView labelTextView;

    String labelText;

    public CircleTextView(Context context) {
        super(context);
        super.inflate(context, R.layout.widget_circle_text_view, this);
        ButterKnife.bind(this);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.inflate(context, R.layout.widget_circle_text_view, this);
        ButterKnife.bind(this);

        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CircleTextView, 0, 0);

        try {
            labelText = array.getString(R.styleable.CircleTextView_labelText);
        } finally {
            array.recycle();
        }

        setLabelText();
    }

    public void setLabelText() {
        if (labelText != null) {
            labelTextView.setText(labelText);
            invalidate();
            requestLayout();
        }
    }

    public void setCircleImageResource(String string) {
        if (string != null) {
            if (string.equals("0")) {
                circleImageView.setImageResource(CIRCLE_NO_ITEMS);
            } else {
                circleImageView.setImageResource(CIRCLE_ITEMS_EXIST);
            }
        }
    }

    public void setText(String string) {
        circleTextView.setText(string);
        setCircleImageResource(string);
    }
}
