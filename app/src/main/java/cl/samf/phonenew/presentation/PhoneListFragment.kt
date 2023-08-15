package cl.samf.phonenew.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.samf.phonenew.R
import cl.samf.phonenew.databinding.FragmentPhoneListBinding
import cl.samf.phonenew.databinding.ItemListPhoneBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PhoneListFragment : Fragment() {

    lateinit var binding: FragmentPhoneListBinding
    private val phoneViewModel: PhoneViewModel by activityViewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneListBinding.inflate(layoutInflater,container,false)
        initAdapter()
        phoneViewModel.getPhone()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterPhone()
        binding.recyclerViewListPhone.adapter = adapter
        phoneViewModel.phoneLiveData().observe(viewLifecycleOwner){
            adapter.setDataPhone(it)
        }
    }

}