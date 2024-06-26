package com.example.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.mynavigation.databinding.FragmentDetailCategoryBinding

class DetailCategoryFragment : Fragment() {

    private var binding: FragmentDetailCategoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailCategoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * RECEIVE DATA WITH SAFE-ARGS
         */
        val dataName = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).name
        val dataStock = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).stock

        /**
         * RECEIVE DATA WITH BUNDLE
         */

//        val dataName = arguments?.getString(CategoryFragment.EXTRA_NAME)
//        val dataStock = arguments?.getLong(CategoryFragment.EXTRA_STOCK)

        binding?.tvCategoryName?.text = dataName.toString()
        binding?.tvCategoryDescription?.text = getString(R.string.stock, dataStock.toString())

        binding?.btnHome?.setOnClickListener {
            it.findNavController().navigate(DetailCategoryFragmentDirections.actionDetailCategoryFragmentToHomeFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}