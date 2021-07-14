package com.ozantopuz.tvshows.util.usecase

import kotlinx.coroutines.flow.Flow
import com.ozantopuz.tvshows.data.Result

interface UseCase {

    @FunctionalInterface
    interface FlowUseCase<in P, out T> : UseCase where P : Params {

        fun execute(params: P): Flow<Result<T>>
    }
}

abstract class Params