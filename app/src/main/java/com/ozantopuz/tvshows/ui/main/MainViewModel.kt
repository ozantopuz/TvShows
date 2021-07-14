package com.ozantopuz.tvshows.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozantopuz.tvshows.data.Result
import com.ozantopuz.tvshows.domain.usecase.TvShowParams
import com.ozantopuz.tvshows.domain.usecase.TvShowUseCase
import com.ozantopuz.tvshows.data.entity.TvShow
import com.ozantopuz.tvshows.data.entity.TvShowResponseViewItem
import com.ozantopuz.tvshows.data.enums.SortingType
import com.ozantopuz.tvshows.util.extension.toDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: TvShowUseCase
) : ViewModel() {

    private var list: ArrayList<TvShow> = arrayListOf()

    private val _tvShowsLiveData = MutableLiveData<ArrayList<TvShow>>()
    val tvShowLiveData: LiveData<ArrayList<TvShow>>
        get() = _tvShowsLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData


    fun fetchTvShows() {
        viewModelScope.launch {
            useCase.execute(TvShowParams(PAGE.toString())).collect { result ->
                handleData(result)
            }
        }
    }

    private fun handleData(result: Result<TvShowResponseViewItem>) {
        when (result) {
            is Result.Error -> _errorLiveData.value = result.exception.localizedMessage
            is Result.Success -> {
                result.data.tvShows?.let { arrayList: ArrayList<TvShow> ->
                    list = arrayList
                    _tvShowsLiveData.value = arrayList
                }
            }
        }
    }

    fun sortList(type: Int) {
        val sortedList = when (SortingType.from(type)) {
            SortingType.ALPHABETICAL -> list.sortedBy { it.originalName }
            SortingType.POPULARITY -> list.sortedBy { it.popularity }
            SortingType.DATE -> list.sortedBy { it.firstAirDate.toDate() }
            SortingType.RATING -> list.sortedBy { it.voteAverage }
            else -> list
        }

        _tvShowsLiveData.value = ArrayList(sortedList)
    }

    companion object {
        const val PAGE = 1
    }
}