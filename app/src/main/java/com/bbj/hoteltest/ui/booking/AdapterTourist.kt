package com.bbj.hoteltest.ui.booking

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.bbj.hoteltest.R
import com.bbj.hoteltest.databinding.ItemRvTouristBinding
import com.bbj.hoteltest.ui.extensions.getTextString
import com.bbj.hoteltest.ui.extensions.setIsValueValid


data class TouristState(
    val titleRes: Int,
    var name: String = "",
    var surname: String = "",
    var birthDate: String = "",
    var citizenShip: String = "",
    var passportNumber: String = "",
    var passportPeriod: String = ""
) {
    companion object {
        val empty = -23
    }

    var isCollapsed = false

    val isValid: Boolean
        get() {
            return titleRes != empty
                    && name.isNotBlank()
                    && surname.isNotBlank()
                    && birthDate.isNotBlank()
                    && citizenShip.isNotBlank()
                    && passportNumber.isNotBlank()
                    && passportPeriod.isNotBlank()
        }

    val isCreated: Boolean
        get() = titleRes != empty
}

class AdapterTourist() : RecyclerView.Adapter<AdapterTourist.VH>() {
    inner class VH(private val binding: ItemRvTouristBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val tourist = touristList[position]
            val res = binding.root.context.resources
            if (tourist.isCreated) {
                configureEditFields(position)
                if (!tourist.isCollapsed)
                    binding.llInputTourist.isVisible = true
                binding.tvTouristNumber.setText(
                    res.getString(
                        R.string.s_tourist,
                        res.getString(tourist.titleRes)
                    )
                )
                binding.flShowTouristInfo.isSelected = true
                binding.flShowTouristInfo.setOnClickListener {
                    if (binding.llInputTourist.scaleY != 0f) {
                        editFieldsDisappear()
                        touristList[position].isCollapsed = true
                    } else {
                        touristList[position].isCollapsed = false
                        editFieldsAppear()
                    }
                    iconRotating()
                }
            } else {
                binding.llInputTourist.isVisible = false
                binding.tvTouristNumber.setText(
                    res.getString(
                        R.string.add_tourist
                    )
                )
                binding.flShowTouristInfo.isSelected = false
                binding.flShowTouristInfo.setOnClickListener {
                    addTourist(TouristState(numbers[touristList.size - 1]))
                }
            }
        }

        private fun configureEditFields(position: Int) {
            binding.run {
                etName.editText?.doOnTextChanged(action = { text, start, before, count ->
                    touristList[position].name = text.toString()
                })
                etSurname.editText?.doOnTextChanged(action = { text, start, before, count ->
                    touristList[position].surname = text.toString()
                })
                etBirthDate.editText?.doOnTextChanged(action = { text, start, before, count ->
                    touristList[position].birthDate = text.toString()
                })
                etCitizenship.editText?.doOnTextChanged(action = { text, start, before, count ->
                    touristList[position].citizenShip = text.toString()
                })
                etPassportNumber.editText?.doOnTextChanged(action = { text, start, before, count ->
                    touristList[position].passportNumber = text.toString()
                })
                etPassportPeriod.editText?.doOnTextChanged(action = { text, start, before, count ->
                    touristList[position].passportPeriod = text.toString()
                })
                if (showErrors) {
                    etName.setIsValueValid(etName.getTextString().isNotBlank())
                    etSurname.setIsValueValid(etSurname.getTextString().isNotBlank())
                    etBirthDate.setIsValueValid(etBirthDate.getTextString().isNotBlank())
                    etCitizenship.setIsValueValid(etCitizenship.getTextString().isNotBlank())
                    etPassportNumber.setIsValueValid(etPassportNumber.getTextString().isNotBlank())
                    etPassportPeriod.setIsValueValid(etPassportPeriod.getTextString().isNotBlank())
                } else {
                    etName.setIsValueValid(true)
                    etSurname.setIsValueValid(true)
                    etBirthDate.setIsValueValid(true)
                    etCitizenship.setIsValueValid(true)
                    etPassportNumber.setIsValueValid(true)
                    etPassportPeriod.setIsValueValid(true)
                }
            }
        }

        private fun editFieldsAppear() {
            binding.llInputTourist.animate()
                .scaleY(1f)
                .translationY(0f)
                .setDuration(400)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        super.onAnimationStart(animation)
                        binding.llInputTourist.isVisible = true
                    }
                })
                .start()
        }

        private fun editFieldsDisappear() {
            binding.llInputTourist.animate()
                .scaleY(0f)
                .translationYBy(-700f)
                .setDuration(200)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        binding.llInputTourist.isVisible = false
                        super.onAnimationEnd(animation)
                    }
                })
                .start()
        }

        private fun iconRotating() {
            binding.ivIconTouristInfo.animate()
                .rotationBy(180f)
                .setDuration(400)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        super.onAnimationStart(animation)
                        binding.flShowTouristInfo.isClickable = false
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        binding.flShowTouristInfo.isClickable = true
                        super.onAnimationEnd(animation)

                    }
                })
                .start()
        }
    }

    private val numbers = arrayListOf(
        R.string.first,
        R.string.second,
        R.string.third,
        R.string.fourth,
        R.string.fifth,
        R.string.sixth,
        R.string.seventh
    )

    private val maxTourist
        get() = numbers.size

    private val touristList = arrayListOf(
        TouristState(numbers[0]),
        TouristState(TouristState.empty)
    )

    var showErrors = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun isTouristInfoValid() = touristList.any { it.isValid }

    private fun addTourist(touristState: TouristState) {
        showErrors = false
        touristList[touristList.size - 1] = touristState
        notifyItemChanged(touristList.size - 1)
        if (touristList.size < maxTourist) {
            touristList.add(TouristState(TouristState.empty))
        }
        notifyItemInserted(touristList.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemRvTouristBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return touristList.size
    }
}


