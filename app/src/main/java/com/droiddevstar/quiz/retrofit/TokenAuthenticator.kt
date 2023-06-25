package com.droiddevstar.quiz.retrofit

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        println("@@@authenticate: route: $route, response: $response ")
        return null
    }

    private fun getUpdatedToken(): String {
        val requestParams = HashMap<String, String>()

//        val authTokenResponse = ApiClient.userApiService.getAuthenticationToken(requestParams).execute().body()!!

//        val newToken = "${authTokenResponse.tokenType} ${authTokenResponse.accessToken}"
//        println("newToken: $newToken")
//        return newToken
        return "newToken"
    }
}