package course.example.modernart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends Activity {
    SeekBar mSeekBar;
    FrameLayout mLeftTop, mTopRight, mBottom, mCenterRight;
    int seek;
    public DialogFragment mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Variables initialization
        mLeftTop = (FrameLayout) findViewById(R.id.leftTop);
        mTopRight = (FrameLayout) findViewById(R.id.topRight);
        mBottom = (FrameLayout) findViewById(R.id.bottom);
        mCenterRight = (FrameLayout) findViewById(R.id.centerRight);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        //Setting up starting colors
        updateBackground();

        mSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

    }

    //Listen on seekBar changes
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

    //Changing frames color
    private void updateBackground() {
        seek = mSeekBar.getProgress();

        mLeftTop.setBackgroundColor(0xff000000 + (255 - seek) * 0x10000 + 70 * 0x100 + 70);
        mTopRight.setBackgroundColor(0xff000000 + 220 * 0x10000 + (255 - seek) * 0x100 + 220);
        mBottom.setBackgroundColor(0xff000000 + 150 * 0x10000 + 150 * 0x100 + (255 - seek));
        mCenterRight.setBackgroundColor(0xff000000 + seek * 0x10000 + 141 * 0x100 + 88);


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
        if (id == R.id.more_information) {
            mDialog = RedirectDialogFragment.newInstance();
            mDialog.show(getFragmentManager(), "Alert");
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    //Our Dialog class
    public static class RedirectDialogFragment extends DialogFragment {
        public static RedirectDialogFragment newInstance() {
            return new RedirectDialogFragment();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage("Inspired by coursera." + "\n" + "Click below to learn more!")
                    .setCancelable(false).setPositiveButton("Not Now",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ((MainActivity) getActivity()).runBrowser(false);
                                }
                            })
                    .setNegativeButton("Visit MOMA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((MainActivity) getActivity()).runBrowser(true);
                        }
                    }).create();
        }
    }

    //Opening moma site
    public void runBrowser(boolean boo){

        if(boo) {
            Intent runBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org/m"));
            startActivity(runBrowser);
        }

        mDialog.dismiss();
    }

}
