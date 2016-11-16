package com.androidhari.childclinic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class Secondtrial extends AppCompatActivity {

    Button cal;
    EditText ag,ht,wt;
    TextView res,neg3sd,ned2sd,neg1sd,med,pos1sd,pos2sd,pos3sd,third,fiftnth,median,egiht5,nine7;
    ArrayList<String> locList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondtrial);
        cal = (Button)findViewById(R.id.calculate);
        ag = (EditText)findViewById(R.id.age);
        ht = (EditText)findViewById(R.id.height);
        wt = (EditText)findViewById(R.id.weight);
        res = (TextView)findViewById(R.id.result);
        neg3sd = (TextView)findViewById(R.id.textView3);
        ned2sd =(TextView)findViewById(R.id.textView4);
        neg1sd = (TextView)findViewById(R.id.textView5);
        med = (TextView)findViewById(R.id.textView6);
        pos1sd = (TextView)findViewById(R.id.textView7);;
        pos2sd = (TextView)findViewById(R.id.textView8);
        pos3sd = (TextView)findViewById(R.id.textView9);
         third = (TextView)findViewById(R.id.third);
        fiftnth = (TextView)findViewById(R.id.fiften);
        median = (TextView)findViewById(R.id.med);
        egiht5 = (TextView)findViewById(R.id.eight);
        nine7 = (TextView)findViewById(R.id.nine7);
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

            InputStream is = getAssets().open("boys.json");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
            //      Log.e("Data",json);
            JSONObject jsonRootObject = new JSONObject(json);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Boys");

            for(int i=0; i < jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String age = jsonObject.optString("age").toString();
                String height = jsonObject.optString("height").toString();
                String weig = jsonObject.optString("weight").toString();
                Log.e("Data",age +"0fdg" +height  +"dfsfsd"+ weig);
                neg3sd.setText(jsonObject.optString("neg3SD").toString());
                ned2sd.setText(jsonObject.optString("neg2SD").toString());
                neg1sd.setText(jsonObject.optString("neg1SD").toString());
                med.setText(jsonObject.optString("Medain").toString());
                pos1sd.setText(jsonObject.optString("po1SD").toString());
                pos2sd.setText(jsonObject.optString("po2SD").toString());
                pos3sd.setText(jsonObject.optString("po3SD").toString());
                third.setText(jsonObject.optString("m3rd").toString());
                fiftnth.setText(jsonObject.optString("m3rd").toString());
                median.setText(jsonObject.optString("m15th").toString());
                egiht5.setText(jsonObject.optString("m85th").toString());
                nine7.setText(jsonObject.optString("m97th").toString());


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
              //      res.setText( "Standard Height:"+height+    " Child Height is : "+   Integer.toString(hh)+ "Standard Weight:"+weig+"Child Weight is :"+Integer.toString(www));
                    //  Log.e("age nad height",Integer.toString(hh));
                    res.setText( "Standard Height:"+height+ " \n Child Weight is :"+Integer.toString(www));
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
