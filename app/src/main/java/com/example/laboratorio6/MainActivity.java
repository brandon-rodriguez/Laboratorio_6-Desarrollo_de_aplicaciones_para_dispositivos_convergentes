package com.example.laboratorio6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private TextInputEditText usuario, contraseña;
    private Button login, registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Login");
        usuario = (TextInputEditText) findViewById(R.id.log_usuario);
        contraseña= (TextInputEditText) findViewById(R.id.log_contraseña);
        login = (Button) findViewById(R.id.log_ingreso);
        registro = (Button) findViewById(R.id.log_registro);
        db = new DatabaseHelper(this);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaActividad = new Intent(MainActivity.this, Register.class);
                startActivity(nuevaActividad);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioS = usuario.getText().toString();
                String contraseñaS = contraseña.getText().toString();
                Cursor resultado = db.findDataByUser(usuarioS);
                if(resultado.getCount()==0){
                    presentarMensaje("Error", "Sin datos");
                    return;
                }
                String validacion ="Contraseña incorrecta";
                while (resultado.moveToNext()){
                    if(resultado.getString(4).equals(contraseñaS)){
                        validacion = "Ingreso exitoso";
                    }
                }
                presentarMensaje("Ingreso", validacion);
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