//package com.droiddevstar.quiz.retrofit
//
//import okhttp3.Interceptor
//import okhttp3.Response
//
//class MyInterceptor : Interceptor {
//    var flag: Boolean = false
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        flag = !flag
//        println("@@@flag: $flag")
////        if (flag) {
//            val request = chain.request()
//            val response = chain.proceed(request)
//            return response
////        } else {
////            val request = chain.request()
////            val response = chain.proceed(request)
////            return response.newBuilder().code(401).build()
////        }
//
//    }
//}