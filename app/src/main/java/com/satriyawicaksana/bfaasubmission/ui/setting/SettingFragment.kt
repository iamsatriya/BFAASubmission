package com.satriyawicaksana.bfaasubmission.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.satriyawicaksana.bfaasubmission.MainViewModel
import com.satriyawicaksana.bfaasubmission.dataStore
import com.satriyawicaksana.bfaasubmission.databinding.FragmentSettingBinding
import com.satriyawicaksana.bfaasubmission.utils.SettingPreference
import com.satriyawicaksana.bfaasubmission.utils.ViewModelFactory

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preference = SettingPreference.getInstance(requireContext().dataStore)

        val mainViewModel = ViewModelProvider(this, ViewModelFactory(preference)).get(MainViewModel::class.java)
        mainViewModel.getThemeSetting().observe(viewLifecycleOwner, { isDarkMode ->
            binding.switchTheme.isChecked = isDarkMode
        })
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            mainViewModel.saveThemeSetting(isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}