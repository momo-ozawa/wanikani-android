package com.mozawa.wanikaniandroid.ui.vocabulary;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder> {

    private Context context;
    private List<Vocabulary> vocabularyList;

    @Inject
    public VocabularyAdapter() {
        vocabularyList = new ArrayList<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setVocabularyList(List<Vocabulary> vocabularyList) {
        this.vocabularyList = vocabularyList;
    }

    @Override
    public VocabularyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_vocabulary, parent, false);
        return new VocabularyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VocabularyViewHolder holder, int position) {
        Vocabulary vocabulary = vocabularyList.get(position);

        holder.characterTextView.setText(vocabulary.character);
        holder.kanaTextView.setText(vocabulary.kana);
        holder.meaningTextView.setText(vocabulary.meaning);
    }

    @Override
    public int getItemCount() {
        return vocabularyList.size();
    }


    public class VocabularyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.characterTextView)
        TextView characterTextView;
        @BindView(R.id.kanaTextView)
        TextView kanaTextView;
        @BindView(R.id.meaningTextView)
        TextView meaningTextView;

        public VocabularyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
