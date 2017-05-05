package com.affinityforapps.foodtruckapiclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;

import com.affinityforapps.foodtruckapiclient.R;
import com.affinityforapps.foodtruckapiclient.activities.FoodTrucksListActivity;
import com.affinityforapps.foodtruckapiclient.holder.FoodTruckHolder;
import com.affinityforapps.foodtruckapiclient.model.FoodTruck;

/**
 * Created by stevensherry on 4/21/17.
 */

public class FoodTruckAdapter extends RecyclerView.Adapter<FoodTruckHolder> {

    private ArrayList<FoodTruck> trucks;

    public FoodTruckAdapter(ArrayList<FoodTruck> trucks) {
        this.trucks = trucks;
    }

    @Override
    public void onBindViewHolder(FoodTruckHolder holder, int position) {
        final FoodTruck truck = trucks.get(position);
        holder.updateUI(truck);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                FoodTrucksListActivity.getFoodTrucksListActivity().loadFoodTruckDetailActivity(truck);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trucks.size();
    }

    @Override
    public FoodTruckHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View truckCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_foodtruck, parent, false);
        return new FoodTruckHolder(truckCard);
    }
}
