package conraud.sylvain.mynews.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import conraud.sylvain.mynews.data.Root;
import retrofit2.Call;
import retrofit2.Response;

public class CallService {

    /*Interface callback*/
    public interface Callback{
        void onResponse(Root root, int id, Context context);
        void onFailure(Context context);
    }
    /*Call TopStories*/
    public static void callTopStories(Callback callback, String category, final int id, final Context context){
        final WeakReference<Callback> callbackWeakReference = new WeakReference<>(callback);
        Call<Root> call =  RetrofitManager.getInstance().callTopStories(category);
        call.enqueue(new retrofit2.Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                callbackWeakReference.get().onResponse(response.body(), id, context);
            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {
                callbackWeakReference.get().onFailure(context);
            }
        });
    }
    /*Call MostPopular*/
    public static void callMostPopular(Callback callback, final int id, final Context context){
        final WeakReference<Callback> callbackWeakReference = new WeakReference<>(callback);
        Call<Root> call =  RetrofitManager.getInstance().callMostPopular();
        call.enqueue(new retrofit2.Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                callbackWeakReference.get().onResponse(response.body(), id, context);
            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {
                callbackWeakReference.get().onFailure(context);
            }
        });

    }
    /*Call Search*/
    public static void callSearch(Callback callback, String search, String filter,String beginDate, String endDate, final int id, final Context context){
        final WeakReference<Callback> callbackWeakReference = new WeakReference<>(callback);
        Call<Root> call =  RetrofitManager.getInstance().callSearch(search, filter,beginDate,endDate);
        call.enqueue(new retrofit2.Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {

                callbackWeakReference.get().onResponse(response.body(), id, context);

            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {

                callbackWeakReference.get().onFailure(context);

            }
        });

    }

    /*Call Search*/
    public static void callNotification(final Callback callback, String search, String filter, String beginDate, String endDate, final int id, final Context context){
        //final WeakReference<Callback> callbackWeakReference = new WeakReference<>(callback);
        Call<Root> call =  RetrofitManager.getInstance().callSearch(search, filter,beginDate,endDate);
        call.enqueue(new retrofit2.Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {

              callback.onResponse(response.body(), id, context);

            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {

                callback.onFailure(context);

            }
        });

    }
}
