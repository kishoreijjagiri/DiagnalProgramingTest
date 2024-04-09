package com.example.diagnalprogramingtest.domain

import com.example.diagnalprogramingtest.data.dto.Page


interface Repository {

   suspend fun getData(pageNumber:Int) : Page
}