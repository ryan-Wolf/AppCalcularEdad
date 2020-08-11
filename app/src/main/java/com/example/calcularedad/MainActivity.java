package com.example.calcularedad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtFecha_nac, txtFecha_act, txtedad;
    Button btnok;

    Date fechaact = new Date();
    int aa = 0;
    int ma = 0;
    int anio = 0, mes = 0, dia = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFecha_nac = findViewById(R.id.fecha_nac);
        txtFecha_act = findViewById(R.id.fecha_act);
        txtedad = findViewById(R.id.txtEdad);

        btnok = findViewById(R.id.btnok);
        btnok.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnok) {
            Calendar ca = Calendar.getInstance();
            anio = ca.get(Calendar.YEAR);
            mes = ca.get(Calendar.MONTH);
            int dia = ca.get(Calendar.DAY_OF_MONTH);
            //txtFecha_act.setText("Fecha actual: " + dia + "/" + (mes + 1) + "/" + anio);
            DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int anioo, int mesi, int diaa) {
                    final int mesActual = mesi + 1;
                    String diaFormateado = (diaa < 10) ? "0" + String.valueOf(diaa) : String.valueOf(diaa);
                    String mesFormateado = (mesActual < 10) ? "0" + String.valueOf(mesActual) :
                            String.valueOf(mesActual);
                    txtFecha_nac.setText("Fecha nacimiento: " + diaFormateado + "/" + mesFormateado + "/" + anioo);
                    aa = anioo;
                    ma = Integer.parseInt(mesFormateado);
                    txtedad.setText(calcular(anio,(mes+1), aa, ma));
                }
            }, anio, mes, dia);
            recogerFecha.show();
        }
    }

    public String calcular(int a, int m, int aa, int ma) {
        int años = 0;
        int meses = 0;
        if (ma <= m) {
            años = a - aa;
            meses = m - ma;
        } else {
            años = a - aa - 1;
            meses = 12 - (ma - m);
        }
        return "Edad: " + años + "años" + meses + "meses";
    }
}