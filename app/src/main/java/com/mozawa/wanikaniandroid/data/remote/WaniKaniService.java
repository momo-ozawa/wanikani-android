package com.mozawa.wanikaniandroid.data.remote;

import com.mozawa.wanikaniandroid.data.model.CriticalItemResponse;
import com.mozawa.wanikaniandroid.data.model.KanjiResponse;
import com.mozawa.wanikaniandroid.data.model.RadicalResponse;
import com.mozawa.wanikaniandroid.data.model.StudyQueueResponse;
import com.mozawa.wanikaniandroid.data.model.VocabularyReponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface WaniKaniService {

    String BASE_URL = "https://www.wanikani.com/api/user/";
    String API_KEY = "f33a8ea625b295955d96c12d4ed3fd7c/";
    // String API_KEY = "6984123dc766bdf49edfe44dfa653c6d/";

    @GET("study-queue")
    Observable<StudyQueueResponse> getStudyQueue();

    @GET("critical-items")
    Observable<CriticalItemResponse> getCriticalItems();

    @GET("radicals")
    Observable<RadicalResponse> getRadicals();

    @GET("kanji")
    Observable<KanjiResponse> getKanji();

    @GET("vocabulary")
    Observable<VocabularyReponse> getVocabulary();

    /******* Helper class that sets up a new service *******/
    class Creator {

        public static WaniKaniService newWaniKaniService() {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL + API_KEY)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            return retrofit.create(WaniKaniService.class);
        }
    }
}
