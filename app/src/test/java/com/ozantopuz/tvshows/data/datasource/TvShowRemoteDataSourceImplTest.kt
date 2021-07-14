package com.ozantopuz.tvshows.data.datasource

import com.google.common.truth.Truth
import com.ozantopuz.tvshows.Constants
import com.ozantopuz.tvshows.data.entity.*
import com.ozantopuz.tvshows.data.mapper.TvShowDomainMapper
import com.ozantopuz.tvshows.data.service.TvShowService
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
class TvShowRemoteDataSourceImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @MockK
    lateinit var tvShowService: TvShowService

    lateinit var tvShowDomainMapper: TvShowDomainMapper

    private lateinit var tvShowRemoteDataSource: TvShowRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        tvShowDomainMapper = TvShowDomainMapper()
        tvShowRemoteDataSource = TvShowRemoteDataSourceImpl(tvShowService, tvShowDomainMapper)
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun test_fetchTvShows_returns_the_expected_data() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // Given
            coEvery {
                tvShowService.getTvShows(Constants.PAGE)
            }.coAnswers {
                TvShowResponseRawFactory.getTvShowResponseRaw()
            }

            // When
            val result: TvShowResponse = tvShowRemoteDataSource.fetchTvShows(Constants.PAGE)

            // Then
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result).isEqualTo(TvShowResponseFactory.getTvShowResponse())

            coVerify(exactly = 1) { tvShowService.getTvShows(Constants.PAGE) }
            confirmVerified(tvShowService)
        }
    }

    @Test
    fun test_fetchTvShowsContract_returns_error() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // Given
            coEvery {
                tvShowService.getTvShows(Constants.PAGE)
            }.coAnswers {
                TvShowResponseRaw(null)
            }

            // When
            val result: TvShowResponse = tvShowRemoteDataSource.fetchTvShows(Constants.PAGE)

            // Then
            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result).isEqualTo(TvShowResponse(null))

            coVerify(exactly = 1) { tvShowService.getTvShows(Constants.PAGE) }
            confirmVerified(tvShowService)
        }
    }
}