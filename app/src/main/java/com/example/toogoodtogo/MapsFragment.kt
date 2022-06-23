package com.example.toogoodtogo

import com.example.toogoodtogo.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_maps.*


class MapsFragment : Fragment() {

    private var button: FloatingActionButton? = null
    var lat = ""
    var lng = ""

    lateinit var dataPasser: OnDataPass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    private fun passData(){
        dataPasser.onDataPass("$lat:$lng")
    }

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.setOnMapClickListener { latLng -> // Creating a marker
            val markerOptions = MarkerOptions()

            // Setting the position for the marker
            markerOptions.position(latLng)

            // Setting the title for the marker.
            // This will be displayed on taping the marker
            markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)

            lat = latLng.latitude.toString()
            lng = latLng.longitude.toString()

            // Clears the previously touched position
            googleMap.clear()

            // Animating to the touched position
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))

            // Placing a marker on the touched position
            googleMap.addMarker(markerOptions)
        }

        val aveiro = LatLng(40.640, -8.65)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(aveiro))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(12.0f))
    }



    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById<View>(R.id.imageButton) as FloatingActionButton

        button!!.setOnClickListener {
            fragmentManager?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        passData()
    }

}