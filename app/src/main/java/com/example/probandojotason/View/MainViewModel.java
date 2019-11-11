package com.example.probandojotason.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.probandojotason.Model.Municipio;
import com.example.probandojotason.Model.Resultado;
import com.example.probandojotason.Request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    //Si necesito el conexto uso android view model
    private MutableLiveData<String> lista;

    //GET DE ESA VARIABLE
    public LiveData<String> getLista(){
        //garantizo que se cree una sola instancia de mutable
        if(lista==null){
            lista=new MutableLiveData<>();
        }
        return lista;
    }

    //Metodo del boton mostrar
    public void buscarVM(String prov){
        //Leer me devuelve un call back asincronico que va a buscar el resultado
        Call<Resultado> datos= ApiClient.getMyApiInterface().leer(prov);

        //Se hace la peticion pero nadie asegura que este el resultado, entonces necesito
        // decirle que me quedo esperando en segundo plano el resultado, que puede ser bueno o malo
        // para eso uso una cola con la nueva llamada paralelo al hilo principal
        datos.enqueue(new Callback<Resultado>() {
            @Override
            //Metodo que se ejecuta cunado devuelvan la llamada
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                //Buena practica
                if(response.isSuccessful()){
                    Resultado resultado= response.body();
                    //String especial
                    StringBuffer cadena = new StringBuffer();

                    //para cada municipio en el arrayList municipios de resultado muestro el nombre
                    for(Municipio it: resultado.getMunicipios()){
                        //Los agrego al string que estoy observando
                        //uso post ya que callback es un hilo
                        cadena.append(it.getNombre()+"\n");
                    }

                    lista.postValue(cadena.toString());
                }
            }

            @Override
            //Metodo que se ejecuta cuando se produce un error
            public void onFailure(Call<Resultado> call, Throwable t) {
                lista.postValue(t.getMessage());

            }
        });

    }

}
