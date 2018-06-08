package com.example.ricardoflores.app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarTag extends AppCompatActivity {

    String id, serial, tag;
    EditText txt_id, txt_serial, txt_tag;
    Button btt_actualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_tag);
        final HelperDB helper = new HelperDB(this);

        id = getIntent().getStringExtra("id");
        serial = getIntent().getStringExtra("serial");
        tag = getIntent().getStringExtra("tag");

        txt_id = (EditText)findViewById(R.id.txt_id_ACT_TAG);
        txt_serial = (EditText)findViewById(R.id.txt_serial_ACT_TAG);
        txt_tag = (EditText)findViewById(R.id.txt_tag_ACT_TAG);

        txt_id.setText( id );
        txt_serial.setText( serial );
        txt_tag.setText( tag );

        btt_actualizar = (Button)findViewById(R.id.btt_actualizar_ACT_TAG);
        btt_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();
                if(db != null) {
                    ContentValues valores = new ContentValues();
                    valores.put("serial", txt_serial.getText().toString());
                    valores.put("nombre_objeto", txt_tag.getText().toString());
                    db.update("tags", valores, "id='" + id + "'", null);
                    Toast.makeText(ActualizarTag.this, "Registro actualizado.", Toast.LENGTH_LONG).show();
                    startActivity( new Intent(getApplicationContext(), ListaTags.class) );
                    finish();
                }
            }
        });


    }
}
