package ru.profit_group.scorocodesdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

public class TestActivity extends AppCompatActivity {

    public static final String APP_ID = "305ffd6cc32832f6819bf4e4f4707848";
    public static final String CLIENT_KEY = "962066371eefc0d1850a76c7ab14c1dc";
    public static final String ACCESS_KEY = "383499df2748bb4560745d5da67f5e41";
    private static final String TAG = "SCOROCODE_SDK_TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testGetStatistic();
//        testRegisterUser();
        testLoginUser();
    }

    private void testLoginUser() {

    }

    private void testRegisterUser() {
        HashMap<String, String> doc = new HashMap<>();
        doc.put("exampleField", "Сегодня 18 июня, и это день рождения Мюриэл! Мюриэл сейчас 20. С днём рождения, Мюриэл!");
        doc.put("anotherExampleField", "Не знаю, что и сказать. Когда-то я хотел быть астрофизиком. К сожалению, это правда.");

        ScorocodeSdk.registerUser(APP_ID, CLIENT_KEY, "PeterStaranchukTest1", "petr.staranchukTest1@gmail.com", "qwertyTest1", doc,
                new Callback<ResponseCodes>() {
                    @Override
                    public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                        Log.d(TAG, "REGISTER USER: SUCCESS");
                    }

                    @Override
                    public void onFailure(Call<ResponseCodes> call, Throwable t) {
                        Log.d(TAG, "REGISTER USER: FAILURE");
                    }
                });
    }

    private void testGetStatistic() {
        try {
            ScorocodeSdk.getApplicationStatistic(APP_ID, CLIENT_KEY, ACCESS_KEY,
                    new Callback<ResponseAppStatistic>() {
                        @Override
                        public void onResponse(Call<ResponseAppStatistic> call, Response<ResponseAppStatistic> response) {
                            Log.d(TAG, "REGISTER USER: SUCCESS");
                        }

                        @Override
                        public void onFailure(Call<ResponseAppStatistic> call, Throwable t) {
                            Log.d(TAG, "REGISTER USER: FAILURE");
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
