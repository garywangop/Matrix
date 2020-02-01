package com.laioffer.matrix;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements OnMapReadyCallback {

  private MapView mapView;
  private View view;
  private GoogleMap googleMap;
  private LocationTracker locationTracker;
  private FloatingActionButton fabReport;
  private ReportDialog dialog;

  public static MainFragment newInstance() {

    Bundle args = new Bundle();
    MainFragment fragment = new MainFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public MainFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mapView = (MapView) view.findViewById(R.id.event_map_view);
    fabReport = view.findViewById(R.id.fab);
    fabReport.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showDialog(null, null);
      }
    });


    if (mapView != null) {
      mapView.onCreate(null);
      mapView.onResume();// needed to get the map to display immediately
      mapView.getMapAsync(this);
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    MapsInitializer.initialize(getContext());
    this.googleMap = googleMap;
    this.googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(),
        R.raw.sytle_json));

    locationTracker = new LocationTracker(getActivity());
    locationTracker.getLocation();

    LatLng latLng = new LatLng(
        locationTracker.getLatitude(),
        locationTracker.getLongitude()
    );

    CameraPosition cameraPosition = new CameraPosition.Builder()
        .target(latLng)
        .zoom(16)
        .bearing(90)
        .tilt(30)
        .build();

    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    MarkerOptions marker = new MarkerOptions().position(latLng).
        title("You");

    // Changing marker icon
    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.boy));

    // adding marker
    googleMap.addMarker(marker);

  }

  private void showDialog(String label, String prefillText) {
    dialog = new ReportDialog(getContext());
    dialog.show();
  }

}
