package cl.samf.phonenew.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.samf.phonenew.R
import cl.samf.phonenew.databinding.FragmentPhoneDetailsBinding
import coil.load
import java.text.NumberFormat
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"


class PhoneDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPhoneDetailsBinding
    private val phoneViewModel: PhoneViewModel by activityViewModels()
    private var param1: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPhoneDetailsBinding.inflate(layoutInflater, container, false)
        initLoadDate()
        phoneViewModel.getDetailsPhone(param1!!.toInt())

        return binding.root

    }

    private fun initLoadDate() {
        //

        phoneViewModel.phoneIdLiveData(param1!!.toInt()).observe(viewLifecycleOwner) {
            if (it != null) {
                binding.textViewTitleDetails.text = it.name
                binding.imageViewDetails.load(it.image)
                binding.textViewDescription.text = it.description
                val chileLocale = Locale("es", "CL")
                val numberFormat = NumberFormat.getNumberInstance(chileLocale)
                val formattedPricelasPrice = numberFormat.format(it.lastPrice)
                val formattedPrice = numberFormat.format(it.price)
                binding.textViewLastPrice.text = "$$formattedPricelasPrice"
                binding.textViewPriceDetails.text = "$$formattedPrice"

                if(it.credit){
                    binding.textViewCreditDetails.text = "Acepta Tarjeta de Credito"
                }else{
                    binding.textViewCreditDetails.text = "No acepta Tarjeta de Credito"
                }
            }
        }

    }
}




