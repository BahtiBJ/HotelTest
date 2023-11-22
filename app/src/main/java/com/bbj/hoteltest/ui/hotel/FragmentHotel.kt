package com.bbj.hoteltest.ui.hotel

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bbj.data.models.DataAboutHotel
import com.bbj.data.models.DataHotel
import com.bbj.hoteltest.R
import com.bbj.hoteltest.databinding.FragmentHotelBinding
import com.bbj.hoteltest.databinding.IncludeHotelAboutBinding
import com.bbj.hoteltest.databinding.IncludeHotelPreviewBinding
import com.bbj.hoteltest.ui.extensions.createPeculiarity
import com.bbj.hoteltest.ui.rooms.FragmentRooms
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentHotel : Fragment() {

    private var _rootBinding: FragmentHotelBinding? = null
    private val rootBinding
        get() = _rootBinding!!

    private var _previewBinding: IncludeHotelPreviewBinding? = null
    private val previewBinding
        get() = _previewBinding!!

    private var _aboutBinding: IncludeHotelAboutBinding? = null
    private val aboutBinding
        get() = _aboutBinding!!

    private val viewModel: ViewModelHotel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _rootBinding = FragmentHotelBinding.inflate(inflater, container, false)
        _previewBinding = rootBinding.includeHotelPreview
        _aboutBinding = rootBinding.includeHotelAbout
        return rootBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHotelInfo()

        setObservers()
        setUI()
    }

    private fun setUI() {
        rootBinding.btnToChooseRoom.setOnClickListener {
            goToRooms()
        }
    }

    private fun goToRooms() {
        parentFragmentManager.commit {
            val arg = bundleOf(FragmentRooms.HOTEL_NAME to previewBinding.tvHotelName.text.toString())
            addToBackStack(this@FragmentHotel.javaClass.simpleName)
            replace(R.id.container, FragmentRooms::class.java,arg)
        }
    }

    private fun setObservers() {
        viewModel.liveHotel.observe(viewLifecycleOwner) { state ->
            state.ifSuccess { hotel ->
                showBody()

                setupPreviewPart(hotel)
                setupAboutPart(hotel.aboutHotel)
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
        rootBinding.flStatus.isVisible = false
        rootBinding.svBody.isVisible = true
        rootBinding.btnToChooseRoom.isVisible = true
    }

    private fun showError(){
        rootBinding.flStatus.isVisible = true
        rootBinding.tvError.isVisible = true
        rootBinding.piProgress.isVisible = false
        rootBinding.svBody.isVisible = false
        rootBinding.btnToChooseRoom.isVisible = false
    }

    private fun showLoading(){
        rootBinding.flStatus.isVisible = true
        rootBinding.piProgress.isVisible = true
        rootBinding.tvError.isVisible = false
        rootBinding.svBody.isVisible = false
        rootBinding.btnToChooseRoom.isVisible = false
    }

    @SuppressLint("SetTextI18n")
    private fun setupPreviewPart(hotel: DataHotel) {
        previewBinding.run {
            tvRate.text = "${hotel.rating} ${hotel.ratingName}"
            tvHotelName.text = hotel.name
            tvHotelAddress.text = hotel.adress
            tvHotelPrice.text = resources.getString(R.string.price_from, hotel.minimalPrice)
            tvHotelPriceFor.text = hotel.priceForIt
            isSlider.setImagePaths(hotel.imageUrls)
        }
    }


    private fun setupAboutPart(aboutHotel: DataAboutHotel) {
        aboutBinding.tvDescribtion.setText(aboutHotel.description)
        aboutHotel.peculiarities.forEach {
            aboutBinding.flexAdvantages.createPeculiarity(it)
        }
    }

    override fun onDestroyView() {
        _rootBinding = null
        _previewBinding = null
        _aboutBinding = null
        super.onDestroyView()
    }
}