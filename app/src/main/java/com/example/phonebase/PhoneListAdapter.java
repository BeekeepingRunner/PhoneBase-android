package com.example.phonebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {

    LayoutInflater layoutInflater;
    List<Phone> phoneList;

    class PhoneViewHolder extends RecyclerView.ViewHolder {

        TextView producerTextView;
        TextView modelTextView;

        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);

            producerTextView = itemView.findViewById(R.id.producerTextView);
            modelTextView = itemView.findViewById(R.id.modelTextView);
        }
    }

    public PhoneListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.phoneList = null;
    }

    // Creates main layout element and returns ViewHolder for each created row
    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = layoutInflater.inflate(R.layout.list_row, null);
        return new PhoneViewHolder(row);
    }

    // Fills each row with data from source, every time a row has to be displayed
    // ...Is it necessary here?
    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (phoneList != null)
            return phoneList.size();
        return 0;
    }

    // Enables update of the data in the adapter, and in consequence, the data displayed in the
    // RecyclerView
    public void setElementList(List<Phone> changedPhoneList) {
        this.phoneList = changedPhoneList;
        notifyDataSetChanged();
    }
}
