package com.satriyawicaksana.bfaasubmission.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.satriyawicaksana.bfaasubmission.adapter.SearchResultAdapter
import com.satriyawicaksana.bfaasubmission.databinding.FragmentSearchBinding
import com.satriyawicaksana.bfaasubmission.pojo.ItemsItem
import com.satriyawicaksana.bfaasubmission.pojo.ResponseDetailUser
import com.satriyawicaksana.bfaasubmission.screen.userprofile.DetailProfileActivity
import com.satriyawicaksana.bfaasubmission.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    companion object {
        private val TAG = SearchFragment::class.java.simpleName
    }

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
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
        binding.etSearchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val username = p0.toString().trim()
                if (username.isNotEmpty()) {
                    showRecyclerCardView(view.context, ArrayList())
                    showLoading(true)
                    searchViewModel.setListUser(username)
                }
            }
        })
        binding.etSearchUser.setOnEditorActionListener { v, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    showRecyclerCardView(view.context, ArrayList())
                    searchViewModel.setListUser(v.text.toString().trim())
                    closeSoftKeyboard()
                    true
                }
                else -> false
            }
        }
        searchViewModel.listUser.observe(viewLifecycleOwner, { userList ->
            if (userList.isNullOrEmpty()) {
                showRecyclerCardView(requireContext(), ArrayList())
                showUserNotFound(true)

                Log.e(TAG, "not null: $userList")
            } else {
                showRecyclerCardView(requireContext(), userList)
                showLoading(false)
                showUserNotFound(false)
                Log.e(TAG, "not null: $userList")
            }
        })
    }

    private fun closeSoftKeyboard() {
        val imm =
            view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun showLoading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun showUserNotFound(isNotFound: Boolean) {
        binding.ivUserNotFound.visibility = if (isNotFound) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showRecyclerCardView(context: Context, list: ArrayList<ItemsItem>) {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val searchResultAdapter = SearchResultAdapter(list)
        binding.recyclerView.adapter = searchResultAdapter
        searchResultAdapter.setOnItemClickCallback(object :
            SearchResultAdapter.OnItemClickCallback {
            override fun onItemClicked(user: ItemsItem) {
                openUserDetail(user.login)
            }
        })
    }

    private fun openUserDetail(username: String) {
        val client = ApiConfig.getApiService().getUserDetail(username)
        client.enqueue(object : Callback<ResponseDetailUser> {
            override fun onResponse(
                call: Call<ResponseDetailUser>,
                response: Response<ResponseDetailUser>
            ) {
                if (response.isSuccessful) {
                    val mIntent = Intent(context, DetailProfileActivity::class.java)
                    mIntent.putExtra(DetailProfileActivity.EXTRA_USER, response.body())
                    mIntent.putExtra(
                        DetailProfileActivity.EXTRA_CALLER,
                        DetailProfileActivity.SEARCH_FRAGMENT_ID
                    )
                    context?.startActivity(mIntent)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Sorry! ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseDetailUser>, t: Throwable) {
                Toast.makeText(requireContext(), "Sorry ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}