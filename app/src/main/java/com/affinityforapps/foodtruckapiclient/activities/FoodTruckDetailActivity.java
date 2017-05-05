package com.affinityforapps.foodtruckapiclient.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.affinityforapps.foodtruckapiclient.model.FoodTruck;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.affinityforapps.foodtruckapiclient.R;

public class FoodTruckDetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FoodTruck foodTruck;
    private TextView truckName;
    private TextView foodType;
    private TextView avgCost;
    private Button addReviewButton;
    private Button viewReviewButton;
    private Button modifyTruckButton;

    public static final String EXTRA_ITEM_TRUCK = "TRUCK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        truckName = (TextView) findViewById(R.id.detail_truck_name);
        foodType = (TextView) findViewById(R.id.detail_food_type);
        avgCost = (TextView) findViewById(R.id.detail_food_cost);
        addReviewButton = (Button) findViewById(R.id.add_review_button);
        viewReviewButton = (Button) findViewById(R.id.view_reviews_button);
        modifyTruckButton = (Button) findViewById(R.id.modify_truck_button);

        foodTruck = getIntent().getParcelableExtra(FoodTrucksListActivity.EXTRA_ITEM_TRUCK);
        updateUI();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        viewReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadReviews(foodTruck);
            }
        });
        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLogin();
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker to foodTruckLocation and move the camera
        LatLng foodTruckLocation = new LatLng(foodTruck.getLatitude(), foodTruck.getLongitude());
        mMap.addMarker(new MarkerOptions().position(foodTruckLocation).title(foodTruck.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(foodTruckLocation,10));
        setUpMap();
    }

    private void updateUI(){
        truckName.setText(foodTruck.getName());
        foodType.setText(foodTruck.getFoodType());
        avgCost.setText("$" + Double.toString(foodTruck.getAvgCost()));
    }

    private void setUpMap(){
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(true);
        mMap.setIndoorEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public void loadReviews(FoodTruck truck){
        Intent intent = new Intent(FoodTruckDetailActivity.this, ReviewsActivity.class);
        intent.putExtra(FoodTruckDetailActivity.EXTRA_ITEM_TRUCK, truck);
        startActivity(intent);
    }

    public void loadLogin(){
        Intent intent = new Intent(FoodTruckDetailActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
