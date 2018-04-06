package com.sergioborne.themoviedb.diimport com.facebook.stetho.okhttp3.StethoInterceptorimport com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactoryimport com.sergioborne.themoviedb.BuildConfigimport com.sergioborne.themoviedb.common.TheMovieDBAPIimport dagger.Moduleimport dagger.Providesimport okhttp3.Interceptorimport okhttp3.OkHttpClientimport retrofit2.Retrofitimport retrofit2.converter.gson.GsonConverterFactoryimport javax.inject.Namedimport javax.inject.Singleton@Moduleclass NetworkModule {  companion object {    private const val API_KEY_QUERY = "api_key"    private const val API_VERSION = 3  }  private val authInterceptor = Interceptor { chain ->    val urlBuilder = chain.request().url().newBuilder()    urlBuilder.addEncodedQueryParameter(API_KEY_QUERY, BuildConfig.TheMovieDBApiKey)    val request = chain.request()        .newBuilder().url(urlBuilder.build())        .build()    chain.proceed(request)  }  @Provides  @Named("apiBaseUri")  internal fun provideBaseUrl(): String {    return "https://api.themoviedb.org/$API_VERSION/"  }  @Provides  @Singleton  internal fun provideApiService(@Named("apiBaseUri") apiBaseUri: String): TheMovieDBAPI {    val builder = OkHttpClient.Builder()    builder.addNetworkInterceptor(authInterceptor)    if (BuildConfig.DEBUG) {      builder.addNetworkInterceptor(StethoInterceptor())    }    val client = builder.build()    val retrofit = Retrofit.Builder().baseUrl(apiBaseUri)        .addConverterFactory(GsonConverterFactory.create())        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())        .client(client)        .build()    return retrofit.create(TheMovieDBAPI::class.java)  }}