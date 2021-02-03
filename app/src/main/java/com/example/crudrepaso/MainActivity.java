 package com.example.crudrepaso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {

    daoContacto dao;
    Adaptador adaptador;
    ArrayList<Contacto> lista;
    Contacto c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao= new daoContacto(this);
        lista=dao.veratodos();
        adaptador= new Adaptador(this, lista, dao);
        ListView list=(ListView)findViewById(R.id.lista);
        Button agregar=(Button)findViewById(R.id.agregar);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog= new Dialog(MainActivity.this);
                dialog.setTitle("Nuevo registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
              final   EditText nombre= (EditText)dialog.findViewById(R.id.nombre);
               final EditText telefono= (EditText)dialog.findViewById(R.id.telefono);
                final EditText email= (EditText)dialog.findViewById(R.id.email);
                final EditText edad= (EditText)dialog.findViewById(R.id.edad);
                Button guardar= (Button)dialog.findViewById(R.id.d_agregar);
                Button cancelar= (Button)dialog.findViewById(R.id.d_cancelar);
                 guardar.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                            try {
                                c= new Contacto(nombre.getText().toString(), telefono.getText().toString(),
                                        email.getText().toString(), Integer.parseInt(edad.getText().toString()));
                             dao.insetar(c);
                             lista=dao.veratodos();
                             adaptador.notifyDataSetChanged();
                             dialog.dismiss();
                            }catch (Exception e){
                                Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
                            }
                     }
                 });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            dialog.dismiss();

                    }
                });

            }
        });
    }
}