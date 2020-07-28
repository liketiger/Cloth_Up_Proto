package com.liketiger.cloth_up


import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_g_map.*

class GMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_g_map)

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.googleMap) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            mMap = p0
        }

        val location = LatLng(36.610967, 127.288547) // 위도 경도, 변수에 저장.
        mMap.addMarker(MarkerOptions().position(location).title("고려대학교 세종캠퍼스"))
        // 지도의 표시를 하고 제목을 추가.
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))


    }
}