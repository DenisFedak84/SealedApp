package com.example.sealedapp.usecase

abstract class BaseUseCase {

   var error = Resource.Error<Any>("General error")
   var success = Resource.Success<Any>(null)

    abstract suspend fun invoke(request: Any? = null): Resource<Any>

    fun useCaseResult(): Resource<Any> {
         return if (success.data != null) {
             success
         } else {
             error
         }
     }
}