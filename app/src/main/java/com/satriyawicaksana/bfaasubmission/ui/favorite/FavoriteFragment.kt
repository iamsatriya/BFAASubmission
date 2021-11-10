package com.satriyawicaksana.bfaasubmission.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.satriyawicaksana.bfaasubmission.adapter.FavoriteAdapter
import com.satriyawicaksana.bfaasubmission.database.Favorite
import com.satriyawicaksana.bfaasubmission.databinding.FragmentFavoriteBinding
import com.satriyawicaksana.bfaasubmission.pojo.ResponseDetailUser
import com.satriyawicaksana.bfaasubmission.screen.userprofile.DetailProfileActivity
import com.satriyawicaksana.bfaasubmission.utils.ApiConfig
import com.satriyawicaksana.bfaasubmission.utils.FavoriteViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private lateinit var adapter: FavoriteAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            adapter.setListFavorites(listFavorite)
            if (listFavorite.isEmpty()){
                binding.ivFavoriteNotFound.visibility = View.VISIBLE
                binding.rvFavorites.visibility = View.GONE
            }else{
                binding.ivFavoriteNotFound.visibility = View.GONE
                binding.rvFavorites.visibility = View.VISIBLE
            }
        })
        adapter = FavoriteAdapter()
        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorites.setHasFixedSize(true)
        binding.rvFavorites.adapter = adapter

        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback{
            override fun onItemClicked(favorite: Favorite) {
                openUserDetail(favorite.login)
            }
        })
    }

    private fun openUserDetail(login: String) {
        val client = ApiConfig.getApiService().getUserDetail(login)
        client.enqueue(object : Callback<ResponseDetailUser>{
            override fun onResponse(
                call: Call<ResponseDetailUser>,
                response: Response<ResponseDetailUser>
            ) {
                if (response.isSuccessful) {
                    val mIntent = Intent(context, DetailProfileActivity::class.java)
                    mIntent.putExtra(DetailProfileActivity.EXTRA_USER, response.body())
                    mIntent.putExtra(DetailProfileActivity.EXTRA_CALLER, DetailProfileActivity.FAVORITE_FRAGMENT_ID)
                    context?.startActivity(mIntent)
                } else {
                    Toast.makeText(requireContext(), "Sorry! ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseDetailUser>, t: Throwable) {
                Toast.makeText(requireContext(), "Sorry ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}