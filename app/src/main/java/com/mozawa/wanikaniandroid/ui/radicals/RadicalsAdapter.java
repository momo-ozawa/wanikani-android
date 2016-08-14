package com.mozawa.wanikaniandroid.ui.radicals;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Radicals;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RadicalsAdapter extends RecyclerView.Adapter<RadicalsAdapter.RadicalsViewHolder> {

    private List<Radicals.RadicalInformation> radicalInformationList;

    @Inject
    public RadicalsAdapter() {
        radicalInformationList = new ArrayList<>();
    }

    public void setRadicalInformationList(List<Radicals.RadicalInformation> radicalInformationList) {
        this.radicalInformationList = radicalInformationList;
    }

    @Override
    public RadicalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_radical, parent, false);
        return new RadicalsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RadicalsViewHolder holder, int position) {
        Radicals.RadicalInformation radical = radicalInformationList.get(position);
//        holder.characterTextView.setText(radical.getCharacter());
        holder.meaningTextView.setText(radical.meaning);
    }

    @Override
    public int getItemCount() {
        return radicalInformationList.size();
    }

    public class RadicalsViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.characterTextView)
        TextView characterTextView;
//        @BindView(R.id.meaningTextView)
        TextView meaningTextView;

        public RadicalsViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            characterTextView = (TextView) itemView.findViewById(R.id.characterTextView);
            meaningTextView = (TextView) itemView.findViewById(R.id.meaningTextView);
        }
    }
}
