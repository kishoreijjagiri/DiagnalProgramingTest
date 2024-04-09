package com.example.diagnalprogramingtest.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.diagnalprogramingtest.data.dto.Content

class MovieItemPagingSource(var searchQury: String? = null, private val repository: Repository) :
    PagingSource<Int, Content>() {
    override fun getRefreshKey(state: PagingState<Int, Content>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        val position = params.key ?: STARTING_PAGE_INDEX

        val result = repository.getData(position)

        return LoadResult.Page(
            data = result.contentItems.content.filter { s ->
                s.name.contains(
                    searchQury ?: "",
                    true
                )
            },
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (position == 3) null else position + 1
        )


    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
