package com.example.practical.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.practical.MyApp
import com.example.practical.R
import com.example.practical.adapter.AdapterMovies
import com.example.practical.api.APIResponseState
import com.example.practical.databinding.ActivityMainBinding
import com.example.practical.models.Search
import com.example.practical.viewmodels.MainViewModel
import com.example.practical.viewmodels.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private var page = 1
    private var mainMovieList = ArrayList<Search>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(MyApp.instance.getApiInstance(), MyApp.instance.getDatabase())
            )[MainViewModel::class.java]

        mainViewModel.getMovieList(page)

        binding.nestedView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                mainViewModel.getMovieList(page++)
            }
        })

        mainViewModel.getMovieList.observe(this) { response ->
            when (response) {
                is APIResponseState.Error -> {
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                }

                is APIResponseState.Success -> {
                    response.data?.Search?.let { movieList ->
                        mainMovieList.addAll(movieList)
                        binding.rcvMovies.adapter = AdapterMovies(mainMovieList)
                    }
                }
            }
        }
    }


}
