package com.ozantopuz.tvshows.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import com.ozantopuz.tvshows.Constants
import com.ozantopuz.tvshows.data.Result
import com.ozantopuz.tvshows.data.entity.TvShow
import com.ozantopuz.tvshows.data.entity.TvShowResponseViewItem
import com.ozantopuz.tvshows.data.entity.TvShowResponseViewItemFactory
import com.ozantopuz.tvshows.domain.usecase.TvShowParams
import com.ozantopuz.tvshows.domain.usecase.TvShowUseCase
import com.ozantopuz.tvshows.rule.CoroutinesTestRule
import com.ozantopuz.tvshows.rule.LifeCycleTestOwner
import com.ozantopuz.tvshows.ui.main.MainViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var tvShowUseCase: TvShowUseCase

    @RelaxedMockK
    private lateinit var tvShowsObserver: Observer<ArrayList<TvShow>>

    @RelaxedMockK
    private lateinit var errorObserver: Observer<String>

    private lateinit var viewModel: MainViewModel
    private lateinit var lifeCycleTestOwner: LifeCycleTestOwner

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        lifeCycleTestOwner = LifeCycleTestOwner()
        lifeCycleTestOwner.onCreate()

        viewModel = MainViewModel(tvShowUseCase)
    }

    @After
    fun tearDown() {
        lifeCycleTestOwner.onDestroy()

        viewModel.tvShowLiveData.removeObserver(tvShowsObserver)
        viewModel.tvShowLiveData.removeObservers(lifeCycleTestOwner)

        viewModel.errorLiveData.removeObserver(errorObserver)
        viewModel.errorLiveData.removeObservers(lifeCycleTestOwner)

        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun test_fetchTvShows_returns_success_data() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // Given
            coEvery {
                tvShowUseCase.execute(TvShowParams(Constants.PAGE))
            }.coAnswers {
                flowOf(Result.Success(TvShowResponseViewItemFactory.getTvShowResponseViewItem()))
            }

            lifeCycleTestOwner.onResume()

            // When
            viewModel.fetchTvShows()
            val tvShowsValue = viewModel.tvShowLiveData.value
            val errorValue = viewModel.errorLiveData.value

            // Then
            Truth.assertThat(tvShowsValue).isNotNull()
            Truth.assertThat(errorValue).isNull()

            coVerify(exactly = 1) { tvShowUseCase.execute(TvShowParams(Constants.PAGE)) }
            confirmVerified(tvShowUseCase)
        }
    }

    @Test
    fun test_fetchPersonalContract_returns_error() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // Given
            coEvery {
                tvShowUseCase.execute(TvShowParams(Constants.PAGE))
            }.coAnswers {
                flowOf(Result.Error(Exception(Constants.EXCEPTION_MESSAGE)))
            }

            lifeCycleTestOwner.onResume()

            // When
            viewModel.fetchTvShows()
            val tvShowsValue = viewModel.tvShowLiveData.value
            val errorValue = viewModel.errorLiveData.value

            // Then
            Truth.assertThat(tvShowsValue).isNull()
            Truth.assertThat(errorValue).isNotNull()

            coVerify(exactly = 1) { tvShowUseCase.execute(TvShowParams(Constants.PAGE)) }
            confirmVerified(tvShowUseCase)
        }
    }
}