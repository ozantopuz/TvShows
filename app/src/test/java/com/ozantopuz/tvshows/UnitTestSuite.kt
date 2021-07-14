/*
 * Copyright (C) 2020. Nuh Koca. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ozantopuz.tvshows

import com.ozantopuz.tvshows.data.datasource.TvShowRemoteDataSourceImplTest
import com.ozantopuz.tvshows.domain.repository.TvShowRepositoryImplTest
import com.ozantopuz.tvshows.domain.usecase.TvShowUseCaseImplTest
import com.ozantopuz.tvshows.ui.MainViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    TvShowRemoteDataSourceImplTest::class,
    TvShowRepositoryImplTest::class,
    TvShowUseCaseImplTest::class,
    MainViewModelTest::class
)
object UnitTestSuite
