package course.example.modernart;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SeekBar;


public class MainActivity extends Activity {
    SeekBar seekBar;
    FrameLayout leftTop, topRight, bottom, centerLeft;
    int seek;
    boolean tr =true;
    int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftTop = (FrameLayout)findViewById(R.id.leftTop);
        topRight = (FrameLayout)findViewById(R.id.topRight);
        bottom = (FrameLayout)findViewById(R.id.bottom);
        centerLeft = (FrameLayout)findViewById(R.id.centerLeft);
        seekBar = (SeekBar)findViewById(R.id.seekBar);


        updateBackground();

        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateBackground();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void updateBackground() {
//        if(count == 0){
//            leftTop.setTranslationX(leftTop.getTranslationX() - 5);
//            topRight.setTranslationY(leftTop.getTranslationX() - 5);
//        }
//         if(count % 2 == 0 ){
//            leftTop.setTranslationX(leftTop.getTranslationX() - 10);
//             topRight.setTranslationY(leftTop.getTranslationX() - 10);
//        }
//        else if(count % 2 != 0 ){
//            leftTop.setTranslationX(leftTop.getTranslationX() + 10);
//             topRight.setTranslationY(leftTop.getTranslationX() + 10);
//        }
        seek = seekBar.getProgress()*3;

        leftTop.setBackgroundColor(0xff000000 + seek * 0x10000 + 5 * 0x100 + 60);
        topRight.setBackgroundColor(0xff000000 + seek * 0x10000 + 200 * 0x100 + 13);
        bottom.setBackgroundColor(0xff000000 + seek * 0x10000 + 160* 0x100 + 220);
        centerLeft.setBackgroundColor(0xff000000 + seek * 0x10000 + 141 * 0x100 + 18);

        count++;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
