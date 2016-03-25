        package com.example.hp.citysearchapp;

                import android.app.ProgressDialog;
                import android.content.Intent;
                import android.support.design.widget.TextInputLayout;
                import android.support.v7.app.AppCompatActivity;
                import android.os.Bundle;
                import android.support.v7.view.menu.ExpandedMenuView;
                import android.util.Log;
                import android.view.View;
                import android.widget.AdapterView;
                import android.widget.EditText;
                import android.widget.ImageView;
                import android.widget.ListView;
                import android.widget.TextView;
                import android.widget.Toast;

                import com.android.volley.Response;
                import com.android.volley.VolleyError;
                import com.android.volley.VolleyLog;
                import com.android.volley.toolbox.JsonArrayRequest;

                import org.json.JSONArray;
                import org.json.JSONException;
                import org.json.JSONObject;

                import java.util.ArrayList;
                import java.util.HashMap;
                import java.util.List;

        public class MainActivity extends AppCompatActivity {

            private static final String TAG = MainActivity.class.getSimpleName();
            private ProgressDialog pDialog;
            private List<City> cityList = new ArrayList<City>();
            private ListView listView;
            private static String url;
            ImageView searchIcon;
            String idGet;
            String edittextSearch;
            TextInputLayout searchLayout;
            EditText search;
            private CustomListAdapter adapter;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);


                listView = (ListView)findViewById(R.id.city_listView);
                searchLayout=(TextInputLayout)findViewById(R.id.input_layout_search);
                search=(EditText)findViewById(R.id.input_search);
                searchIcon=(ImageView)findViewById(R.id.imageView);

                //list adaper
                adapter = new CustomListAdapter(this,cityList);
                searchIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edittextSearch=search.getText().toString();
                        Log.d("hello2", search.getText().toString());
                        adapter.notifyDataSetChanged();
                        url = "http://test.maheshwari.org/services/testwebservice.asmx/SuggestCity?tryValue="+edittextSearch;
                        parsingMethod();
                    }
                });

                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this,CityDetailsActivity.class);
                        TextView textView = (TextView)view.findViewById(R.id.id);
                        String gettingID=textView.getText().toString();
                      //  Toast.makeText(MainActivity.this, gettingID, Toast.LENGTH_SHORT).show();
                        //sending the id to the city details activity
                        intent.putExtra("gettingId",gettingID);
                        startActivity(intent);
                    }
                });
                                }

            private void parsingMethod() {

                Log.d("hello", url);
                pDialog = new ProgressDialog(this);
                // Showing progress dialog
                pDialog.setMessage("Loading...");
                pDialog.show();

                // Creating volley request obj
                JsonArrayRequest cityReq = new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray jsonArray) {
                                hidePDialog();

                                // Parsing json
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject obj = null;
                                    try {
                                        obj = jsonArray.getJSONObject(i);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    City city = new City();
                                    try {
                                        city.setId(obj.getString("Id"));
                                        city.setTitle(obj.getString("Title"));
                                        city.setDescription(obj.getString("Description"));
                                        city.setExv1(obj.getString("ExtraValue1"));
                                        Log.d("hello",obj.getString("ExtraValue1"));
                                        city.setExv2(obj.getString("ExtraValue2"));
                                        city.setExv3(obj.getString("ExtraValue3"));
                                        city.setExv4(obj.getString("ExtraValue4"));
                                        city.setExv5(obj.getString("ExtraValue5"));
                                        city.setExv6(obj.getString("ExtraValue6"));
                                        city.setExv7(obj.getString("ExtraValue7"));
                                        city.setExv8(obj.getString("ExtraValue8"));
                                        city.setExv9(obj.getString("ExtraValue9"));

                                        cityList.add(city);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }

                                // notifying list adapter about data changes
                                // so that it renders the list view with updated data
                                adapter.notifyDataSetChanged();
                            }

                        }, new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse (VolleyError error){
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        hidePDialog();

                    }

                });


                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(cityReq);
            }

            @Override
            public void onDestroy() {
                super.onDestroy();
                hidePDialog();
            }

            private void hidePDialog() {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
            }


        }