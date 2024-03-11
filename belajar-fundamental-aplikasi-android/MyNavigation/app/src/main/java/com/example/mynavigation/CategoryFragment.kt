package com.example.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.mynavigation.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment() {

    private var binding: FragmentCategoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * SEND DATA WITH SAFE-ARGS
         */
        binding?.btnCategoryLifestyle?.setOnClickListener {
            val toDetailCategoryFragment = CategoryFragmentDirections.actionCategoryFragmentToDetailCategoryFragment()
            toDetailCategoryFragment.apply {
                name = "lifestyle"
                stock = 7
            }
            it.findNavController().navigate(toDetailCategoryFragment)
        }

        /**
         * SEND DATA WITH BUNDLE
         */
//        binding?.btnCategoryLifestyle?.setOnClickListener{
//            val bundle = Bundle()
//            bundle.apply {
//                putString("EXTRA_NAME", "lifecycle")
//                putLong("EXTRA_STOCK", 7)
//            }
//            it.findNavController().navigate(R.id.action_categoryFragment_to_detailCategoryFragment, bundle)
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_STOCK = "extra_stock"
    }
}