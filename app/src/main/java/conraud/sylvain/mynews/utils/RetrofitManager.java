package conraud.sylvain.mynews.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    class RetrofitManager {

    /*private constructor*/
    private  RetrofitManager(){}

    private static NewYorkTimesService instance = null;

    static NewYorkTimesService getInstance(){
        if(instance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.nytimes.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance = retrofit.create(NewYorkTimesService.class);
        }
        return instance;
    }
}
