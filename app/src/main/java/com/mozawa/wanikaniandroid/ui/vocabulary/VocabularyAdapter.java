package com.mozawa.wanikaniandroid.ui.vocabulary;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder> {

    private Context context;
    private List<Vocabulary.VocabularyInformation> vocabularyInformationList;

    @Inject
    public VocabularyAdapter() {
        vocabularyInformationList = new ArrayList<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setRadicalInformationList(List<Vocabulary.VocabularyInformation> vocabularyInformationList) {
        this.vocabularyInformationList = vocabularyInformationList;
    }

    @Override
    public VocabularyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_vocabulary, parent, false);
        return new VocabularyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VocabularyViewHolder holder, int position) {
        Vocabulary.VocabularyInformation vocabulary = vocabularyInformationList.get(position);
    }

    @Override
    public int getItemCount() {
        return vocabularyInformationList.size();
    }


    public class VocabularyViewHolder extends RecyclerView.ViewHolder {

        public VocabularyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
