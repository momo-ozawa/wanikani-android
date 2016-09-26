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
import com.mozawa.wanikaniandroid.data.model.Radicals;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadicalsAdapter extends RecyclerView.Adapter<RadicalsAdapter.RadicalsViewHolder> {

    private Context context;
    private List<Radicals.RadicalInformation> radicalInformationList;

    @Inject
    public RadicalsAdapter() {
        radicalInformationList = new ArrayList<>();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setRadicalInformationList(List<Radicals.RadicalInformation> radicalInformationList) {
        this.radicalInformationList = radicalInformationList;
    }

    @Override
    public RadicalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.item_radical, parent, false);
        return new RadicalsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RadicalsViewHolder holder, int position) {
        Radicals.RadicalInformation radical = radicalInformationList.get(position);

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

        int color = -1;
        switch (radical.userSpecific.srs) {
            case "apprentice":
                color = R.color.apprentice;
                break;
            case "guru":
                color = R.color.guru;
                break;
            case "master":
                color = R.color.master;
                break;
            case "enlightened":
                color = R.color.enlightened;
                break;
            case "burned":
                color = R.color.burned;
                break;
        }
        holder.srsLevelView.setBackgroundColor(ContextCompat.getColor(context, color));
    }

    @Override
    public int getItemCount() {
        return radicalInformationList.size();
    }

    public class RadicalsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.characterTextView)
        TextView characterTextView;
        @BindView(R.id.characterImageView)
        ImageView characterImageView;
        @BindView(R.id.meaningTextView)
        TextView meaningTextView;
        @BindView(R.id.srsLevelView)
        View srsLevelView;

        public RadicalsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
