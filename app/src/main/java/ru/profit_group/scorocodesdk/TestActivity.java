package ru.profit_group.scorocodesdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.profit_group.scorocodesdk.pojo_statistic.AppStatistic;

public class TestActivity extends AppCompatActivity {

    public static final String APP_ID = "83121fb3e1b1af31780206af003fc34d";
    public static final String CLIENT_KEY = "6b5b3a41eb77c728c3f7f4ecfb51662f";
    public static final String ACCESS_KEY = "383499df2748bb4560745d5da67f5e41";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testGetStatistic();
    }

    private void testGetStatistic() {
        try {
            ScorocodeSdk.getApplicationStatistic(APP_ID, CLIENT_KEY, ACCESS_KEY,
                            new Callback<AppStatistic>() {
                                @Override
                                public void onResponse(Call<AppStatistic> call, Response<AppStatistic> response) {
                                    Log.d("", "");
                                }

                                @Override
                                public void onFailure(Call<AppStatistic> call, Throwable t) {
                                    Log.d("", "");
                                }
                            }
                    );
            Log.d("", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
