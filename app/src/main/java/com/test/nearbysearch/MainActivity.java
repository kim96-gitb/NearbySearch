package com.test.nearbysearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.test.nearbysearch.model.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url =  "https://maps.googleapis.com/maps/api/place/nearbysearch/json?language=ko&location=37.544147,126.8357822&radius=2000&type=restaurant&key=AIzaSyD3KC2ug6UrcFciDoqR8LrWd98rn59mit0";
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    EditText edtsearch;
    ImageView imgsearch;

    ArrayList<Store> storeArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtsearch = findViewById(R.id.edtsearch);
        imgsearch = findViewById(R.id.imgsearch);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int totalCount = recyclerView.getAdapter().getItemCount();

                if(lastPosition+1 == totalCount){
                    //아이템 추가 ! 입맛에 맞게 설정하시면됩니다.

                    }



                }

        });







        // 발리 라이브러리 이용해서, 호출
        // 로그 찍어본다.
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        getNetworkdata(url);

    }
    // 제이슨 파싱한거 함수처리해서 위에 검색할때 씀
    public void   getNetworkdata(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("AAA", response.toString());
                        try {
                            JSONArray result = response.getJSONArray("results");
                            for(int i = 0; i < result.length(); i++){
                                JSONObject jsonObject = result.getJSONObject(i);
                                JSONObject geometry = jsonObject.getJSONObject("geometry");
                                String name = jsonObject.getString("name");
                                String vicinity = jsonObject.getString("vicinity");
//                                double lat = geometry.getDouble("lat");
//                                double lng = geometry.getDouble("lng");
                                Log.i("AAA",name+vicinity);


                                Store store = new Store(name,vicinity);
                                storeArrayList.add(store);


                            }
                            recyclerViewAdapter = new RecyclerViewAdapter(
                                    MainActivity.this,storeArrayList);
                            recyclerView.setAdapter(recyclerViewAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }


                }
        );
        requestQueue.add(jsonObjectRequest);

    }
}
