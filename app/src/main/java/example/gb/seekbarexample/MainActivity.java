package example.gb.seekbarexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import example.gb.widget.VerticalSeekBar;

public class MainActivity extends AppCompatActivity implements VerticalSeekBar.VerticalSeekBarListener {

    private Button submitButton;
    private TextView customTextProgress;
    private SeekBar customSeekBar;
    private ImageView customThumb;
    private LinearLayout.LayoutParams customThumbLayoutParams;
    private int customThumbLayoutMax;
    private int progressMaxValue;
    private float density;

    private RelativeLayout seekBarParentLayout;

    /*
    private LinearLayout customThumbLinearLayout;
    private TextView customThumbText;
    private ImageView customThumbImageView;
    */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        density = getResources().getDisplayMetrics().density;

        // initiate  views
        customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
        progressMaxValue = customSeekBar.getMax();

        seekBarParentLayout = (RelativeLayout)customSeekBar.getParent();

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
        float dpValue = (customThumbLayoutMax + 7) - ((customThumbLayoutMax / (progressMaxValue / 10)) * ((float)progress / 10));
        return dpValue;

    }

    /**
     * Create custom thumb dynamically
     * **/
    @Override
    public void onInitializeSeekBar() {

        /*
        int customSeekBarHeight = customSeekBar.getHeight();
        int customSeekBarHeightDip = Math.round(customSeekBarHeight / density);

        customThumbLinearLayout = new LinearLayout(this);
        customThumbLinearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams customThumbLinearLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (customSeekBarHeight + 140)
        );
        customThumbLinearLayoutParams.setMargins(0,0,- Math.round( 20 * density + 0.5f),0);
        customThumbLinearLayout.setLayoutParams(customThumbLinearLayoutParams);

        customThumbText = new TextView(this);
        customThumbText.setText("0");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            customThumbText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        customThumbText.setTextColor(Color.WHITE);
        customThumbText.setPadding(0,0,convertDipToPx(10),0);
        LinearLayout.LayoutParams customThumbTextParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        customThumbLinearLayoutParams.setMargins(convertDipToPx(20),convertDipToPx(customSeekBarHeightDip + 5),0,0);
        customThumbText.setLayoutParams(customThumbTextParams);

        customThumbImageView = new ImageView(this);
        customThumbImageView.setImageResource(R.drawable.thumb_drawable);
        customThumbImageView.setPadding(0,0,0,convertDipToPx(10));
        LinearLayout.LayoutParams customThumbImageViewParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        customThumbImageViewParams.setMargins(convertDipToPx(20),0,0,0);
        customThumbImageView.setLayoutParams(customThumbImageViewParams);

        customThumbLinearLayout.addView(customThumbText);
        customThumbLinearLayout.addView(customThumbImageView);

        customThumbLayoutParams = (LinearLayout.LayoutParams) customThumbText.getLayoutParams();
        customThumbLayoutMax = (int) (customThumbLayoutParams.topMargin / density);

        RelativeLayout.LayoutParams seekBarLayoutParams = (RelativeLayout.LayoutParams) customSeekBar.getLayoutParams();
        seekBarLayoutParams.addRule(RelativeLayout.ALIGN_TOP,customThumbLinearLayout.getId());
        seekBarLayoutParams.addRule(RelativeLayout.END_OF,customThumbLinearLayout.getId());
        seekBarLayoutParams.addRule(RelativeLayout.RIGHT_OF,customThumbLinearLayout.getId());

        customSeekBar.setLayoutParams(seekBarLayoutParams);

        seekBarParentLayout.removeView(customSeekBar);
        seekBarParentLayout.addView(customThumbLinearLayout);
        seekBarParentLayout.addView(customSeekBar);
        */

    }

    private int convertDipToPx(int px){

        return Math.round(px * density + 0.5f);

    }

}
