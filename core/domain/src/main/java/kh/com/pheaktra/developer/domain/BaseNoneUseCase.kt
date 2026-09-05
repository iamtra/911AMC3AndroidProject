package kh.com.pheaktra.developer.domain

// base/BaseUseCase.kt
abstract class BaseNoneUseCase<in Params, out Result> {

    operator fun invoke(params: Params): Result {
        return execute(params)
    }

    protected abstract fun execute(params: Params): Result
}