package com.reablace.masterquiz.ui.listevent

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.reablace.masterquiz.R
import com.reablace.masterquiz.base.BaseMapFragment
import com.reablace.masterquiz.common.ViewModelFactory
import com.reablace.masterquiz.models.QuizEvent
import javax.inject.Inject

private const val TAG = "EventMapFragment"

class EventMapFragment : BaseMapFragment(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mapViewModel: MapViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        getControllerComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapViewModel = ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)

        // Events observer
        mapViewModel.events.observe(viewLifecycleOwner, Observer { setMarkers(it) })

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mapViewModel.fetchEventList()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ContextCompat.checkSelfPermission(
                activity!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap?.isMyLocationEnabled = true

            mapViewModel.fetchEventList()

            val edinburgh = LatLng(55.953472, -3.188275)
            val cameraPosition = CameraPosition.builder()
                .target(edinburgh)
                .zoom(13f)
                .build()
            mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

            mMap?.setOnMarkerClickListener {
                mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(it.position, 6f))
                false
            }

        } else {
            // Show rationale and request permission.
            super.showRationaleAndRequest()
        }
    }


    /**
     * Update map markers when
     */
    private fun setMarkers(events: List<QuizEvent>) {

        if (mMap == null) {
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }

        mMap?.clear()

        events.forEach {

            val location = it.location?.let { geoPoint ->
                LatLng(geoPoint.latitude, geoPoint.longitude)
            }

            location?.apply {
                mMap?.addMarker(
                    MarkerOptions()
                        .position(this)
                        .title(it.name)
                        .snippet(it.state)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)))
            }
        }
    }
}

// https://developers.google.com/maps/documentation/android-sdk/location