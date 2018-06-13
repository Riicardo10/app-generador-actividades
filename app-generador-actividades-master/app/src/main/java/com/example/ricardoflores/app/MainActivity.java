package com.example.ricardoflores.app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btt_iniciar;
    EditText txt_email, txt_contrasenia;
    TextView btt_crear_cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_email = (EditText)findViewById(R.id.txt_email_MAIN);
        txt_contrasenia = (EditText)findViewById(R.id.txt_contrasenia_MAIN);
        btt_iniciar = (Button)findViewById(R.id.btt_iniciar_MAIN);
        btt_crear_cuenta = (TextView) findViewById(R.id.btt_crear_cuenta_MAIN);

        final HelperDB helper = new HelperDB(this);

        // INICIAR
        btt_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txt_email.getText().toString();
                String contrasenia = txt_contrasenia.getText().toString();
                if(camposLlenos(new String[]{email, contrasenia})){
                    SQLiteDatabase db = helper.getReadableDatabase();
                    if(db != null){
                        String[] args = new String[] {email, contrasenia};
                        Cursor c = db.rawQuery(" SELECT * FROM docentes WHERE email=? AND contrasenia=?", args);
                        if(c.getCount() == 1){
                            Intent intent = new Intent(MainActivity.this, MenuPricipal.class);
                            if(c.moveToFirst()){
                                do {
                                    intent.putExtra("id", c.getString(0));
                                    intent.putExtra("nombre", c.getString(1));
                                    intent.putExtra("apellidos", c.getString(2));
                                    intent.putExtra("email", c.getString(3));
                                    intent.putExtra("cct", c.getString(5));
                                    intent.putExtra("escuela", c.getString(6));
                                    intent.putExtra("grado", c.getString(7));
                                    intent.putExtra("grupo", c.getString(8));
                                } while (c.moveToNext());
                            }
                            startActivity( intent );
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Verifica datos.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Rellena los campos solicitados.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // DOCENTES
        btt_crear_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(), RegistroDocente.class) );
            }
        });
    }
    public boolean camposLlenos( String arreglo[] ){
        for (int i = 0; i < arreglo.length; i++){
            if(arreglo[i].toString().equals(""))
                return false;
        }
        return true;
    }
}
