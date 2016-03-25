                package com.example.hp.citysearchapp;

                import android.app.DownloadManager;
                import android.app.ProgressDialog;
                import android.content.Intent;
                import android.support.v7.app.AppCompatActivity;
                import android.os.Bundle;
                import android.util.Log;
                import android.widget.TextView;
                import android.widget.Toast;

                import com.android.volley.Request;
                import com.android.volley.RequestQueue;
                import com.android.volley.Response;
                import com.android.volley.VolleyError;
                import com.android.volley.VolleyLog;
                import com.android.volley.toolbox.JsonObjectRequest;
                import com.android.volley.toolbox.StringRequest;
                import com.android.volley.toolbox.Volley;

                import org.json.JSONArray;
                import org.json.JSONException;
                import org.json.JSONObject;
                import org.json.JSONException;
                import org.json.JSONObject;

                import java.io.File;
                import java.io.FileInputStream;
                import java.io.FileNotFoundException;
                import java.io.IOException;
                import java.io.InputStream;

                public class CityDetailsActivity extends AppCompatActivity {

                    TextView CityCode , CityName , StateCode , StateName , CountryCode ,CountryName,ISDCode,GPlaceID,Latitude,Longitude,ActiveStatus;
                    public static String url;
                    String getId;
                    TextView tv;
                    private ProgressDialog pDialog;

                    @Override
                    protected void onCreate(final Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        setContentView(R.layout.activity_city_details);


                        CityCode=(TextView)findViewById(R.id.citycode);
                        tv=(TextView)findViewById(R.id.countrycode_one);
                        CityName=(TextView)findViewById(R.id.cityname);
                        StateCode=(TextView)findViewById(R.id.statecode);
                        StateName=(TextView)findViewById(R.id.statename);
                        CountryCode=(TextView)findViewById(R.id.countrycode);
                        CountryName=(TextView)findViewById(R.id.countryname);
                        ISDCode=(TextView)findViewById(R.id.isdcode);
                        GPlaceID=(TextView)findViewById(R.id.gplaceid);
                        Latitude=(TextView)findViewById(R.id.latitude);
                        Longitude=(TextView)findViewById(R.id.longitude);
                        ActiveStatus=(TextView)findViewById(R.id.activestatus);

                        //getting the id
                        final Bundle bundle = getIntent().getExtras();
                        getId = bundle.getString("gettingId");
                        //adding id in the url
                        url = "http://test.maheshwari.org/services/testwebservice.asmx/GetCity?cityId=" + getId;

                        //setting up the request queue
                        RequestQueue queue = Volley.newRequestQueue(this);

    // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("resp",response);
                                        try {

                                            JSONObject jsonObject =new JSONObject(response);
                                            //getting citycode
                                            String citycode= String.valueOf(jsonObject.getInt("CityCode"));
                                            //setting citycode
                                            CityCode.setText(citycode);
                                            String cityname=jsonObject.getString("CityName");
                                            CityName.setText(cityname);

                                            //getting the state object
                                            JSONObject state = jsonObject.getJSONObject("State");
                                            String statecode= String.valueOf(state.getInt("StateCode"));
                                            StateCode.setText(statecode);
                                            String statename=state.getString("StateName");
                                            StateName.setText(statename);
                                            String countrycode= String.valueOf(state.getInt("CountryCode"));
                                            CountryCode.setText(countrycode);

                                            //getting the country object
                                            JSONObject country = state.getJSONObject("Country");
                                            String countrycode_country= String.valueOf(country.getInt("CountryCode"));
                                            tv.setText(countrycode_country);
                                            String countryname=country.getString("CountryName");
                                            CountryName.setText(countryname);
                                            String isdcode=country.getString("IsdCode");
                                            ISDCode.setText(isdcode);

                                            //getting the second country object
                                            JSONObject country_second=jsonObject.getJSONObject("Country");
                                            String countrycode_second= String.valueOf(country_second.getInt("CountryCode"));
                                            String countryname_second=country_second.getString("CountryName");
                                            String isdcode_second=country_second.getString("IsdCode");




                                            String gplaceid=jsonObject.getString("GPlaceId");
                                            GPlaceID.setText(gplaceid);
                                            String latitude= String.valueOf(jsonObject.getDouble("Latitude"));
                                            Latitude.setText(latitude);
                                            String longitude= String.valueOf(jsonObject.getDouble("Longitude"));
                                            Longitude.setText(longitude);
                                            String activestatus= String.valueOf(jsonObject.getInt("ActiveStatus"));
                                            ActiveStatus.setText(activestatus);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                tv.setText("That didn't work!");
                            }
                        });
                         // Add the request to the RequestQueue.
                        queue.add(stringRequest);

                    }

                    private void hidePDialog() {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                    }
                }
