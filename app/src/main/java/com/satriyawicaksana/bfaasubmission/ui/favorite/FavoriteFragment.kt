package com.satriyawicaksana.bfaasubmission.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.satriyawicaksana.bfaasubmission.adapter.FavoriteAdapter
import com.satriyawicaksana.bfaasubmission.databinding.FragmentFavoriteBinding
import com.satriyawicaksana.bfaasubmission.utils.FavoriteViewModelFactory

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private lateinit var adapter: FavoriteAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        favoriteViewModel = obtainViewModel(requireActivity() as AppCompatActivity)
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = FavoriteViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel.getAllFavorites().observe(viewLifecycleOwner, { listFavorite ->
            if (listFavorite.isNotEmpty()){
                adapter.setListFavorites(listFavorite)
            }
        })
        adapter = FavoriteAdapter()
        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorites.setHasFixedSize(true)
        binding.rvFavorites.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}