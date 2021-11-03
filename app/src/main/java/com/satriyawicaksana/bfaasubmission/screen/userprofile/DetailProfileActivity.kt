package com.satriyawicaksana.bfaasubmission.screen.userprofile

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
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
        private val TAB_TITLES = intArrayOf(
            R.string.tab_title_1,
            R.string.tab_title_2,
        )
    }

    private var _binding: ActivityDetailProfileBinding? = null
    private val binding get() = _binding!!

    //    private val viewModel: DetailProfileViewModel by viewModels()
    private lateinit var viewModel: DetailProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = obtainViewModel(this@DetailProfileActivity)
//        viewModel = ViewModelProvider(this).get(DetailProfileViewModel::class.java)

        val user = intent.getParcelableExtra<ResponseDetailUser>(EXTRA_USER) as ResponseDetailUser
        viewModel.setUser(user)
        viewModel.userDetail.observe(this, { users ->
            if (users != null) {
                setProfile(users)
            } else {
                displayNullUser()
            }
        })
        val sectionPageAdapter = SectionPageAdapter(this)
        binding.vpFollow.adapter = sectionPageAdapter
        TabLayoutMediator(binding.tlFollow, binding.vpFollow) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        binding.btnFavorite.setOnClickListener {
            viewModel.insert()
            Toast.makeText(
                this,
                "${viewModel.userDetail.value?.login} added to favorites",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun obtainViewModel(detailProfileActivity: DetailProfileActivity): DetailProfileViewModel {
        val factory = FavoriteViewModelFactory.getInstance(detailProfileActivity.application)
        return ViewModelProvider(
            detailProfileActivity,
            factory
        ).get(DetailProfileViewModel::class.java)
    }

    private fun displayNullUser() {
        TODO("Not yet implemented")
    }

    private fun setProfile(user: ResponseDetailUser) {
        binding.profileImage.load(user.avatarUrl)
        binding.tvPublicRepo.text = user.publicRepos.toString()

//        binding.tvFullName.text = user.name ?: "Unknown"
//        binding.tvLocation.text = user.location ?: "Unknown"
//        binding.tvOffice.text = user.company ?: "Unknown"
//        binding.tvEmail.text = user.email ?: "Unknown"
//        binding.tvLogin.text = user.login
        checkNullValue(binding.tvLogin, user.login)
        checkNullValue(binding.tvFullName, user.name)
        checkNullValue(binding.tvOffice, user.company)
        checkNullValue(binding.tvLocation, user.location)
        checkNullValue(binding.tvEmail, user.email)
    }

    private fun checkNullValue(textView: TextView, text: String?) {
        textView.text = text ?: "Unknown"
//        if (text != null) {
//            textView.text = text
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                textView.setTextAppearance(R.style.TextAppearance_AppCompat_Subhead)
//            } else {
//                textView.setTextAppearance(this, R.style.TextAppearance_AppCompat_Subhead)
//            }
//        }else{
//            textView.setText(R.string.unknown)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                textView.setTextAppearance(R.style.TextAppearance_AppCompat_Body1)
//            } else {
//                textView.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body1)
//            }
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}