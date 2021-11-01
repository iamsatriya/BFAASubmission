package com.satriyawicaksana.bfaasubmission.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.satriyawicaksana.bfaasubmission.adapter.SearchResultAdapter
import com.satriyawicaksana.bfaasubmission.databinding.FragmentSearchBinding
import com.satriyawicaksana.bfaasubmission.pojo.ResponseSearch

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.setListUser("satriya")

        searchViewModel.listUser.observe(viewLifecycleOwner, {userList ->
            if (userList != null) {
                showRecyclerCardView(requireContext(), userList)
            }else{
                showRecyclerCardView(requireContext(), ArrayList())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showRecyclerCardView(context: Context, list: ArrayList<ResponseSearch>){
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val searchResultAdapter = SearchResultAdapter(list)
        binding.recyclerView.adapter = searchResultAdapter
        searchResultAdapter.setOnItemClickCallback(object : SearchResultAdapter.OnItemCliclCallback{
            override fun onItemClicked(user: ResponseSearch) {
                TODO("Not yet implemented")
                Log.e("Search Fragment", "onItemClicked: hehe", )
            }
        })
    }
}