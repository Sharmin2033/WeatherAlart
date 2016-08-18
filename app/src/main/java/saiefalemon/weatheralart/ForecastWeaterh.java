package saiefalemon.weatheralart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class ForecastWeaterh extends AppCompatActivity {
    TextView headTV,datetvOne,datetvTwo,datetvThree,datetvFour,datetvFive,maxOne,maxTwo,maxThree,maxFour,maxFive,minOne,minTwo,minThree,minFour,minFive,textTwo,textThree,textFour,textFive,textOne;
    ImageView imageViewOne,imageViewTwo,imageViewThree,imageViewFour,imageViewFive;
    String datetvOneSt,datetvTwoSt,datetvThreeSt,datetvFourSt,datetvFiveSt,maxOneSt,maxTwoSt,maxThreeSt,maxFourSt,maxFiveSt,minOneSt,minTwoSt,minThreeSt,minFourSt,minFiveSt,textTwoSt,textThreeSt,textFourSt,textFiveSt,textOneSt,imageViewOneSt,imageViewTwoSt,imageViewThreeSt,imageViewFourSt,imageViewFiveSt;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_weaterh);

        final String DEGREE  = "\u00b0";

        headTV = (TextView) findViewById(R.id.headTV);

        datetvOne = (TextView) findViewById(R.id.datetvOne);
        datetvTwo = (TextView) findViewById(R.id.datetvtwo);
        datetvThree = (TextView) findViewById(R.id.datetvthree);
        datetvFour = (TextView) findViewById(R.id.datetvfour);
        datetvFive = (TextView) findViewById(R.id.datetvFive);

        maxOne = (TextView) findViewById(R.id.maxOne);
        maxTwo = (TextView) findViewById(R.id.maxTwo);
        maxThree = (TextView) findViewById(R.id.maxThree);
        maxFour = (TextView) findViewById(R.id.maxFour);
        maxFive = (TextView) findViewById(R.id.maxFive);

        minOne = (TextView) findViewById(R.id.minOne);
        minTwo = (TextView) findViewById(R.id.minTow);
        minThree = (TextView) findViewById(R.id.minThree);
        minFour = (TextView) findViewById(R.id.minFour);
        minFive = (TextView) findViewById(R.id.minFive);

        textOne = (TextView) findViewById(R.id.textOne);
        textTwo = (TextView) findViewById(R.id.textTwo);
        textThree = (TextView) findViewById(R.id.textThree);
        textFour = (TextView) findViewById(R.id.textFour);
        textFive = (TextView) findViewById(R.id.textFive);

        imageViewOne = (ImageView) findViewById(R.id.imageViewOne);
        imageViewTwo = (ImageView) findViewById(R.id.imageViewTwo);
        imageViewThree = (ImageView) findViewById(R.id.imageViewThree);
        imageViewFour = (ImageView) findViewById(R.id.imageViewFour);
        imageViewFive = (ImageView) findViewById(R.id.imageViewFive);


        Intent cityIntent = getIntent();
        city = cityIntent.getStringExtra("city");
        String forecast = cityIntent.getStringExtra("forecast");
        String country = cityIntent.getStringExtra("country");
        headTV.setText("Forecast of "+city+", "+country);

        datetvOneSt = JSONDecode(forecast,1,"date");
        datetvTwoSt = JSONDecode(forecast,2,"date");
        datetvThreeSt = JSONDecode(forecast,3,"date");
        datetvFourSt = JSONDecode(forecast,4,"date");
        datetvFiveSt = JSONDecode(forecast,5,"date");

        minOneSt = JSONDecode(forecast,1,"low");
        minTwoSt = JSONDecode(forecast,2,"low");
        minThreeSt = JSONDecode(forecast,3,"low");
        minFourSt = JSONDecode(forecast,4,"low");
        minFiveSt = JSONDecode(forecast,5,"low");

        maxOneSt = JSONDecode(forecast,1,"high");
        maxTwoSt = JSONDecode(forecast,2,"high");
        maxThreeSt = JSONDecode(forecast,3,"high");
        maxFourSt = JSONDecode(forecast,4,"high");
        maxFiveSt = JSONDecode(forecast,5,"high");

        textOneSt = JSONDecode(forecast,1,"text");
        textTwoSt = JSONDecode(forecast,2,"text");
        textThreeSt = JSONDecode(forecast,3,"text");
        textFourSt = JSONDecode(forecast,4,"text");
        textFiveSt = JSONDecode(forecast,5,"text");

        imageViewOneSt = JSONDecode(forecast,1,"code");
        imageViewTwoSt = JSONDecode(forecast,2,"code");
        imageViewThreeSt = JSONDecode(forecast,3,"code");
        imageViewFourSt = JSONDecode(forecast,4,"code");
        imageViewFiveSt = JSONDecode(forecast,5,"code");

        setImage(imageViewOne,imageViewOneSt);
        setImage(imageViewTwo,imageViewTwoSt);
        setImage(imageViewThree,imageViewThreeSt);
        setImage(imageViewFour,imageViewFourSt);
        setImage(imageViewFive,imageViewFiveSt);

        textOne.setText(textOneSt);
        textTwo.setText(textTwoSt);
        textThree.setText(textThreeSt);
        textFour.setText(textFourSt);
        textFive.setText(textFiveSt);

        maxOne.setText("Max: "+maxOneSt+DEGREE+" F");
        maxTwo.setText("Max: "+maxTwoSt+DEGREE+" F");
        maxThree.setText("Max: "+maxThreeSt+DEGREE+" F");
        maxFour.setText("Max: "+maxFourSt+DEGREE+" F");
        maxFive.setText("Max: "+maxFiveSt+DEGREE+" F");

        minOne.setText("Min: "+minOneSt+DEGREE+" F");
        minTwo.setText("Min: "+minTwoSt+DEGREE+" F");
        minThree.setText("Min: "+minThreeSt+DEGREE+" F");
        minFour.setText("Min: "+minFourSt+DEGREE+" F");
        minFive.setText("Min: "+minFiveSt+DEGREE+" F");

        datetvOne.setText(datetvOneSt);
        datetvTwo.setText(datetvTwoSt);
        datetvThree.setText(datetvThreeSt);
        datetvFour.setText(datetvFourSt);
        datetvFive.setText(datetvFiveSt);


    }
    private void setImage(ImageView imageView,String code){
        int i= Integer.parseInt(code);
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

        imageView.setImageResource(images[i]);
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

    public void backToDetail(View view) {
        Intent cityIntent = new Intent(this, Details.class);
        cityIntent.putExtra("cityName", city);
        startActivity(cityIntent);

    }
}
