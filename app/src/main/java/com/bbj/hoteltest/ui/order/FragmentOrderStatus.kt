package com.bbj.hoteltest.ui.order

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bbj.hoteltest.R
import com.bbj.hoteltest.ui.hotel.FragmentHotel
import com.bbj.hoteltest.ui.rooms.FragmentRooms
import java.util.Random

class FragmentOrderStatus : Fragment(R.layout.fragment_order) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI()
    }

    private fun setUI() {
        requireView().findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            returnToStart()
        }
        requireView().findViewById<AppCompatButton>(R.id.btnNext).setOnClickListener {
            returnToStart()
        }
        requireView().findViewById<TextView>(R.id.tvOrderConfirmation).text =
            resources.getString(R.string.сonfirmation_of_order, 104800 + Random().nextInt(100))
        handleBackPressure()
    }

    private fun handleBackPressure() {
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                returnToStart()
                return@OnKeyListener true
            }
            false
        })
    }

    private fun returnToStart(){
        parentFragmentManager.popBackStack(FragmentHotel::class.java.simpleName,FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}