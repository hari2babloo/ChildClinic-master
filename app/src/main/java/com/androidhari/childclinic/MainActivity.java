package com.androidhari.childclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button cal;
    EditText ag,ht,wt;
    TextView res;
    ArrayList<String> locList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cal = (Button)findViewById(R.id.calculate);
        ag = (EditText)findViewById(R.id.age);
        ht = (EditText)findViewById(R.id.height);
        wt = (EditText)findViewById(R.id.weight);
        res = (TextView)findViewById(R.id.result);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadJSONFromAsset();
            }
        });

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {


            //String jsonLocation = AssetJSONFile("formules.json", context);

            InputStream is = getAssets().open("document.json");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
      //      Log.e("Data",json);
            JSONObject  jsonRootObject = new JSONObject(json);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Boys");

            for(int i=0; i < jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String age = jsonObject.optString("age").toString();
               String height = jsonObject.optString("height").toString();
                String weig = jsonObject.optString("value").toString();
//                float salary = Float.parseFloat(jsonObject.optString("weight").toString());

                String agu = ag.getText().toString();

                 locList = new ArrayList<>();

                locList.add(age);
                locList.add(height);



if (age.equalsIgnoreCase(agu)){


String actheight = ht.getText().toString();
    int n1 = Integer.parseInt(actheight);
    int n2= Integer.parseInt(height);
    int hh = n1-n2;
    String actwt = wt.getText().toString();
    int w1 = Integer.parseInt(actwt);
    int w2 = Integer.parseInt(weig);
    int www = w1-w2;

  //  res.setText( "Standard Height:"+height);
   res.setText( "Standard Height:"+height+    " Child Height is : "+   Integer.toString(hh)+ "Standard Weight:"+weig+"Child Weight is :"+Integer.toString(www));

  //  Log.e("age nad height",Integer.toString(hh));

}

            }



        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;


    }

    private class MyLocations {

        String age;
        String height;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
