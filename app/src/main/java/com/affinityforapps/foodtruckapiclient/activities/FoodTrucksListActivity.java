package com.affinityforapps.foodtruckapiclient.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.affinityforapps.foodtruckapiclient.R;
import com.affinityforapps.foodtruckapiclient.adapter.FoodTruckAdapter;
import com.affinityforapps.foodtruckapiclient.data.DataService;
import com.affinityforapps.foodtruckapiclient.model.FoodTruck;
import com.affinityforapps.foodtruckapiclient.view.ItemDecorator;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FoodTrucksListActivity extends AppCompatActivity {

    private FoodTruckAdapter adapter;
    private ArrayList<FoodTruck> trucks = new ArrayList<>();
    private static FoodTrucksListActivity foodTrucksListActivity;
    public static final String EXTRA_ITEM_TRUCK = "TRUCK";

    public static FoodTrucksListActivity getFoodTrucksListActivity() {
        return foodTrucksListActivity;
    }

    public static void setFoodTrucksListActivity(FoodTrucksListActivity foodTrucksListActivity) {
        FoodTrucksListActivity.foodTrucksListActivity = foodTrucksListActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_trucks_list);

        foodTrucksListActivity.setFoodTrucksListActivity(this);

        TrucksDownloaded listener = new TrucksDownloaded() {
            @Override
            public void success(boolean success) {
                if (success) {
                    setUpRecycler();
                }
            }
        };

        trucks = DataService.getInstance().downloadAllFoodTrucks(this, listener);
    }

    private void setUpRecycler(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_foodtruck);
        recyclerView.setHasFixedSize(true);
        adapter = new FoodTruckAdapter(trucks);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDecorator(0,0,0,10));
    }

    public interface TrucksDownloaded {
        void success(boolean success);
    }

    public void loadFoodTruckDetailActivity(FoodTruck truck){
        Intent intent = new Intent(FoodTrucksListActivity.this, FoodTruckDetailActivity.class);
        intent.putExtra(FoodTrucksListActivity.EXTRA_ITEM_TRUCK, truck);
        startActivity(intent);
    }

}

