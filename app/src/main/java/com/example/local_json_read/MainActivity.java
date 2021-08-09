package com.example.local_json_read;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> numberList=new ArrayList<>();
    /*List<Fields> field=new ArrayList<>();*/
    CityList cityList = new CityList();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.nesne);



        get_json();


    }

    private void loadData() {
        try {
            //Load File
            BufferedReader jsonReader =
                    new BufferedReader(
                            new InputStreamReader(this.getResources()
                                    .openRawResource(R.raw.jsondata)));
            StringBuilder jsonBuilder =
                    new StringBuilder();
            for (String line = null;
                 (line = jsonReader.readLine()) != null;) {

                    jsonBuilder.append(line).append("\n");
            }

            Gson gson = new Gson();
            cityList = gson.fromJson(jsonBuilder.toString(),CityList.class);

            Log.d("Deneme",cityList.getCityDetail().get(0).getName());


        } catch (FileNotFoundException e) {
            Log.e("jsonFile", "file not found");
        } catch (IOException e) {
            Log.e("jsonFile", "ioerror");
        }
    }

    public void spinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        List<String> spinnerData = new ArrayList<>();
        for(int i=0;i<cityList.getCityDetail().size();i++){
            spinnerData.add(cityList.getCityDetail().get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }




    public void get_json(){
        String json = null;
        try {
            InputStream is = this.getAssets().open("dataTek.json");       //TODO Json File  name from assets folder
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

        }
       //TODO pass array object name
        Fields fields=new Fields();
        JSONArray  jsonArray;
        try {
            JSONObject obj = new JSONObject(json);
            jsonArray = obj.getJSONArray("value");

            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i).getJSONObject("fields");
                String title=object.getString("Title");
                String foodCategory=object.getString("FoodCategory");
                String colorie=object.getString("Calorie");

                fields.setCalori(colorie);
                fields.setFoodCategory(foodCategory);
                fields.setTitle(title);
                numberList.add("\n"+title+"\n"+foodCategory+"\n"+colorie);

            }
            textView.setText(numberList.toString());
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }

    }

}




