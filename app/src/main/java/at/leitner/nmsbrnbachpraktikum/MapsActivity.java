package at.leitner.nmsbrnbachpraktikum;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Shows google map including buttons and markers.
 *
 * @author David Leitner
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    /**
     * map
     */
    private GoogleMap mMap;
    /**
     * button go to school
     */
    private Button goToSchool;
    /**
     * button go to company
     */
    private Button goToDecide;
    /**
     * button show overview
     */
    private Button overview;

    /**
     * Adds click listener to all buttons and initialize visibility of buttons.
     *
     * @see android.app.Activity#onCreate(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        goToSchool = this.findViewById(R.id.buttonSchool);
        goToDecide = this.findViewById(R.id.buttonDecide);
        overview = this.findViewById(R.id.buttonOverview);
        this.disableButton(overview);

        goToSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng mark = new LatLng(MapsCoordinate.LATITUDE_NMS_BAERNBACH, MapsCoordinate.LONGITUDE_NMS_BAERNBACH);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mark, 17));
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                enableButton(overview);
                disableButton(goToSchool);
                enableButton(goToDecide);
            }
        });

        goToDecide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng mark = new LatLng(MapsCoordinate.LATITUDE_DECIDE, MapsCoordinate.LONGITUDE_DECIDE);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mark, 17));
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                enableButton(overview);
                enableButton(goToSchool);
                disableButton(goToDecide);
            }
        });

        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MapsCoordinate.LAT_LNG_BETWEEN, 10));
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                disableButton(overview);
                enableButton(goToSchool);
                enableButton(goToDecide);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * <p>
     * Sets the current camera position between school and company showing both marker. Adds also a camera move listener enabling/disabling buttons.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng markNms = new LatLng(MapsCoordinate.LATITUDE_NMS_BAERNBACH, MapsCoordinate.LONGITUDE_NMS_BAERNBACH);
        mMap.addMarker(new MarkerOptions().position(markNms).title("NMS Bärnbach"));

        final LatLng markDecide = new LatLng(MapsCoordinate.LATITUDE_DECIDE, MapsCoordinate.LONGITUDE_DECIDE);
        mMap.addMarker(new MarkerOptions().position(markDecide).title("decide Clinical Software GmbH"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MapsCoordinate.LAT_LNG_BETWEEN, 10));

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                LatLngBounds latLngBounds = mMap.getProjection().getVisibleRegion().latLngBounds;
                boolean nmsNotVisible = !latLngBounds.contains(markNms);
                boolean decideNotVisible = !latLngBounds.contains(markDecide);
                if (nmsNotVisible) {
                    enableButton(goToSchool);
                } else {
                    disableButton(goToSchool);
                }
                if (decideNotVisible) {
                    enableButton(goToDecide);
                } else {
                    disableButton(goToDecide);
                }
                if (nmsNotVisible || decideNotVisible) {
                    enableButton(overview);
                }
                if (!nmsNotVisible && !decideNotVisible) {
                    disableButton(overview);
                }
            }
        });
    }

    /**
     * Disable button and set invisible.
     *
     * @param button disable this
     */
    private void disableButton(Button button) {
        button.setEnabled(false);
        button.setAlpha(0.0f);
    }

    /**
     * Enable button and set visible.
     *
     * @param button enable this
     */
    private void enableButton(Button button) {
        button.setEnabled(true);
        button.setAlpha(0.8f);
    }
}

