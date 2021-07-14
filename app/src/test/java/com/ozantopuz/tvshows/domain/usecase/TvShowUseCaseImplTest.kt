package com.ozantopuz.tvshows.domain.usecase

import com.google.common.truth.Truth
import com.ozantopuz.tvshows.Constants
import com.ozantopuz.tvshows.data.Result
import com.ozantopuz.tvshows.data.entity.TvShowResponseFactory
import com.ozantopuz.tvshows.domain.mapper.TvShowViewItemMapper
import com.ozantopuz.tvshows.domain.repository.TvShowRepository
import com.ozantopuz.tvshows.rule.CoroutinesTestRule
import com.ozantopuz.tvshows.util.test
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TvShowUseCaseImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @MockK
    lateinit var tvShowRepository: TvShowRepository

    lateinit var tvShowViewItemMapper: TvShowViewItemMapper

    private lateinit var tvShowUseCase: TvShowUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        coEvery {
            tvShowRepository.fetchTvShows(Constants.PAGE)
        } coAnswers {
            TvShowResponseFactory.getTvShowResponse()
        }

        tvShowViewItemMapper = TvShowViewItemMapper()
        tvShowUseCase = TvShowUseCase(tvShowRepository, tvShowViewItemMapper)
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun test_execute_returns_the_expected_data() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // Given

            // When
            val flow = tvShowUseCase.execute(TvShowParams(Constants.PAGE))

            // Then
            flow.test {
                expectItem().run {
                    Truth.assertThat(this).isNotNull()
                    Truth.assertThat(this).isInstanceOf(Result.Success::class.java)
                }
                expectComplete()
            }

            coVerify(exactly = 1) { tvShowRepository.fetchTvShows(Constants.PAGE) }
            confirmVerified(tvShowRepository)
        }
    }
}