package com.marketpulse;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MarketDataAdapter extends RecyclerView.Adapter<MarketDataAdapter.ItemViewHolder> {

    final String TAG = "MarketDataAdapter";
    private List<MarketResponseModel> responseModelList;
    private Activity activity;

    public MarketDataAdapter(Activity activity, List<MarketResponseModel> items) {
        this.activity = activity;
        this.responseModelList = items;
    }

    @NonNull
    @Override
    public MarketDataAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_market_data, null);
        return new MarketDataAdapter.ItemViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MarketDataAdapter.ItemViewHolder holder, int position) {
        holder.name.setText(responseModelList.get(position).getName());
        holder.tag.setText(responseModelList.get(position).getTag());
    }

    @Override
    public int getItemCount() {
        return responseModelList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name, tag;

        ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            tag = itemView.findViewById(R.id.tag);
        }
    }
}
