package cs.ubru.covid19th;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String url = "https://covid19.th-stat.com/api/open/today";
    private TextView tvConfirmed, tvRecover, tvHospitalize, tvDeath, tvNewConfirmed, tvUpdateDate;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid19_new);

        initComponent();

        getData();
    }

    private void getData() {
        JsonObjectRequest objectRequest =
                new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    int confirmed = response.getInt("Confirmed");
                                    int recover = response.getInt("Recovered");
                                    int hospitalize = response.getInt("Hospitalized");
                                    int death = response.getInt("Deaths");
                                    int newConfirmed = response.getInt("NewConfirmed");
                                    String updateDate = response.getString("UpdateDate");

                                    tvConfirmed.setText(confirmed + "");
                                    tvRecover.setText(recover + "");
                                    tvHospitalize.setText(hospitalize + "");
                                    tvDeath.setText(death + "");
                                    tvNewConfirmed.setText(newConfirmed + "");
                                    tvUpdateDate.setText(updateDate);

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
        queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(objectRequest);
    }

    private void initComponent() {
        tvConfirmed = findViewById(R.id.tv_confirmed_number);
        tvRecover = findViewById(R.id.tv_recover_number);
        tvHospitalize = findViewById(R.id.tv_hospitalize_number);
        tvDeath = findViewById(R.id.tv_death_number);
        tvNewConfirmed = findViewById(R.id.tv_new_hospitalize_number);
        tvUpdateDate = findViewById(R.id.tv_update_date);
    }
}