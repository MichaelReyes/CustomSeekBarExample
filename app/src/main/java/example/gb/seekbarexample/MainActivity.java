package example.gb.seekbarexample;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button submitButton;
    private TextView customTextProgress;
    private SeekBar customSeekBar;
    private ImageView customThumb;
    private LinearLayout.LayoutParams customThumbLayoutParams;
    private int customThumbLayoutMax;
    private int progressMaxValue;
    private float density;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        density = getResources().getDisplayMetrics().density;

        // initiate  views
        customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
        progressMaxValue = customSeekBar.getMax();
        customThumb = (ImageView) findViewById(R.id.customThumb);
        customTextProgress = (TextView) findViewById(R.id.customTextProgress);

        customThumbLayoutParams = (LinearLayout.LayoutParams) customTextProgress.getLayoutParams();
        customThumbLayoutMax = (int) (customThumbLayoutParams.topMargin / density);

        // perform seek bar change listener event used for getting the progress value
        customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                customThumbLayoutParams = (LinearLayout.LayoutParams) customTextProgress.getLayoutParams();
                //int dpValue = customThumbLayoutMax - ((customThumbLayoutMax / (progressMaxValue / 10)) * (progress / 10)); // margin in dips
                float dpValue = getDpValue(progress);
                int margin =  Math.round((dpValue * density + 0.5f) - 30); // margin in pixels
                Log.d("====================== ", "======================");
                Log.d("customThumbLayoutMax >> ", " " + customThumbLayoutMax);
                Log.d("progressMaxValue >> ", " " + progressMaxValue);
                Log.d("computed >> ", (customThumbLayoutMax - ((customThumbLayoutMax / (progressMaxValue / 10)) * (progress / 10))) + " ");
                Log.d("progress >> ", " " + progress);
                Log.d("dpValue >> ", " " + dpValue);
                Log.d("margin >> " , " " + margin);
                Log.d("====================== ", "======================");
                customThumbLayoutParams.setMargins(20, margin, 0, 0);


                customTextProgress.setText(String.valueOf(progress));
                customTextProgress.setLayoutParams(customThumbLayoutParams);
                //customThumb.setLayoutParams(customThumbLayoutParams);

                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });

        customSeekBar.setProgress(0);

    }


    private float getDpValue(int progress){
        Log.d(" ******************************************* "," ******************************************* ");
        Log.d("getDpValue >> ", customThumbLayoutMax + " - (( " + customThumbLayoutMax + " / (" + progressMaxValue + " / 10 )) * (" + progress + " / 10 ))");
        Log.d("customThumbLayoutMax >> ", " " + customThumbLayoutMax);
        Log.d("((customThumbLayoutMax / (progressMaxValue / 10)) >> ", " " + (customThumbLayoutMax / (progressMaxValue / 10)));
        Log.d("(progress / 10) >> ", " " + ((float)progress/6));
        Log.d(" ******************************************* "," ******************************************* ");

        float dpValue = customThumbLayoutMax - ((customThumbLayoutMax / (progressMaxValue / 10)) * ((float)progress / 10));
        return dpValue;

    }

}
