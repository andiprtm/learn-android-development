package com.example.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import androidx.fragment.app.commit
import com.example.myflexiblefragment.databinding.ActivityMainBinding
import com.example.myflexiblefragment.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        fragmentHomeBinding = binding
        binding.btnCategroy.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == fragmentHomeBinding.btnCategroy.id){
            val fragmentManager = parentFragmentManager
            val categoryFragment = CategoryFragment()
            fragmentManager.commit {
                addToBackStack(null)
                replace(R.id.frame_container, categoryFragment, CategoryFragment::class.java.simpleName)
            }
        }
    }
}