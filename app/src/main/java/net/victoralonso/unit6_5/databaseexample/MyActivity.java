package net.victoralonso.unit6_5.databaseexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MyActivity extends Activity {
    private TextView significadoMostrado = null;
    private TextView origenMostrado = null;
    private NombresAdapter adaptadorSQL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        String [] nombres = null;
        AutoCompleteTextView texto = null;
        ArrayAdapter<String> adaptador = null;

        adaptadorSQL = new NombresAdapter(this);
        nombres = adaptadorSQL.todosNombres();

        // Capturando elementos
        significadoMostrado = (TextView) findViewById(R.id.significado);
        origenMostrado = (TextView) findViewById(R.id.origen);
        texto = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

        // Añadir adaptador y Listener al AutoCompleteTextView
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, nombres);
        texto.setAdapter(adaptador);

        texto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
			/*
			 * parent	 	The AdapterView where the selection happened
			 * view 		The view within the AdapterView that was clicked
			 * position 	The position of the view in the adapter
			 * id 			The row id of the item that is selected
			 */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreSeleccionado = parent.getItemAtPosition(position).toString(),
                        resultadoBusqueda[] = new String[2];

                resultadoBusqueda = adaptadorSQL.busquedaNombre(nombreSeleccionado);

                significadoMostrado.setText(resultadoBusqueda[0]);
                origenMostrado.setText(resultadoBusqueda[1]);

            }

        });

    }

}
