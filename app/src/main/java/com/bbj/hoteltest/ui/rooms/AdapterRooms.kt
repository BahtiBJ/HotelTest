package com.bbj.hoteltest.ui.rooms

import com.bbj.data.models.DataRoom
import com.bbj.hoteltest.R
import com.bbj.hoteltest.databinding.ItemRvRoomBinding
import com.bbj.hoteltest.ui.extensions.createPeculiarity
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class AdapterRooms(chooseRoom: (DataRoom) -> Unit) : ListDelegationAdapter<List<DataRoom>>(
    roomListAdapterDelegate(chooseRoom)
)

private fun roomListAdapterDelegate(chooseRoom: (DataRoom) -> Unit) = adapterDelegateViewBinding<DataRoom,DataRoom, ItemRvRoomBinding>(
    viewBinding = {inflater,parent -> ItemRvRoomBinding.inflate(inflater,parent,false)}
){
    binding.btnChooseRoom.setOnClickListener {
        chooseRoom(item)
    }
    bind {
        binding.tvHotelName.text = item.name
        binding.tvHotelPricePer.text = item.pricePer
        binding.tvHotelPrice.text = binding.root.context.getString(R.string.price_d,item.price)
        binding.isSlider.setImagePaths(item.imageU1rls)
        item.peculiarities.forEach {
            binding.flexRoomAdvantages.createPeculiarity(it)
        }
    }
}