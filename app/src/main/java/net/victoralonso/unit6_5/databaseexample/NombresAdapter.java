package net.victoralonso.unit6_5.databaseexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NombresAdapter {

	// Gestión de bbdd (creación, actualización, obtención o cerrado)
	private NombresHelper helper = null;
	
	// Base de datos propiamente dicha
	private SQLiteDatabase bbdd = null;

	// Cursor para la consulta en la bbdd
	private Cursor cursor = null;
	 
	private Context contexto = null;

	public NombresAdapter(Context contexto) {
		this.contexto = contexto;
	}

	private void abrir() {
		helper = new NombresHelper(contexto);
		bbdd = helper.getReadableDatabase();
	}

	private void cerrar() {
		if (cursor != null) {
			cursor.close();
		}
		if (bbdd != null) {
			bbdd.close();
		}
		if (helper != null) {
			helper.close();
		}
	}

	public String[] busquedaNombre (String argumentos) {
		String[] nombre = new String [2], args = {argumentos} ;
		
		abrir();
		cursor = bbdd.rawQuery("SELECT descripcion, origen FROM nombres WHERE nombre = ? ", args);
		
		if (cursor != null) {
			cursor.moveToFirst();
			nombre[0] = cursor.getString(0);
			nombre[1] = cursor.getString(1);
			
		}
		cerrar();
		
		return nombre;
		
	}

	public String[] todosNombres () {
		String [] nombres = null;
		int cont = 0;
		
		abrir();
		cursor = bbdd.query("nombres", new String[] {"nombre"}, null, null, null, null, null, null);
		
		if (cursor.moveToFirst()) {
			nombres = new String [ cursor.getCount() ];
			do {
				nombres[cont] = cursor.getString(0);
				cont ++;
			} while (cursor.moveToNext());
		}
		cerrar();
		return nombres;
		
	}
	
}
