package com.example.licenta.dto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.licenta.R;
import com.example.licenta.dto.ActiveLoanRecord;

import java.util.List;

public class ActiveLoanRecordAdapter extends ArrayAdapter<ActiveLoanRecord> {

    private Context context;
    private int resource;
    private List<ActiveLoanRecord> activeLoanRecordList;
    private LayoutInflater inflater;

    public ActiveLoanRecordAdapter(@NonNull Context context, int resource, @NonNull List<ActiveLoanRecord> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.activeLoanRecordList = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        ActiveLoanRecord activeLoanRecord = activeLoanRecordList.get(position);

        addRecordId(activeLoanRecord.getRecordId(), view);
        addDate(activeLoanRecord.getDateOfRecord(), view);
        addAmountPaid(activeLoanRecord.getAmountPaid(), view);

        return view;
    }

    private void addRecordId(long recordId, View view) {
        TextView textView = view.findViewById(R.id.id_tv_lv_recordId);
        textView.append(" " + recordId);
    }

    private void addDate(String date, View view) {
        TextView textView = view.findViewById(R.id.id_tv_recordDate);
        textView.append(" " + date);
    }

    private void addAmountPaid(double amountPaid, View view) {
        TextView textView = view.findViewById(R.id.id_tv_recordAmountPaid);
        textView.append(" " + amountPaid);
    }

}
