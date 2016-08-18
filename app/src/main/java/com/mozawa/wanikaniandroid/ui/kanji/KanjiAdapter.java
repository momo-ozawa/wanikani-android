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
    private List<Kanji.KanjiInformation> kanjiInformationList;

    @Inject
    public KanjiAdapter() {
        kanjiInformationList = new ArrayList<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setKanjiInformationList(List<Kanji.KanjiInformation> kanjiInformationList) {
        this.kanjiInformationList = kanjiInformationList;
    }

    @Override
    public KanjiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_kanji, parent, false);
        return new KanjiViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(KanjiViewHolder holder, int position) {
        Kanji.KanjiInformation kanji = kanjiInformationList.get(position);

        holder.characterTextView.setText(kanji.character);
    }

    @Override
    public int getItemCount() {
        return kanjiInformationList.size();
    }

    public class KanjiViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.characterTextView)
        TextView characterTextView;

        public KanjiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
