package com.dyh.presentation.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dyh.presentation.R
import com.dyh.presentation.databinding.ActivityMainBinding
import com.dyh.presentation.utils.isNetworkAvailable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private var adapter: MainAdapter? = MainAdapter()
    private val postViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewBinding.recyclerView.adapter = adapter

        if (isNetworkAvailable()) {
            postViewModel.getPosts()
        } else {
            Toast.makeText(
                this,
                getString(R.string.no_internet_connection),
                LENGTH_SHORT
            ).show()
        }

        with(postViewModel) {

            postsData.observe(this@MainActivity, Observer {
                viewBinding.progress.visibility = GONE
                adapter?.mPostList = it
            })

            messageData.observe(this@MainActivity, Observer {
                Toast.makeText(this@MainActivity, it, LENGTH_LONG).show()
            })

            isLoading.observe(this@MainActivity, Observer { isVisible ->
                viewBinding.progress.visibility = if (isVisible) VISIBLE else GONE
            })
        }
    }


    override fun onDestroy() {
        adapter?.notifyDataSetChanged()
        super.onDestroy()
    }

    companion object {
        private val TAG = MainActivity::class.java.name
    }
}
