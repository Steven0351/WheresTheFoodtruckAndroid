package com.affinityforapps.foodtruckapiclient.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.affinityforapps.foodtruckapiclient.R;
import com.affinityforapps.foodtruckapiclient.model.FoodTruck;
import com.affinityforapps.foodtruckapiclient.model.FoodTruckReview;

/**
 * Created by stevensherry on 4/26/17.
 */

public class ReviewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView text;

    public ReviewHolder(View itemView){
        super(itemView);
        this.title = (TextView) itemView.findViewById(R.id.review_title);
        this.text = (TextView) itemView.findViewById(R.id.review_text);
    }

    public void updateUI(FoodTruckReview review){
        title.setText(review.getTitle());
        text.setText(review.getText());
    }
}
