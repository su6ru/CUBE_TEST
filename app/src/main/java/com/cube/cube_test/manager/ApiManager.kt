package com.cube.cube_test.manager

import android.content.Context
import android.util.Log
import com.ci.v1_ci_view.ui.`interface`.IOnOptionLister
import com.cube.cube_test.R
import com.cube.cube_test.data.api.data.BaseData
import com.cube.cube_test.data.api.drawer.ApiAttractions
import com.cube.cube_test.data.api.drawer.ApiEvents
import com.cube.cube_test.data.define.CubeTestConfig
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.security.cert.X509Certificate
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
/** API呼叫的管理類 */
class ApiManager(context: Context) {
    var mContext: Context = context
    init {

    }
    fun createOkHttpClient() : OkHttpClient {
        val trustAllCerts: Array<TrustManager> = arrayOf(object : X509TrustManager {
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        return OkHttpClient
            .Builder()
            .addInterceptor(Interceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(newRequest)
            })
            .build()
    }



    val mIApiService: IApiService = Retrofit.Builder()
        .baseUrl(CubeTestConfig.Api.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()
        .create(IApiService ::class.java)

    inner class APICallBack<TResponse :BaseData.Response<*>> constructor(
        private val mSuccessListener : IOnOptionLister<TResponse>,
        private val mFailListener : IOnOptionLister<String>,
        private val mCompleteListener : IOnOptionLister<Void?>) :BaseData(),Callback<TResponse>{
        override fun onResponse(call: Call<TResponse>, response: retrofit2.Response<TResponse>) {
            val requestRaw = response.raw()
            val request = requestRaw.request()
            val httpUrl = request.url().toString()
            Log.d("api", "Request url=$httpUrl")
            val objectBody = request.body()
            if (objectBody!=null){
                val body = Gson().toJson(request.body())
                Log.d("api", "Request body=$body")
            }

            val message = response.message()
            Log.d("api",message)

            val body = response.body()
            if (body == null){
                onCallFail(mContext.getString(R.string.msg_connect_fail))
                return
            }

            val responseStr = Gson().toJson(body)
            //Log中經過多少長度後換行
            val maxLogSize = 1000
            val frequency = responseStr.length /maxLogSize
            for (i in 0..frequency){
                var start = i * maxLogSize
                var end = (i+1)*maxLogSize
                end = if (end > responseStr.length){
                    responseStr.length
                }else{
                    end
                }
                Log.d("api","onResponse:" + responseStr.substring(start, end))
            }
            if (response.code() != 200){
                onCallFail(mContext.getString(R.string.msg_data_anomaly))
                return
            }

            onCallSuccess(response.body()!!)

        }
        override fun onFailure(call: Call<TResponse>, t: Throwable) {
            onCallFail(t.message.toString())
        }
       /**當 回傳 成功*/
       private fun onCallSuccess(obj: TResponse) {
           mSuccessListener.onExecute(obj)
           mCompleteListener.onExecute(null)
       }

       /**當 回傳 失敗*/
       private fun onCallFail(message: String) {
           mFailListener.onExecute(message)
           mCompleteListener.onExecute(null)
       }
    }

    /** 遊憩景點API呼叫控制 */
    inner class APIEventsDrawer{
        fun callGetNews(
            url: String,
            page: String,
            successListener: IOnOptionLister<ApiEvents.GetNews.Response>,
            failListener: IOnOptionLister<String>,
            completeListener: IOnOptionLister<Void?>){

            mIApiService
                .getNews(url,page)
                .enqueue(APICallBack(
                    successListener,failListener,completeListener))
        }
    }
    /** 遊憩景點API呼叫控制 */
    inner class APIAttractionsDrawer{
        fun callGetAttractions(
            url: String,
            page: String,
            successListener: IOnOptionLister<ApiAttractions.GetAttractions.Response>,
            failListener: IOnOptionLister<String>,
            completeListener: IOnOptionLister<Void?>){

            mIApiService
                .getAttractions(url,page)
                .enqueue(APICallBack(
                    successListener,failListener,completeListener))
        }
    }



    // API 呼叫

    interface IApiService {
        //============================== 普通共用類
        /** 查詢遊憩景點  */
        @GET
        fun getAttractions(@Url url:String?,@Query("page") page:String): Call<ApiAttractions.GetAttractions.Response>
        /** 查詢最新消息  */
        @GET
        fun getNews(@Url url:String?,@Query("page") page:String): Call<ApiEvents.GetNews.Response>
    }
}