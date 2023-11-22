package com.bbj.hoteltest.ui.common

import com.bbj.hoteltest.databinding.ItemVpImageBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.squareup.picasso.Picasso

class AdapterImageSlider : ListDelegationAdapter<List<String>>(
    imagesAdapterDelagate()
)

private fun imagesAdapterDelagate() = adapterDelegateViewBinding<String,String, ItemVpImageBinding>(
    viewBinding = {inflater,root -> ItemVpImageBinding.inflate(inflater,root,false)}
){
    bind {
        Picasso.get()
            .load(item)
            .into(binding.ivHotelImage)
    }
}