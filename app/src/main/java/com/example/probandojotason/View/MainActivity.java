package com.example.probandojotason.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.probandojotason.R;

public class MainActivity extends AppCompatActivity {
    private Button btnMostrar;
    private TextView tvMostrar;
    private MainViewModel mvm;
    private String datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvm.buscarVM("74");
            }
        });

    }

    private void inicializar(){
        btnMostrar=findViewById(R.id.btnMostrar);
        tvMostrar=findViewById(R.id.tvMostrar);
        mvm= ViewModelProviders.of(this).get(MainViewModel.class);
        mvm.getLista().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                if(datos!=null){
                    s=datos;
                }
                tvMostrar.setText(s);
            }
        });
    }

}
