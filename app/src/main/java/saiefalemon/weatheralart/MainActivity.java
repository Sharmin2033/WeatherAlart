package saiefalemon.weatheralart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    private EditText cityET;
    private String cityString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void setCity(View view) {
        cityET = (EditText) findViewById(R.id.cityET);
        cityString = cityET.getText().toString();
        if(cityString==null||cityString.equals("")){
            Toast.makeText(this, "Invalid City", Toast.LENGTH_SHORT).show();
        }else {
            Intent cityIntent = new Intent(this, Details.class);
            cityIntent.putExtra("cityName", cityString);
            startActivity(cityIntent);
        }
    }
}
