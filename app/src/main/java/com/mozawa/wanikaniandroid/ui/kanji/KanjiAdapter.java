package com.mozawa.wanikaniandroid.ui.kanji;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Kanji;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.KanjiViewHolder> {

    private Context context;
    private List<Kanji> kanjiList;
    private KanjiClickedListener kanjiClickedListener;

    @Inject
    public KanjiAdapter() {
        kanjiList = new ArrayList<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setKanjiList(List<Kanji> kanjiList) {
        this.kanjiList = kanjiList;
    }

    public void setKanjiClickedListener(KanjiClickedListener kanjiClickedListener) {
        this.kanjiClickedListener = kanjiClickedListener;
    }

    @Override
    public KanjiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_kanji, parent, false);
        return new KanjiViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(KanjiViewHolder holder, int position) {
        final Kanji kanji = kanjiList.get(position);

        holder.characterTextView.setText(kanji.character);
        holder.onyomiTextView.setText(kanji.onyomi);
        holder.meaningTextView.setText(kanji.meaning);
        holder.levelTextView.setText("Level " + kanji.level);

        // On click
        holder.kanjiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kanjiClickedListener.onKanjiClicked(kanji);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kanjiList.size();
    }

    public class KanjiViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.kanjiView)
        View kanjiView;
        @BindView(R.id.characterTextView)
        TextView characterTextView;
        @BindView(R.id.onyomiTextView)
        TextView onyomiTextView;
        @BindView(R.id.meaningTextView)
        TextView meaningTextView;
        @BindView(R.id.levelTextView)
        TextView levelTextView;

        public KanjiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface KanjiClickedListener {

        void onKanjiClicked(Kanji kanji);

    }
}
