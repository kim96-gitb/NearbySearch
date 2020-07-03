package com.test.nearbysearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.nearbysearch.model.Store;

import java.util.ArrayList;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHoler> {

    Context context;
    ArrayList<Store> storeArrayList = new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<Store> storeArrayList) {
        this.context = context;
        this.storeArrayList = storeArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,
                false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHoler holder, int position) {
        Store store = storeArrayList.get(position);
        String name = store.getName();
        String vicinity = store.getVicinity();

        holder.txtname.setText(name);
        holder.txtgps.setText(vicinity);

    }

    @Override
    public int getItemCount() {
        return storeArrayList.size();
    }


    public class  ViewHoler extends RecyclerView.ViewHolder{

        TextView txtname;
        TextView txtgps;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.txtname);
            txtgps = itemView.findViewById(R.id.txtgps);


        }
    }
}
