package vn.uit.thien.changebackgroundcolor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.btnChange);

        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.doSave(view);
            }
        });
        loadBackground();
    }

    public void doSave(View view) {
        SharedPreferences sharedPreferences= this.getSharedPreferences("changedBackground", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Resources res = getResources();

        final RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
        final TypedArray myBackgroundColor = res.obtainTypedArray(R.array.colorArray);
        final Random random = new Random();
        int randomInt = random.nextInt(myBackgroundColor.length());
        int drawableID = myBackgroundColor.getResourceId(randomInt, -1);

        background.setBackgroundResource(drawableID);
        editor.putInt("backgroundID", drawableID);
        editor.apply();
    }

    public void loadBackground() {
        SharedPreferences sharedPreferences= this.getSharedPreferences("changedBackground", Context.MODE_PRIVATE);
        final RelativeLayout background = (RelativeLayout) findViewById(R.id.background);

        if (sharedPreferences != null) {
            int drawableID = sharedPreferences.getInt("backgroundID", -1);
            background.setBackgroundResource(drawableID);
        }
    }
}
