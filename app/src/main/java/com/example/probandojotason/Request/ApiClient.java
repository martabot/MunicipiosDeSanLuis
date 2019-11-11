package com.example.probandojotason.Request;

import android.util.Log;

import com.example.probandojotason.Model.Resultado;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiClient {

    //URL base donde estan los servicios que vamos a consumir
    private static final String PATH="https://apis.datos.gob.ar/georef/api/";

    //Una interfaz que tiene los metrodos que va a implementar retrofit (El controlador)
    private static MyApiInterface myApiInterface;

    // Con estos datos crea automaticamente la conexion
    public static MyApiInterface getMyApiInterface(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Log.d("mensaje",);
        //Al metodo create le paso el nombre de la clase que va a implementar y lo seteo a la interfaz
        myApiInterface= retrofit.create(MyApiInterface.class);
        //Y lo devuelvo
        return myApiInterface;
    }


    //Creamos la interfaz interna para obligar el comportamiento
    public interface MyApiInterface{

        //con @GET @POST @PUT voy creando las llamadas al servicio
        //no lleva ; ya que no es nativo de java
        @GET("municipios")
        //Uso la palabra call para decirle como le llamara el metodo en java que ulitice el servicio
        //Crea una instacia de conexion asincronica para la peticion de metodo leer
        Call<Resultado> leer(@Query("provincia") String prov);

    }

}
