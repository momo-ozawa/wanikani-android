package com.mozawa.wanikaniandroid.ui.kanji;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.data.model.ListHeader;
import com.mozawa.wanikaniandroid.data.model.ListItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.KanjiViewHolder> {

    public static final int HEADER_VIEW_TYPE = 0;
    public static final int KANJI_VIEW_TYPE = 1;

    private Context context;
    private List<ListItem> listItems;
    private KanjiClickedListener kanjiClickedListener;

    @Inject
    public KanjiAdapter() {
        listItems = new ArrayList<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }

    public void setKanjiClickedListener(KanjiClickedListener kanjiClickedListener) {
        this.kanjiClickedListener = kanjiClickedListener;
    }

    @Override
    public KanjiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView;

        if (viewType == HEADER_VIEW_TYPE) {
            itemView = layoutInflater.inflate(R.layout.item_header, parent, false);
            return new KanjiViewHolder(itemView, viewType);
        } else if (viewType == KANJI_VIEW_TYPE) {
            itemView = layoutInflater.inflate(R.layout.item_kanji, parent, false);
            return new KanjiViewHolder(itemView, viewType);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(KanjiViewHolder holder, int position) {
        ListItem item = listItems.get(position);

        switch (item.getType()) {
            case ListHeader.TYPE:
                final ListHeader header = (ListHeader) item;
                holder.levelTextView.setText(header.title);
                break;
            case Kanji.TYPE:
                final Kanji kanji = (Kanji) item;
                holder.characterTextView.setText(kanji.character);
                holder.onyomiTextView.setText(kanji.onyomi);
                holder.meaningTextView.setText(kanji.meaning);

                // On click
                holder.kanjiView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kanjiClickedListener.onKanjiClicked(kanji);
                    }
                });

                break;
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        ListItem listItem = listItems.get(position);
        switch (listItem.getType()) {
            case ListHeader.TYPE:
                return 0;
            case Kanji.TYPE:
                return 1;
        }
        return -1;
    }

    public class KanjiViewHolder extends RecyclerView.ViewHolder {

        // Header
        TextView levelTextView;

        // Kanji
        View kanjiView;
        TextView characterTextView;
        TextView onyomiTextView;
        TextView meaningTextView;

        public KanjiViewHolder(View itemView, int itemViewType) {
            super(itemView);
            if (itemViewType == HEADER_VIEW_TYPE) {
                levelTextView = (TextView) itemView.findViewById(R.id.levelTextView);
            } else if (itemViewType == KANJI_VIEW_TYPE) {
                kanjiView = itemView.findViewById(R.id.kanjiView);
                characterTextView = (TextView) itemView.findViewById(R.id.characterTextView);
                onyomiTextView = (TextView) itemView.findViewById(R.id.onyomiTextView);
                meaningTextView = (TextView) itemView.findViewById(R.id.meaningTextView);
            }
        }
    }

    public interface KanjiClickedListener {

        void onKanjiClicked(Kanji kanji);

    }
}
