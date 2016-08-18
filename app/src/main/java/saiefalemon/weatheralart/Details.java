package saiefalemon.weatheralart;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Details extends Activity {
    ImageView weatherIconImageView;
    TextView dateTV,sunriseTV,sunsetTV,tempForen,tempCelsius,maxTempTV,minTempTV,cityTV,countryTV,conditonTV;
    String forecast,dateString,codeString,sunriseString,sunsetString,tempforenString,tempcelsius,maxTempString,minTempString,cityString,countryString,conditionString;
    final String DEGREE  = "\u00b0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        dateTV = (TextView) findViewById(R.id.dateTV);
        sunriseTV = (TextView) findViewById(R.id.sunriseTV);
        sunsetTV = (TextView) findViewById(R.id.sunsetTV);
        tempForen = (TextView) findViewById(R.id.tempForen);
        tempCelsius = (TextView) findViewById(R.id.tempCelsius);
        maxTempTV = (TextView) findViewById(R.id.maxTempTV);
        minTempTV = (TextView) findViewById(R.id.minTempTV);
        cityTV = (TextView) findViewById(R.id.cityTV);
        countryTV = (TextView) findViewById(R.id.countryTV);
        conditonTV = (TextView) findViewById(R.id.conditonTV);


        Intent cityIntent = getIntent();
        String city = cityIntent.getStringExtra("cityName");


        String URLStrings = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+city+"%22)&format=json";
        new JESONTask().execute(URLStrings);
    }

    public void backToScreen(View view) {
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public void ForecastTemp(View view) {
        Intent cityIntent = new Intent(this, ForecastWeaterh.class);
        if (cityString.equals("")||cityString==null){
            Toast.makeText(Details.this, "Invalid String", Toast.LENGTH_SHORT).show();
        }else {
            cityIntent.putExtra("forecast", forecast);
            cityIntent.putExtra("city", cityString);
            cityIntent.putExtra("country", countryString);
            startActivity(cityIntent);
        }
    }


    public class JESONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {

                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                StringBuffer buffer;
                reader = new BufferedReader(new InputStreamReader(stream));

                buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine())!=null){
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(connection !=null) {
                    connection.disconnect();
                }
                try {
                    if(reader !=null){
                        reader.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String query = JSONDecode(result,"query");
            String results = JSONDecode(query,"results");
            String channel = JSONDecode(results,"channel");
            dateString = JSONDecode(channel,"lastBuildDate");
            String location = JSONDecode(channel,"location");
            String astronomy = JSONDecode(channel,"astronomy");
            String item = JSONDecode(channel,"item");
            String condition = JSONDecode(item,"condition");
            forecast = JSONDecode(item,"forecast");
            tempforenString = JSONDecode(condition,"temp");
            codeString = JSONDecode(condition,"code");
            conditionString = JSONDecode(condition,"text");

            maxTempString = JSONDecode(forecast,0,"high");
            minTempString = JSONDecode(forecast,0,"low");
            cityString = JSONDecode(location,"city");
            countryString = JSONDecode(location,"country");
            tempcelsius = ConverTer(tempforenString);

            sunriseString = JSONDecode(astronomy,"sunrise");
            sunsetString = JSONDecode(astronomy,"sunset");


            setImage(Integer.parseInt(codeString));

            dateTV.setText(dateString);
            maxTempTV.setText(maxTempString);
            minTempTV.setText(minTempString);
            cityTV.setText(cityString);
            countryTV.setText(countryString);
            sunriseTV.setText(sunriseString);
            sunsetTV.setText(sunsetString);
            conditonTV.setText(conditionString);
            tempForen.setText(tempforenString+DEGREE+" F");
            tempCelsius.setText(tempcelsius+DEGREE+" C");


        }
        public double round(double value, int places) {
            if (places < 0) throw new IllegalArgumentException();

            long factor = (long) Math.pow(10, places);
            value = value * factor;
            long tmp = Math.round(value);
            return (double) tmp / factor;
        }
        private String ConverTer(String temp){
            double forenhit = Double.parseDouble(temp);
            double temperatue = ((forenhit - 32)*5)/9;

            return String.valueOf(round(temperatue,2));
        }
        private String JSONDecode(String data,int offset, String part){
            String decodeData="";
            try {
                JSONArray jsonArray = new JSONArray(data);
                decodeData = jsonArray.getJSONObject(offset).getString(part);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return decodeData;
        }
        private String JSONDecode(String data, String part){
            String decodeData="";
            try{
                JSONObject obj = new JSONObject(data);
                decodeData = obj.getString(part);

            }catch(Exception e){
                e.printStackTrace();
            }
            return decodeData;
        }

        private void setImage(int i){
            int[] images = new int[100];
            images[0] = R.drawable.icon0;
            images[1] = R.drawable.icon1;
            images[2] = R.drawable.icon_2;
            images[3] = R.drawable.icon_3;
            images[4] = R.drawable.icon_4;
            images[5] = R.drawable.icon_5;
            images[6] = R.drawable.icon_6;
            images[7] = R.drawable.icon_7;
            images[8] = R.drawable.icon_8;
            images[9] = R.drawable.icon_9;
            images[10] = R.drawable.icon_10;
            images[11] = R.drawable.icon_11;
            images[12] = R.drawable.icon_12;
            images[13] = R.drawable.icon_13;
            images[14] = R.drawable.icon_14;
            images[15] = R.drawable.icon_15;
            images[16] = R.drawable.icon_16;
            images[17] = R.drawable.icon_17;
            images[18] = R.drawable.icon_18;
            images[19] = R.drawable.icon_19;
            images[20] = R.drawable.icon_20;
            images[21] = R.drawable.icon_21;
            images[22] = R.drawable.icon_22;
            images[23] = R.drawable.icon_23;
            images[24] = R.drawable.icon_24;
            images[25] = R.drawable.icon_25;
            images[26] = R.drawable.icon_26;
            images[27] = R.drawable.icon_27;
            images[28] = R.drawable.icon_28;
            images[29] = R.drawable.icon_29;
            images[30] = R.drawable.icon_30;
            images[31] = R.drawable.icon_31;
            images[32] = R.drawable.icon_32;
            images[33] = R.drawable.icon_33;
            images[34] = R.drawable.icon_34;
            images[35] = R.drawable.icon_35;
            images[36] = R.drawable.icon_36;
            images[37] = R.drawable.icon_37;
            images[38] = R.drawable.icon_38;
            images[39] = R.drawable.icon_39;
            images[40] = R.drawable.icon_40;
            images[41] = R.drawable.icon_41;
            images[42] = R.drawable.icon_42;
            images[43] = R.drawable.icon_43;
            images[44] = R.drawable.icon_44;
            images[45] = R.drawable.icon_45;
            images[46] = R.drawable.icon_46;
            images[47] = R.drawable.icon_47;

            weatherIconImageView.setImageResource(images[i]);
        }
    }

}
