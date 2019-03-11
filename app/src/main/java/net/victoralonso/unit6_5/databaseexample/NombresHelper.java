package net.victoralonso.unit6_5.databaseexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class NombresHelper extends SQLiteOpenHelper {
	private Context contexto = null;
	public static final String NOMBRE_BBDD = "bdnombres";
	public static final int VERSION_BBDD = 1;
	
	public NombresHelper(Context context) {
		super(context, NOMBRE_BBDD, null, VERSION_BBDD);
		this.contexto = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase bbdd) {
		String sentencia = null;
		InputStream flujoEntrada = contexto.getResources().openRawResource(R.raw.bdnombres);
		InputStreamReader lectorFlujoEntrada = new InputStreamReader(flujoEntrada);
		BufferedReader archivoLeido = new BufferedReader(lectorFlujoEntrada);

		try {
			sentencia = archivoLeido.readLine();
			while (sentencia != null){
				bbdd.execSQL(sentencia);
				sentencia = archivoLeido.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			Toast.makeText(contexto, "Ha sido imposible leer el archivo de creación, consultar el log", Toast.LENGTH_LONG).show();
			Log.wtf("-- WARNING-BBDD --", "Error en NombresHelper", ioe);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Apéndice de método generado automáticamente

	}
	
}
