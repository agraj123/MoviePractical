package com.example.practical.views

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.practical.MyApp
import com.example.practical.R
import com.example.practical.api.APIResponseState
import com.example.practical.databinding.ActivityMovieDetailBinding
import com.example.practical.viewmodels.MovieDetailViewModel
import com.example.practical.viewmodels.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var binding: ActivityMovieDetailBinding
    private var progressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        showLoading()

        binding.ivBack.setOnClickListener { finish() }

        movieDetailViewModel = ViewModelProvider(
            this,
            ViewModelFactory(MyApp.instance.getApiInstance(), MyApp.instance.getDatabase())
        )[MovieDetailViewModel::class.java]

        val movieId = intent.getStringExtra("movieId")

        if (!movieId.isNullOrEmpty()) {
            movieDetailViewModel.getMovieDetail(movieId)
        }

        movieDetailViewModel.getMovieDetail.observe(this) { response ->
            when (response) {
                is APIResponseState.Success -> {
                    binding.movieDetail = response.data
                    hideLoading()
                }

                is APIResponseState.Error -> {
                    hideLoading()
                    Toast.makeText(
                        this,
                        response.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun showLoading() {
        if (progressDialog == null) {
            progressDialog = Dialog(this)
        } else {
            return
        }
        val view = LayoutInflater.from(this).inflate(R.layout.app_loading_dialog, null, false)
        val imageView1 = view.findViewById<ImageView>(R.id.imageView2)
        val a1 = AnimationUtils.loadAnimation(this, R.anim.progress_anim)
        a1.duration = 1500
        imageView1.startAnimation(a1)

        progressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog?.setContentView(view)
        val window = progressDialog?.window
        window?.setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent))
        progressDialog?.setCancelable(false)
        progressDialog?.setCanceledOnTouchOutside(false)
        progressDialog?.show()
    }

    private fun hideLoading() {
        if (progressDialog != null) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

}
