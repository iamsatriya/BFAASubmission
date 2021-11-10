package com.satriyawicaksana.bfaasubmission.screen.userprofile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.satriyawicaksana.bfaasubmission.R
import com.satriyawicaksana.bfaasubmission.adapter.SectionPageAdapter
import com.satriyawicaksana.bfaasubmission.databinding.ActivityDetailProfileBinding
import com.satriyawicaksana.bfaasubmission.pojo.ResponseDetailUser
import com.satriyawicaksana.bfaasubmission.utils.FavoriteViewModelFactory

class DetailProfileActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_CALLER = "extra_caller"
        const val FAVORITE_FRAGMENT_ID = 100
        const val SEARCH_FRAGMENT_ID = 101
        private val TAB_TITLES = intArrayOf(
            R.string.tab_title_1,
            R.string.tab_title_2,
        )
    }

    private var _binding: ActivityDetailProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = obtainViewModel(this@DetailProfileActivity)

        val user = intent.getParcelableExtra<ResponseDetailUser>(EXTRA_USER) as ResponseDetailUser
        viewModel.setUser(user)
        viewModel.userDetail.observe(this, { users ->
            if (users != null) {
                setProfile(users)
            }
        })
        val sectionPageAdapter = SectionPageAdapter(this)
        binding.vpFollow.adapter = sectionPageAdapter
        TabLayoutMediator(binding.tlFollow, binding.vpFollow) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        setFavoriteButton()
    }

    private fun setFavoriteButton() {
        val buttonCode = intent.getIntExtra(EXTRA_CALLER, 0)
        when (buttonCode) {
            FAVORITE_FRAGMENT_ID -> {
                binding.btnFavorite.setImageResource(R.drawable.ic_baseline_remove_circle_24)
            }
            SEARCH_FRAGMENT_ID -> {
                binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        }
        binding.btnFavorite.setOnClickListener {
            when (buttonCode) {
                SEARCH_FRAGMENT_ID -> {
                    viewModel.insert()
                    binding.btnFavorite.setImageResource(R.drawable.ic_baseline_remove_circle_24)
                    Toast.makeText(
                        this,
                        "${viewModel.userDetail.value?.login} added to favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                FAVORITE_FRAGMENT_ID -> {
                    viewModel.delete()
                    binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                    Toast.makeText(
                        this,
                        "${viewModel.userDetail.value?.login} removed from favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun obtainViewModel(detailProfileActivity: DetailProfileActivity): DetailProfileViewModel {
        val factory = FavoriteViewModelFactory.getInstance(detailProfileActivity.application)
        return ViewModelProvider(
            detailProfileActivity,
            factory
        ).get(DetailProfileViewModel::class.java)
    }

    private fun setProfile(user: ResponseDetailUser) {
        binding.profileImage.load(user.avatarUrl)
        binding.tvPublicRepo.text = user.publicRepos.toString()
        binding.tvLogin.text = user.login

        binding.tvFullName.text = user.name ?: "Unknown"
        binding.tvLocation.text = user.location ?: "Unknown"
        binding.tvOffice.text = user.company ?: "Unknown"
        binding.tvEmail.text = user.email ?: "Unknown"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}