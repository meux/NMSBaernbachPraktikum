package at.leitner.nmsbrnbachpraktikum;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * About dialog including developer.
 *
 * @author David Leitner
 */
public class AboutActivity extends FragmentActivity {

    /**
     * @see android.app.Activity#onCreate(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
