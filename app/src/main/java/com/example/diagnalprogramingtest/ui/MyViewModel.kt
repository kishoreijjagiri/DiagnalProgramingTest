package com.example.diagnalprogramingtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.diagnalprogramingtest.domain.MovieItemPagingSource
import com.example.diagnalprogramingtest.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(var repository: Repository) : ViewModel() {


    fun search(p0: String?) = Pager(PagingConfig(1)) {
        MovieItemPagingSource(p0, repository)
    }.flow.cachedIn(viewModelScope)

    val movilist = Pager(PagingConfig(1)) {
        MovieItemPagingSource("", repository)
    }.flow.cachedIn(viewModelScope)

}