/*
 *  Retrofit позволяет программе общаться с веб-сервисом и получать из него данные. Чтобы использовать
 * Retrofit, необходимо подключить 2 библиотеки, одна из которых подключает Retrofit, а вторая
 * конвертирует данные, полученные от сервиса, в необходимый для нас тип данных.
 *  Сперва необходимо создать обект Retrofit-а с помощью Retrofit.Builder(). Чтобы заработать,
 * данному обекту необходимы как минимум 2 вещи: baseUrl для веб-сервиса и converter factory.
 */
package com.example.android.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/*
 * Here we create a moshi object using the Moshi builder.
 * For Moshi's annotations to work properly with Kotlin, add the KotlinJsonAdapterFactory
 */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

/*
 *  Создаем интерфейс, который определяет, как Retrofit будет общаться с веб-сервисом.
 *  Чтобы получить Json ответ от веб-сервиса создаем функцию getProperties(), при вызове
 * которой функция обратится к аннотации GET и добавит к Url-ссылке "realestate" и создаст обект
 * Call, который используется, чтобы начать запрос и будет возвращать лист необходимых нам
 * элементов.
 */
interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>
}

/*
 *  Создаем Retrofit Service вызовом метода .create() с использованием интерфейса MarsApiService.
 * Так как такой вызов дорогой по нагрузке на память, а нам необходим только один экземпляр Retrofit
 * Service, мы предоставляем сервис используя публичный объект MarsApi и инициализируем в нем
 * retrofitService. Каждый раз, когда наше приложение будет запрашивать MarsApi.retrofitService,
 * он вернет синглтон объекта Retrofit, который имплементирует MarsApiService.
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}