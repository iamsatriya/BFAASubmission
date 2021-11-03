package com.satriyawicaksana.bfaasubmission.ui.follower

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.satriyawicaksana.bfaasubmission.R
import com.satriyawicaksana.bfaasubmission.adapter.SearchResultAdapter
import com.satriyawicaksana.bfaasubmission.databinding.FollowerFragmentBinding
import com.satriyawicaksana.bfaasubmission.pojo.ItemsItem
import com.satriyawicaksana.bfaasubmission.screen.userprofile.DetailProfileViewModel

class FollowerFragment : Fragment() {
    private var _binding: FollowerFragmentBinding? = null
    private val binding get() = _binding!!
    private val model: DetailProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FollowerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFollower.setHasFixedSize(true)
        binding.rvFollower.layoutManager = LinearLayoutManager(this.context)
        model.listFollower.observe(viewLifecycleOwner, {list ->
            binding.rvFollower.adapter = SearchResultAdapter(list)
            binding.rvFollower.visibility = View.VISIBLE
            binding.pbFollower.visibility = View.INVISIBLE
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}