package com.example.timscontact.ui.contacts;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timscontact.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> implements Filterable {

    private ArrayList<ContactRecord> contactRecordArrayList;
    private ArrayList<ContactRecord> fullList;

    public ContactAdapter(ArrayList<ContactRecord> contactRecordArrayList) {
        this.contactRecordArrayList = contactRecordArrayList;
        fullList = new ArrayList<>();
        fullList.addAll(contactRecordArrayList);
    }

    public void setContactRecordArrayList(ArrayList<ContactRecord> contactRecordArrayList) {
        this.contactRecordArrayList = contactRecordArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_contact_detail, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ContactRecord record = contactRecordArrayList.get(position);
        holder.name.setText(record.getFullname());
        holder.number.setText("" + record.getNumber());
        holder.address.setText(record.getAddress());
        // TODO: image side
        try {
            Bitmap bitmap = ConvertImage.StringToBitMap(record.getContactImg());
            holder.contact_img.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return contactRecordArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return listFilter;
    }
    Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<ContactRecord> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(fullList);
            }
            else {
                String filteredPattern = charSequence.toString().toLowerCase().trim();

                for(ContactRecord record : fullList){
                    if(record.getFullname().contains(filteredPattern) ){
                        filteredList.add(record);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            contactRecordArrayList.clear();
            contactRecordArrayList.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView number;
        private TextView address;
        private ImageView contact_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.det_name);
            number = itemView.findViewById(R.id.det_number);
            address = itemView.findViewById(R.id.det_address);
            contact_img = itemView.findViewById(R.id.det_img);
        }
    }
}
