package dita.shafira.mate.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static final String BASE_URL="http://192.168.1.2/catemate/public/api/";
    private static Service instance;
    private Retrofit retrofit;

    public Service () {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized Service getInstance(){
        if (instance==null){
            instance=new Service();
        }
        return instance;
    }

    public ApiService getApi(){return retrofit.create(ApiService.class);}

}
