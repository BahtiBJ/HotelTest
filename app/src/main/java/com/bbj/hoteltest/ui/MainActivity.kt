package com.bbj.hoteltest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.bbj.hoteltest.R
import com.bbj.hoteltest.ui.hotel.FragmentHotel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace(R.id.container,FragmentHotel())
        }
    }
}