package com.diegoalvis.sandbox.domain.use_cases

import com.diegoalvis.sandbox.domain.Resource
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<in Input, out T> {

    abstract fun execute(input: Input) : Flow<Resource<T>>

}