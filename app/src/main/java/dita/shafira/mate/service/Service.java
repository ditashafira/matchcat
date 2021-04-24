package dita.shafira.mate.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    public static final String BASE_URL_STORAGE="http://catmate.imajisociopreneur.id/storage/";
    public static final String BASE_URL="http://catmate.imajisociopreneur.id/api/";
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
