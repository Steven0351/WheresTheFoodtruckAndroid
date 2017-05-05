package com.affinityforapps.foodtruckapiclient.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.affinityforapps.foodtruckapiclient.R;
import com.affinityforapps.foodtruckapiclient.adapter.FoodTruckAdapter;
import com.affinityforapps.foodtruckapiclient.adapter.ReviewAdapter;
import com.affinityforapps.foodtruckapiclient.data.DataService;
import com.affinityforapps.foodtruckapiclient.model.FoodTruck;
import com.affinityforapps.foodtruckapiclient.model.FoodTruckReview;
import com.affinityforapps.foodtruckapiclient.view.ItemDecorator;

import java.util.ArrayList;

public class ReviewsActivity extends AppCompatActivity {

    private FoodTruck foodTruck;
    private ArrayList<FoodTruckReview> reviews = new ArrayList<>();
    private ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        System.out.println("We're in the reviews");

        foodTruck = getIntent().getParcelableExtra(FoodTruckDetailActivity.EXTRA_ITEM_TRUCK);
        System.out.println(foodTruck.getName());

        ReviewInterface listener = new ReviewInterface() {
            @Override
            public void success(Boolean success) {
                if (success) {
                    setUpRecycler();
                    if(reviews.size() == 0){
                        Toast.makeText(getBaseContext(), "No reviews for " + foodTruck.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        reviews = DataService.getInstance().downloadReviews(this, foodTruck, listener);
    }

    public interface ReviewInterface {
        void success(Boolean success);
    }

    private void setUpRecycler(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_reviews);
        recyclerView.setHasFixedSize(true);
        adapter = new ReviewAdapter(reviews);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDecorator(0,0,0,10));
    }

}
