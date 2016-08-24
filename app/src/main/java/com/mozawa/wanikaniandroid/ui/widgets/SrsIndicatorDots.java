package com.mozawa.wanikaniandroid.ui.widgets;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SrsIndicatorDots extends LinearLayout {

    public static final int SRS_LEVELS = 5;

    @BindView(R.id.srsIndicatorDots)
    LinearLayout srsIndicatorDots;

    private List<TextView> indicatorDots = new LinkedList<>();

    public SrsIndicatorDots(Context context) {
        super(context);
        super.inflate(context, R.layout.widget_srs_indicator_dots, this);
        ButterKnife.bind(this);

        initIndicatorDots(SRS_LEVELS);
    }

    public SrsIndicatorDots(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.inflate(context, R.layout.widget_srs_indicator_dots, this);
        ButterKnife.bind(this);

        initIndicatorDots(SRS_LEVELS);
    }

    private void initIndicatorDots(int levels) {
        setIndicatorDots(levels);

        srsIndicatorDots.removeAllViews();
        for (int i = 0; i < indicatorDots.size(); i++) {
            srsIndicatorDots.addView(indicatorDots.get(i));
        }
    }

    public List<TextView> getIndicatorDots() {
        return indicatorDots;
    }

    private void setIndicatorDots(int levels) {
        TextView dotTextView;

        for (int i = 0; i < levels; i++) {
            dotTextView = new TextView(getContext());
            dotTextView.setText("•");
            dotTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.lightGrey200));

            if (i > 0) {
                dotTextView.setPadding(32, 0, 0, 0);
                dotTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            } else {
                dotTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            }

            indicatorDots.add(dotTextView);
        }
    }
}
