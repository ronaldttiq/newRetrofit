package com.bs.newretrofitbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.newretrofitbasic.data.Data;
import com.bs.newretrofitbasic.interfaces.DataInterfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView textData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       textData = findViewById(R.id.textoData);

        // añadir la url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //llamar a la interface
        DataInterfaces dataInterfaces = retrofit.create(DataInterfaces.class);

        // llamar a la lista
        Call<List<Data>> call = dataInterfaces.getPost();

        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if(!response.isSuccessful()){
                    textData.setText("Code: " + response.code());

                    return;
                }
                List<Data> data = response.body();
                for (Data data1 : data){
                    String  content="";
                    Log.d("mensasje", content);
                    Toast.makeText(MainActivity.this, content, Toast.LENGTH_LONG).show();
                    content += data1.getId()+"\n";
                    content += data1.getUserId()+"\n";
                    content += data1.getTitle()+"\n";
                    content += data1.getText()+"\n";

                    //pintar la informacion
                    textData.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                textData.setText(t.getMessage());
                Toast.makeText(MainActivity.this, "Error en la consulta", Toast.LENGTH_LONG).show();

            }
        });


    }
}