package com.bbj.hoteltest.ui.booking

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bbj.hoteltest.R
import com.bbj.hoteltest.databinding.FragmentBookingBinding
import com.bbj.hoteltest.databinding.IncludeBookingCustomerInfoBinding
import com.bbj.hoteltest.databinding.IncludeBookingInfoBinding
import com.bbj.hoteltest.databinding.IncludeBookingPayBinding
import com.bbj.hoteltest.ui.extensions.getTextString
import com.bbj.hoteltest.ui.extensions.setIsValueValid
import com.bbj.hoteltest.ui.order.FragmentOrderStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentBooking : Fragment() {

    private var _rootBinding: FragmentBookingBinding? = null
    private val rootBinding
        get() = _rootBinding!!

    private var _infoBinding: IncludeBookingInfoBinding? = null
    private val infoBinding
        get() = _infoBinding!!


    private var _customerBinding: IncludeBookingCustomerInfoBinding? = null
    private val customerBinding
        get() = _customerBinding!!

    private var _payBinding: IncludeBookingPayBinding? = null
    private val payBinding
        get() = _payBinding!!

    private val viewModel by viewModel<ViewModelBooking>()

    private val adapterTourist = AdapterTourist()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _rootBinding = FragmentBookingBinding.inflate(inflater, container, false)
        _infoBinding = rootBinding.includeBookingInfo
        _customerBinding = rootBinding.includeBookingCustomerInfo
        _payBinding = rootBinding.includePay
        return rootBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTourInfo()

        setUI()
        setObservers()
    }

    @SuppressLint("SetTextI18n")
    private fun setObservers() {
        viewModel.liveTour.observe(viewLifecycleOwner) { state ->
            showBody()

            state.ifSuccess {
                rootBinding.run {
                    tvHotelName.text = it.hotelName
                    tvHotelAddress.text = it.hotelAddress
                    tvRate.text = it.ratingName
                }

                infoBinding.run {
                    tvDepartureFrom.text = it.departure
                    tvCountryCity.text = it.arrivalCountry
                    tvDates.text = it.tourDateStart + " - " + it.tourDateStop
                    tvNightCount.text = resources.getString(R.string.d_night, it.numberOfNights)
                    tvHotel.text = it.hotelName
                    tvRoomType.text = it.room
                    tvFood.text = it.nutrition
                }

                payBinding.run {
                    tvTourPrice.text = resources.getString(R.string.price_d, it.tourPrice)
                    tvFuelPrice.text = resources.getString(R.string.price_d, it.fuelCharge)
                    tvServicePrice.text = resources.getString(R.string.price_d, it.serviceCharge)
                    val resultPrice = it.tourPrice + it.fuelCharge + it.serviceCharge
                    tvToPay.text = resources.getString(R.string.price_d, resultPrice)
                    rootBinding.btnToOrderStatus.text =
                        resources.getString(R.string.pay_d, resultPrice.toString())
                }
            }
            state.ifLoading {
                showLoading()
            }
            state.ifError {
                showError()
            }
        }
    }

    private fun showBody() {
        rootBinding.flStatus.isVisible = false
        rootBinding.svBody.isVisible = true
    }

    private fun showError() {
        rootBinding.flStatus.isVisible = true
        rootBinding.tvError.isVisible = true
        rootBinding.piProgress.isVisible = false
        rootBinding.svBody.isVisible = false
    }

    private fun showLoading() {
        rootBinding.flStatus.isVisible = true
        rootBinding.piProgress.isVisible = true
        rootBinding.tvError.isVisible = false
        rootBinding.svBody.isVisible = false
    }

    private fun setUI() {
        rootBinding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        rootBinding.btnToOrderStatus.setOnClickListener {
            if (isInputCorrect())
                goToOrderStatus()
        }
        rootBinding.rvTourists.adapter = adapterTourist
        customerBinding.etPhoneNumber.editText?.text =
            Editable.Factory.getInstance().newEditable("+7 (9")
    }

    private fun goToOrderStatus() {
        parentFragmentManager.commit {
            addToBackStack(this@FragmentBooking.javaClass.simpleName)
            replace(R.id.container, FragmentOrderStatus())
        }
    }

    private fun isInputCorrect(): Boolean {
        val isNumberValid = isValidPhoneNumber()
        val isValidEmail = isValidEmail()
        val isValidTouristInfo = isValidTouristInfo()
        return  isNumberValid && isValidEmail && isValidTouristInfo
    }

    private val numberMask = "+7 (***) ***-**-**"

    private fun isValidPhoneNumber(): Boolean {
        val number = customerBinding.etPhoneNumber.getTextString()
        val isNumberValid = number.length == numberMask.length
        customerBinding.etPhoneNumber.setIsValueValid(isNumberValid)
        return isNumberValid
    }

    private fun isValidEmail(): Boolean {
        val email = customerBinding.etEmail.getTextString()
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        customerBinding.etEmail.setIsValueValid(isEmailValid)
        return isEmailValid
    }

    private fun isValidTouristInfo() : Boolean{
        adapterTourist.showErrors = true
        return adapterTourist.isTouristInfoValid()
    }

    override fun onDestroyView() {
        _rootBinding = null
        _infoBinding = null
        _payBinding = null
        super.onDestroyView()
    }

}