package com.ozantopuz.tvshows.domain.repository

import com.google.common.truth.Truth
import com.ozantopuz.tvshows.Constants
import com.ozantopuz.tvshows.data.datasource.TvShowRemoteDataSource
import com.ozantopuz.tvshows.data.entity.TvShow
import com.ozantopuz.tvshows.data.entity.TvShowResponse
import com.ozantopuz.tvshows.data.entity.TvShowResponseFactory
import com.ozantopuz.tvshows.domain.repository.TvShowRepository
import com.ozantopuz.tvshows.domain.repository.TvShowRepositoryImpl
import com.ozantopuz.tvshows.rule.CoroutinesTestRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TvShowRepositoryImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @MockK
    lateinit var tvShowRemoteDataSource: TvShowRemoteDataSource

    private lateinit var tvShowRepository: TvShowRepository


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        tvShowRepository = TvShowRepositoryImpl(tvShowRemoteDataSource)
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun test_fetchTvShows_returns_success() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // Given
            coEvery {
                tvShowRemoteDataSource.fetchTvShows(Constants.PAGE)
            }.coAnswers {
                TvShowResponseFactory.getTvShowResponse()
            }

            // When
            val result: TvShowResponse = tvShowRepository.fetchTvShows(Constants.PAGE)

            // Then
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result).isEqualTo(TvShowResponseFactory.getTvShowResponse())

            coVerify(exactly = 1) { tvShowRemoteDataSource.fetchTvShows(Constants.PAGE) }
            confirmVerified(tvShowRemoteDataSource)
        }
    }

    @Test
    fun test_fetchTvShows_returns_error() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // Given
            coEvery {
                tvShowRemoteDataSource.fetchTvShows(Constants.PAGE)
            }.coAnswers {
                TvShowResponse(null)
            }

            // When
            val result: TvShowResponse = tvShowRepository.fetchTvShows(Constants.PAGE)

            // Then
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result).isEqualTo(TvShowResponse(null))

            coVerify(exactly = 1) { tvShowRemoteDataSource.fetchTvShows(Constants.PAGE) }
            confirmVerified(tvShowRemoteDataSource)
        }
    }
}