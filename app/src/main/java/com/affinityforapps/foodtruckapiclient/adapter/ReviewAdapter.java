package com.affinityforapps.foodtruckapiclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.affinityforapps.foodtruckapiclient.R;
import com.affinityforapps.foodtruckapiclient.holder.ReviewHolder;
import com.affinityforapps.foodtruckapiclient.model.FoodTruckReview;

import java.util.ArrayList;

/**
 * Created by stevensherry on 4/26/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {

    private ArrayList<FoodTruckReview> reviews;

    public ReviewAdapter(ArrayList<FoodTruckReview> reviews){
        this.reviews = reviews;
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        final FoodTruckReview review = reviews.get(position);
        holder.updateUI(review);
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View reviewCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_review, parent, false);
        return new ReviewHolder(reviewCard);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
