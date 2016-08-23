package com.mozawa.wanikaniandroid.data.remote;

import com.mozawa.wanikaniandroid.data.model.CriticalItems;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.data.model.Radicals;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.data.model.Vocabulary;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

public interface WaniKaniService {

    String BASE_URL = "https://www.wanikani.com/api/user/";
    String API_KEY = "6984123dc766bdf49edfe44dfa653c6d/";

    @GET("study-queue")
    Observable<StudyQueue> getStudyQueue();

    @GET("critical-items")
    Observable<CriticalItems> getCriticalItems();

    @GET("radicals")
    Observable<Radicals> getRadicals();

    @GET("kanji")
    Observable<Kanji> getKanji();

    @GET("vocabulary")
    Observable<Vocabulary> getVocabulary();

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
