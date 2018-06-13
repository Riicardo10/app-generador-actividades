package com.example.ricardoflores.app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class ListaActividades extends AppCompatActivity {

    WebView txt_html;
    Button btt_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_actividades);
        final HelperDB helper = new HelperDB(this);

        txt_html = (WebView)findViewById(R.id.web_view);
        WebSettings ws = txt_html.getSettings();
        ws.setJavaScriptEnabled(true);

        cargarListaActividades(helper);

        // BOTON DE SALIR
        btt_salir = (Button)findViewById(R.id.btt_salir_LIS_ACT);
        btt_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(), MenuUsuario.class) );
                finish();
            }
        });
    }

    public void cargarListaActividades(HelperDB helper){
        SQLiteDatabase db = helper.getReadableDatabase();
        if(db != null){
            Cursor c = db.rawQuery("SELECT * FROM actividades GROUP BY nombre", null);
            String resultado = "";
            if(c.moveToFirst()){
                do {
                    String name = c.getString(0);
                    String moment = c.getString(1);
                    Cursor c2 = db.rawQuery("SELECT * FROM actividades WHERE nombre = '" + name + "'", null);
                    if(c2.moveToFirst()){
                        resultado += "<h3 style='color:#137;text-align:center'>" + "Actividad: " + name + "</h3>";
                        resultado += "<h4 style='color:red'>" + moment + "</h4>";
                        resultado += "<b>" + "Tags:" + "</b>";
                        resultado += "<ul>";
                        do{
                            resultado += "<li>" + c2.getString(2) + "</li>";
                        }while (c2.moveToNext());
                        resultado += "</ul>";
                        resultado += "<hr><hr><hr>";
                    }


                } while (c.moveToNext());
            }
            txt_html.loadDataWithBaseURL(null, resultado, "text/html", "UTF-8", null);
        }
    }
}
