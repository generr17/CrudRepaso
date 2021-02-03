package com.example.crudrepaso;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Contacto> lista;
    daoContacto dao;
    Contacto c;
    Activity a;
int id=0;

    public void setId(int id) {
        this.id = id;
    }

    public Adaptador(Activity a, ArrayList<Contacto> lista, daoContacto dao) {
        this.lista = lista;
        this.a = a;
        this.dao = dao;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Contacto getItem(int position) {
        c = lista.get(position);
        return c;
    }

    @Override
    public long getItemId(int position) {
        c = lista.get(position);
        return c.getId();
    }

    public int getId() {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }
        c = lista.get(position);
        TextView nombre = (TextView) v.findViewById(R.id.t_nombre);
        TextView tel = (TextView) v.findViewById(R.id.t_telefono);
        TextView email = (TextView) v.findViewById(R.id.t_email);
        TextView edad = (TextView) v.findViewById(R.id.t_edad);
        Button editar = (Button) v.findViewById(R.id.editar);
        Button eliminar = (Button) v.findViewById(R.id.eliminar);
        nombre.setText(c.getNombre());
        tel.setText(c.getTelefono());
        email.setText(c.getEmail());
        edad.setText("" + c.getEdad());
        editar.setTag(position);
        eliminar.setTag(position);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText nombre = (EditText) dialog.findViewById(R.id.nombre);
                final EditText telefono = (EditText) dialog.findViewById(R.id.telefono);
                final EditText email = (EditText) dialog.findViewById(R.id.email);
                final EditText edad = (EditText) dialog.findViewById(R.id.edad);
                Button guardar = (Button) dialog.findViewById(R.id.d_agregar);
                Button cancelar = (Button) dialog.findViewById(R.id.d_cancelar);
                c = lista.get(pos);
                setId(c.getId());
                nombre.setText(c.getNombre());
                telefono.setText(c.getTelefono());
                email.setText(c.getEmail());
                edad.setText(""+c.getEdad());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            c = new Contacto(getId(),nombre.getText().toString(), telefono.getText().toString(),
                                    email.getText().toString(), Integer.parseInt(edad.getText().toString()));
                            dao.editar(c);
                            lista = dao.veratodos();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(a, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        });
        eliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());
                c= lista.get(pos);
                setId(c.getId());
                AlertDialog.Builder del=new AlertDialog.Builder(a);
                del.setMessage("Seguro que desea eliminar el contacto?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.eliminar(getId());
                        lista=dao.veratodos();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
               del.show();
            }
        });
        return v;
    }
}
