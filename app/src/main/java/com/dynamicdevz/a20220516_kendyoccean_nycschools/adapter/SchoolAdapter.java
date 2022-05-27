package com.dynamicdevz.a20220516_kendyoccean_nycschools.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dynamicdevz.a20220516_kendyoccean_nycschools.R;
import com.dynamicdevz.a20220516_kendyoccean_nycschools.model.Schools;

import java.util.ArrayList;
import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> {

    private List<Schools> dataSet;
    private DetailsHandler handlerDetailsClick;

    public void changeDataSet(List<Schools> newSchools) {
        dataSet.addAll(newSchools);
        notifyDataSetChanged();
    }

    public SchoolAdapter(DetailsHandler clickHandler) {
        handlerDetailsClick = clickHandler;
        dataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.school_item_card, parent, false);
        return new SchoolViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolViewHolder holder, int position) {
        holder.bind(dataSet.get(position), handlerDetailsClick);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class SchoolViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView, addressSchool, webSiteSchool;
        public Button detailsButton;

        public SchoolViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.school_name);
            addressSchool = (TextView) itemView.findViewById(R.id.school_address);
            webSiteSchool = (TextView) itemView.findViewById(R.id.school_website);
            detailsButton = (Button) itemView.findViewById(R.id.school_scores);
        }

        public void bind(Schools school, DetailsHandler handlerClick) {
            nameTextView.setText(school.getSchoolName());
            addressSchool.setText(school.getLocation());
            webSiteSchool.setText(school.getWebsite());

            detailsButton.setOnClickListener(view -> {
                handlerClick.handleDetailsClick(school);
            });
        }
    }
}
