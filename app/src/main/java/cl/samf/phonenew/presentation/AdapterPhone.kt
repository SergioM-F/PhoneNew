package cl.samf.phonenew.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.samf.phonenew.data.local.PhoneEntity
import cl.samf.phonenew.databinding.ActivityMainBinding
import cl.samf.phonenew.databinding.ItemListPhoneBinding
import coil.load

class AdapterPhone: RecyclerView.Adapter<AdapterPhone.PhoneViewHolder>() {

    lateinit var binding: ItemListPhoneBinding
    private val listItemPhones = mutableListOf<PhoneEntity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        binding = ItemListPhoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItemPhones.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        val phone = listItemPhones[position]
        holder.bind(phone)
    }

    class PhoneViewHolder(val binding: ItemListPhoneBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(phone: PhoneEntity) {
            binding.imageViewTerreno.load(phone.image)
            binding.textViewName.text = phone.name
            binding.textViewPrice.text = phone.price.toString()

        }

    }

    fun setDataPhone(phone: List<PhoneEntity>){
        this.listItemPhones.clear()
        this.listItemPhones.addAll(phone)
        notifyDataSetChanged()
    }

}