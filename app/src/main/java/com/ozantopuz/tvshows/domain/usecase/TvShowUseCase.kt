package com.ozantopuz.tvshows.domain.usecase

import com.ozantopuz.tvshows.data.Result
import com.ozantopuz.tvshows.domain.mapper.TvShowViewItemMapper
import com.ozantopuz.tvshows.domain.repository.TvShowRepository
import com.ozantopuz.tvshows.data.entity.TvShowResponse
import com.ozantopuz.tvshows.data.entity.TvShowResponseViewItem
import com.ozantopuz.tvshows.util.usecase.Params
import com.ozantopuz.tvshows.util.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TvShowUseCase @Inject constructor(
    private val repository: TvShowRepository,
    private val mapper: TvShowViewItemMapper
) : UseCase.FlowUseCase<TvShowParams, TvShowResponseViewItem> {

    override fun execute(params: TvShowParams): Flow<Result<TvShowResponseViewItem>> {
        return flow {
            val response = getTvShows(params)
            val viewItem = mapper.map(response)
            emit(Result.Success(viewItem))
        }.catch { throwable ->
            Result.Error(Exception(throwable))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getTvShows(params: TvShowParams) = coroutineScope {
        val response: TvShowResponse = try {
            repository.fetchTvShows(params.page)
        } catch (e: Exception) {
            TvShowResponse()
        }
        response
    }
}

data class TvShowParams(
    val page: String
) : Params()