package com.example.laboratorio6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    private DatabaseHelper db;
    private TextInputLayout nombreCompleto, usuario, correo, contraseña, verificarContraseña;
    private RadioGroup rg;
    private Button registro, verTodos;
    private int genero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Login");
        db = new DatabaseHelper(Register.this);
        registro = (Button) findViewById(R.id.btn_registrar);
        verTodos = (Button) findViewById(R.id.btn_verTodos);

        nombreCompleto = (TextInputLayout) findViewById(R.id.ti_nombre) ;
        usuario= (TextInputLayout) findViewById(R.id.ti_usuario) ;
        correo= (TextInputLayout) findViewById(R.id.ti_correo) ;
        contraseña= (TextInputLayout) findViewById(R.id.ti_contraseña) ;
        verificarContraseña= (TextInputLayout) findViewById(R.id.ti_confirmar_contraseña) ;

        verTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor resultado = db.getAllData();
                if(resultado.getCount()==0){
                    presentarMensaje("Error", "Sin datos");
                    return;
                }
                StringBuffer sb= new StringBuffer();
                while (resultado.moveToNext()){
                    sb.append("ID: "+ resultado.getString(0)+ "\n");
                    sb.append("Nombre: "+ resultado.getString(1)+ "\n");
                    sb.append("Usuario: "+ resultado.getString(2)+ "\n");
                    sb.append("Correo: "+ resultado.getString(3)+ "\n");
                    sb.append("Contraseña: "+ resultado.getString(4)+ "\n");
                    sb.append("Genero: "+ resultado.getString(5)+ "\n");
                }
                presentarMensaje("Datos", sb.toString());
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contraseña.getEditText().getText().toString().equals(verificarContraseña.getEditText().getText().toString())){
                    String n =nombreCompleto.getEditText().getText().toString();
                    String c=correo.getEditText().getText().toString();
                    String us= usuario.getEditText().getText().toString();
                    String cn=contraseña.getEditText().getText().toString();
                    rg = (RadioGroup) findViewById(R.id.rad_group);

                    int checkedRadioButtonId = rg.getCheckedRadioButtonId();
                    if (checkedRadioButtonId == -1) {
                        Toast.makeText(Register.this, "Selecciona un genero", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        int genero =0;
                        if (checkedRadioButtonId == R.id.rdb_masculino) {
                           genero=1;
                        }
                        Usuario u = new Usuario(n,  c,  us,  cn,  genero);
                        boolean resultado= db.insertData(u);
                        if(!resultado){
                            Toast.makeText(Register.this, "Error al insertar en la base de datos", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Register.this, "Insercion exitosa", Toast.LENGTH_SHORT).show();
                        }
                    }


                }else{
                    Toast.makeText(Register.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void presentarMensaje(String titulo, String informacion){
        AlertDialog.Builder b= new AlertDialog.Builder(this);
        b.setCancelable(true);
        b.setTitle(titulo);
        b.setMessage(informacion);
        b.show();
    }
}