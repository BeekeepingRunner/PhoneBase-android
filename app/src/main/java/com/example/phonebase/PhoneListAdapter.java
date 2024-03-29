package com.example.phonebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Phone> phoneList;

    private OnItemClickListener mOnItemClickListener;

    public PhoneListAdapter(Activity context) {
        this.layoutInflater = context.getLayoutInflater();
        this.phoneList = null;
        // activity will be informed about phone selection
        mOnItemClickListener = (OnItemClickListener) context;
    }

    class PhoneViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView producerTextView;
        private TextView modelTextView;

        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);

            producerTextView = itemView.findViewById(R.id.producerTextView);
            modelTextView = itemView.findViewById(R.id.modelTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int rowNumber = getAdapterPosition();
            mOnItemClickListener.onItemClickListener(phoneList.get(rowNumber));
        }
    }

    // Implemented by the main activity, it informs which element has been chosen
    interface OnItemClickListener {
        void onItemClickListener(Phone phone);
    }

    // Creates main layout element and returns ViewHolder for each created row
    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = layoutInflater.inflate(R.layout.list_row, parent, false);
        return new PhoneViewHolder(row);
    }

    // Fills each row with data from source, every time a row has to be displayed
    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {

        holder.producerTextView.setText(phoneList.get(position).getManufacturer());
        holder.modelTextView.setText(phoneList.get(position).getModel());
    }

    @Override
    public int getItemCount() {
        if (phoneList != null)
            return phoneList.size();
        return 0;
    }

    // Enables update of the data in the adapter, and in consequence, the data displayed in the
    // RecyclerView
    public void setPhoneList(List<Phone> changedPhoneList) {
        this.phoneList = changedPhoneList;
        notifyDataSetChanged();
    }
}
