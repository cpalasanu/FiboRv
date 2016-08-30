package com.example.cristi.fiborv.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cristi.fiborv.R;

public class FiboAdapter extends RecyclerView.Adapter<FiboAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView index;
        private TextView fiboNumber;

        public ViewHolder(View itemView) {
            super(itemView);

            index = (TextView) itemView.findViewById(R.id.fibo_index);
            fiboNumber = (TextView) itemView.findViewById(R.id.fibo_number);
        }

        public TextView getFiboTextView() {
            return fiboNumber;
        }

        public TextView getIndexTextView() {
            return index;
        }
    }

    private String[] dataSet;

    public FiboAdapter(String[] dataSet) {
        this.dataSet = dataSet;
    }

    public void updateDataSet(String[] dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getIndexTextView().setText(String.valueOf(position));
        holder.getFiboTextView().setText(dataSet[position]);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
