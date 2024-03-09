package com.example.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myflexiblefragment.databinding.FragmentDetailCategoryBinding

class DetailCategoryFragment : Fragment() {

    private lateinit var fragmentDetailCategoryBinding: FragmentDetailCategoryBinding
    var description: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailCategoryBinding.bind(view)
        fragmentDetailCategoryBinding = binding

        if (savedInstanceState != null){
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }

        if (arguments != null){
            val categoryname = arguments?.getString(EXTRA_NAME)
            fragmentDetailCategoryBinding.tvCategoryName.text = categoryname
            fragmentDetailCategoryBinding.tvCategoryDescription.text = description
        }

        fragmentDetailCategoryBinding.btnShowDialog.setOnClickListener{
            val optionDialogFragment = OptionalDialogFragment()

            val fragmentManager = childFragmentManager
            optionDialogFragment.show(fragmentManager, OptionalDialogFragment::class.java.simpleName)
        }
    }

    internal var optionDialogListener: OptionalDialogFragment.OnOptionDialogListener = object : OptionalDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }
}