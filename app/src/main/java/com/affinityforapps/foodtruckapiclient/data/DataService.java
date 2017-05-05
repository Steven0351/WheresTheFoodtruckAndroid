package com.affinityforapps.foodtruckapiclient.data;

import android.content.Context;
import android.util.Log;

import com.affinityforapps.foodtruckapiclient.activities.FoodTrucksListActivity;
import com.affinityforapps.foodtruckapiclient.activities.ReviewsActivity;
import com.affinityforapps.foodtruckapiclient.constants.Constants;
import com.affinityforapps.foodtruckapiclient.model.FoodTruck;
import com.affinityforapps.foodtruckapiclient.model.FoodTruckReview;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by stevensherry on 4/25/17.
 */

public class DataService {

    private static DataService instance = new DataService();

    public static DataService getInstance() {
        return instance;
    }

    private DataService() {
    }

    // Request all the FoodTrucks

    public ArrayList<FoodTruck> downloadAllFoodTrucks(Context context, final FoodTrucksListActivity.TrucksDownloaded listener){
        String url = Constants.GET_FOOD_TRUCKS;
        final ArrayList<FoodTruck> foodTruckList = new ArrayList<>();

        final JsonArrayRequest getTrucks = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response.toString());

                try {
                    JSONArray foodTrucks = response;
                    for(int x = 0; x < foodTrucks.length(); x++){
                        JSONObject foodTruck = foodTrucks.getJSONObject(x);
                        String name = foodTruck.getString("name");
                        String id = foodTruck.getString("_id");
                        String foodType = foodTruck.getString("foodType");
                        Double avgCost = foodTruck.getDouble("averageCost");

                        JSONObject geometry = foodTruck.getJSONObject("geometry");
                        JSONObject coordinates = geometry.getJSONObject("coordinates");

                        Double latitude = coordinates.getDouble("lat");
                        Double longitude = coordinates.getDouble("long");

                        FoodTruck newFoodTruck = new FoodTruck(id,name,foodType,avgCost,latitude,longitude);
                        foodTruckList.add(newFoodTruck);
                    }
                } catch (JSONException e) {
                    Log.v("JSON", "EXC" + e.getLocalizedMessage());
                }
                listener.success(true);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.v("API","Err" + error.getLocalizedMessage());
            }
        });

        Volley.newRequestQueue(context).add(getTrucks);
        return foodTruckList;
    }

    // Request all the FoodTrucks Reviews

    public ArrayList<FoodTruckReview> downloadReviews(Context context, FoodTruck foodTruck, final ReviewsActivity.ReviewInterface listener){
        String url = Constants.GET_REVIEWS + foodTruck.getId();
        final ArrayList<FoodTruckReview> reviewsList = new ArrayList<>();

        final JsonArrayRequest getReviews = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response.toString());

                try {
                    JSONArray reviews = response;
                    for(int x = 0; x < reviews.length(); x++){
                        JSONObject review = reviews.getJSONObject(x);
                        String title = review.getString("title");
                        String id = review.getString("_id");
                        String text = review.getString("text");



                        FoodTruckReview newFoodTruckReview = new FoodTruckReview(id,title,text);
                        reviewsList.add(newFoodTruckReview);
                    }
                } catch (JSONException e) {
                    Log.v("JSON", "EXC" + e.getLocalizedMessage());
                }
                listener.success(true);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.v("API","Err" + error.getLocalizedMessage());
            }
        });

        Volley.newRequestQueue(context).add(getReviews);
        return reviewsList;
    }

}
