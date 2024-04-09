package com.example.diagnalprogramingtest.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diagnalprogramingtest.R
import com.example.diagnalprogramingtest.common.readJsonFromAssets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MyViewModel by viewModels()

    @Inject
    lateinit var moviadpter: MyItemRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val searchView = findViewById<SearchView>(R.id.search_view)

        recyclerView.adapter = moviadpter

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = GridLayoutManager(this, 7)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 3)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {


                lifecycleScope.launch {
                    mainViewModel.search(p0).collect {
                        moviadpter.submitData(it)
                    }
                }



                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                if (p0?.isEmpty() == true) {
                    lifecycleScope.launch {
                        mainViewModel.search(p0).collect {
                            moviadpter.submitData(it)
                        }
                    }
                }
                return false
            }

        })


        lifecycleScope.launch {
            mainViewModel.movilist.collect {
                moviadpter.submitData(it)
            }
        }


    }


}