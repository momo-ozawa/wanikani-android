package com.mozawa.wanikaniandroid.ui.radicals;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.ListHeader;
import com.mozawa.wanikaniandroid.data.model.ListItem;
import com.mozawa.wanikaniandroid.data.model.Radical;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RadicalsAdapter extends RecyclerView.Adapter<RadicalsAdapter.RadicalsViewHolder> {

    public static final int HEADER_VIEW_TYPE = 0;
    public static final int ITEM_VIEW_TYPE = 1;

    private Context context;
    private List<ListItem> listItems;

    @Inject
    public RadicalsAdapter() {
        listItems = new ArrayList<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }

    public boolean isHeader(int position) {
        return getItemViewType(position) == 0;
    }

    @Override
    public RadicalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView;

        if (viewType == HEADER_VIEW_TYPE) {
            itemView = layoutInflater.inflate(R.layout.item_header, parent, false);
            return new RadicalsViewHolder(itemView, viewType);
        } else if (viewType == ITEM_VIEW_TYPE) {
            itemView = layoutInflater.inflate(R.layout.item_radical, parent, false);
            return new RadicalsViewHolder(itemView, viewType);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RadicalsViewHolder holder, int position) {
        ListItem item = listItems.get(position);

        switch (item.getType()) {
            case ListHeader.TYPE:
                final ListHeader header = (ListHeader) item;
                holder.levelTextView.setText(header.title);
                break;
            case Radical.TYPE:
                final Radical radical = (Radical) item;

                if (radical.characterExists()) {
                    holder.characterTextView.setText(radical.character);
                    holder.characterTextView.setVisibility(View.VISIBLE);
                    holder.characterImageView.setVisibility(View.GONE);
                } else {
                    Picasso.with(context).load(radical.image).into(holder.characterImageView);
                    holder.characterImageView.setColorFilter(ContextCompat.getColor(context, android.R.color.tertiary_text_light));
                    holder.characterImageView.setVisibility(View.VISIBLE);
                    holder.characterTextView.setVisibility(View.GONE);
                }

                holder.meaningTextView.setText(radical.meaning);
                break;
        }


//        int color = -1;
//        switch (radical.userSpecific.srs) {
//            case "apprentice":
//                color = R.color.apprentice;
//                break;
//            case "guru":
//                color = R.color.guru;
//                break;
//            case "master":
//                color = R.color.master;
//                break;
//            case "enlightened":
//                color = R.color.enlightened;
//                break;
//            case "burned":
//                color = R.color.burned;
//                break;
//        }
//
//        if (color != -1) {
//            holder.srsLevelView.setBackgroundColor(ContextCompat.getColor(context, color));
//        }

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        String type = listItems.get(position).getType();
        return (type == ListHeader.TYPE) ? HEADER_VIEW_TYPE : ITEM_VIEW_TYPE;
    }

    public class RadicalsViewHolder extends RecyclerView.ViewHolder {

        // Header
        TextView levelTextView;

        // Radical
        TextView characterTextView;
        ImageView characterImageView;
        TextView meaningTextView;
        View srsLevelView;

        public RadicalsViewHolder(View itemView, int itemViewType) {
            super(itemView);
            if (itemViewType == HEADER_VIEW_TYPE) {
                levelTextView = (TextView) itemView.findViewById(R.id.levelTextView);
            } else if (itemViewType == ITEM_VIEW_TYPE) {
                characterTextView = (TextView) itemView.findViewById(R.id.characterTextView);
                characterImageView = (ImageView) itemView.findViewById(R.id.characterImageView);
                meaningTextView = (TextView) itemView.findViewById(R.id.meaningTextView);
                srsLevelView = itemView.findViewById(R.id.srsLevelView);
            }
        }
    }
}
