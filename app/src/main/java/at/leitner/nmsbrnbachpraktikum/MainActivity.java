package at.leitner.nmsbrnbachpraktikum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Starting activity showing buttons and edit.
 *
 * @author David Leitner
 */
public class MainActivity extends AppCompatActivity {

    /**
     * @see android.app.Activity#onCreate(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Loads google maps containing markers for school and company.
     *
     * @param view ??
     */
    public void showMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /**
     * Show about dialog.
     *
     * @param view bla bla
     */
    public void showAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

}
