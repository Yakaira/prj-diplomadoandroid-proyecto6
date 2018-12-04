package com.example.yakarapina.calculadora_de_tiempos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Fecha, FechaNew, Hora, HoraNew;
   ImageView btnFecha, btnFechaNew, btnHora, btnHoraNew;
   private int dia, mes, ano, hora, minutos;
    private int diaNew, mesNew, anoNew, horaNew, minutosNew;
   TextView difTiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Aqui estamos enlazando nuestras contantes con el Id de nuestro diseño
        Fecha = (EditText)findViewById(R.id.eFecha);
        FechaNew = (EditText)findViewById(R.id.eFechaNew);
        Hora = (EditText)findViewById(R.id.eHora);
        HoraNew = (EditText)findViewById(R.id.eHoraNew);
        difTiempo = (TextView)findViewById(R.id.eTiempoTranscurrido);
        btnFecha  = (ImageView) findViewById(R.id.btn_fecha);
        btnFechaNew  = (ImageView) findViewById(R.id.btn_fecha_new);
        btnHora  = (ImageView) findViewById(R.id.btn_hora);
        btnHoraNew  = (ImageView) findViewById(R.id.btn_hora_new);

        //Esta funsion habilitar el OnClick en los botones
        btnFecha.setOnClickListener(this);
        btnFechaNew.setOnClickListener(this);
        btnHora.setOnClickListener(this);
        btnHoraNew.setOnClickListener(this);

        //Esta Funsion es para bloquear la entrada de Datos por el Teclado
        Fecha.setKeyListener(null);
        FechaNew.setKeyListener(null);
        Hora.setKeyListener(null);
        Hora.setKeyListener(null);
        HoraNew .setKeyListener(null);

    }

    //Esta es la funcion OnClick de los diferentes botones para llamar el DatePicker y el TimePicker
    @Override
    public void onClick(View v) {
        if (v==btnFecha){
            final Calendar b= Calendar.getInstance();
            ano=b.get(Calendar.YEAR);
             mes=b.get(Calendar.MONTH);
            dia=b.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    ano = year;
                    mes = month+1;
                    dia = dayOfMonth;

                    Fecha.setText(dayOfMonth+" / "+(month +1)+ " / " + year);

                }
            }
            ,ano, mes,dia);
            datePickerDialog.show();
        }

        if (v==btnHora){
            final  Calendar b= Calendar.getInstance();
            hora = b.get (Calendar.HOUR_OF_DAY);
            minutos = b.get (Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    hora = hourOfDay;
                    minutos = minute;
                    Hora.setText(hourOfDay+":"+minute);

                }
            }
            ,hora,minutos,false);
            timePickerDialog.show();
        }

        if (v==btnFechaNew){
            final Calendar b= Calendar.getInstance();
            anoNew=b.get(Calendar.YEAR);
             mesNew=b.get(Calendar.MONTH);
            diaNew=b.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        anoNew = year;
                        mesNew = month + 1;
                        diaNew = dayOfMonth;

                        //Con esta desicion  hacemos que la Segunda fecha introducida sea igual o mayor a la primera
                    if (ano <= anoNew) {

                        FechaNew.setText(dayOfMonth + " / " + (month + 1) + " / " + year);
                    }

                    else {

                        Toast toast = Toast.makeText (getApplicationContext (),
                                "Favor introducir una fecha igual o menor a la primera",
                                Toast.LENGTH_SHORT);

                        toast.show ();
                        return;
                    }

                }
            }
                , anoNew,mesNew,diaNew);
            datePickerDialog.show();

        }

        if (v==btnHoraNew){
            final  Calendar b= Calendar.getInstance();
            horaNew = b.get (Calendar.HOUR_OF_DAY);
            minutosNew = b.get (Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                   horaNew = hourOfDay;
                   minutosNew = minute;
                   HoraNew.setText(hourOfDay+":"+minute);
                }

            }
                    ,horaNew,minutosNew,false);
            timePickerDialog.show();
        }

    }


  //Metodo para el Boton que calculara la Diferencia de Fechas
public void Calcular (View view){

   int difAno = ano - anoNew;
   int difMes = mes - mesNew;
   int difDias = dia - diaNew;
   int difHoras = hora - horaNew;
   int difMinutos = minutos- minutosNew;

   //Muestra en pantalla el tiempo transcurido
 difTiempo.setText("("+difDias+") " + "Dias  " + "("+difMes+") " + "Mes/es  " + "("+difAno+") " + "Año/s  "  + "("+difHoras+") " +  "Hora/s  "  + "("+difMinutos+") " +  "Minutos");


}


}
