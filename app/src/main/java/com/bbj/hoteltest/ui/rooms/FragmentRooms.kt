package com.bbj.hoteltest.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bbj.data.models.DataRoom
import com.bbj.hoteltest.R
import com.bbj.hoteltest.databinding.FragmentRoomsBinding
import com.bbj.hoteltest.databinding.ItemRvRoomBinding
import com.bbj.hoteltest.ui.booking.FragmentBooking
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateViewBindingViewHolder
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentRooms : Fragment() {

    companion object {
        val HOTEL_NAME = "HOTEL_NAME"
        val HOTEL_ID = "HOTEL_ID" //Если нужно будет запрашивать комнаты по id
    }

    private var _binding: FragmentRoomsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModel<ViewModelRooms>()

    private var hotelName = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            hotelName = it.getString(HOTEL_NAME, "")
        }

        if (hotelName.isBlank()) {
            showError()
        } else {
            viewModel.getRoomsList()
            setUI()
            setObservers()
        }
    }

    private fun setUI() {
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.tvToolbarTitle.text = hotelName
    }

    private fun setObservers() {
        viewModel.liveRoom.observe(viewLifecycleOwner){state ->
            state.ifSuccess {
                showBody()

                val adapter = AdapterRooms{
                    goToBooking()
                }
                adapter.items = it.rooms
                binding.rvRoomInfo.adapter = adapter
            }
            state.ifLoading {
                showLoading()
            }
            state.ifError {
                showError()
            }
        }
    }

    private fun showBody(){
        binding.flStatus.isVisible = false
        binding.rvRoomInfo.isVisible = true
    }

    private fun showError(){
        binding.flStatus.isVisible = true
        binding.tvError.isVisible = true
        binding.piProgress.isVisible = false
        binding.rvRoomInfo.isVisible = false
    }

    private fun showLoading(){
        binding.flStatus.isVisible = true
        binding.piProgress.isVisible = true
        binding.tvError.isVisible = false
        binding.rvRoomInfo.isVisible = false
    }

    private fun goToBooking(){
        parentFragmentManager.commit {
            addToBackStack(this@FragmentRooms.javaClass.simpleName)
            replace(R.id.container,FragmentBooking())
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}