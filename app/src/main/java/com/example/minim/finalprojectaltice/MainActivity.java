package com.example.minim.finalprojectaltice;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner mySpinner;
    private EditText Name,LastName,dateText;
    private int day,month,year;
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySpinner =  findViewById(R.id.Gender);
        Name = findViewById(R.id.txtName);
        LastName = findViewById(R.id.txtLastName);
        dateText = findViewById(R.id.getFecha);


       startSpinner();

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setFecha(View view) {
        final Calendar calendar = Calendar.getInstance();
        final int actualDay = calendar.get(Calendar.DAY_OF_MONTH);
        final int actualMonth = calendar.get(Calendar.MONTH);
        final int actualYear = calendar.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {

                String fecha=dia + "/" + (mes + 1) + "/" + ano;
                dateText.setText(fecha);
                MainActivity.this.day=dia;
                MainActivity.this.year=ano;
                MainActivity.this.month=mes+1;
                if (actualYear > ano) {
                    check = true;
                }else if(actualYear == ano && actualMonth > mes){
                    check = true;
                }else if(actualYear == ano && actualMonth == mes && dia <= actualDay){
                    check = true;
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Ingrese una fecha del presente pa' tra'", Toast.LENGTH_LONG).show();
                }
            }
        }, day, month, year);

        datePickerDialog.show();



    }

    //Start and events for the spinner
    public void startSpinner(){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Generos,
                android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(this);
    }


    public void saveButton(View v){
        Intent intentNext = new Intent(this,SecondUser.class);
        String message = "Verifique que los datos sean correctos y  esten completos";
        String nombre = Name.getText().toString();
        String apellido = LastName.getText().toString();
        String gender = mySpinner.getSelectedItem().toString();
        String dia =  String.valueOf(day);
        String mes = String.valueOf(month);
        String ano = String.valueOf(year);



        //Sending User1 data to next activity
        intentNext.putExtra("NAME", nombre);
        intentNext.putExtra("LAST_NAME",apellido);
        intentNext.putExtra("GENDER",gender);
        intentNext.putExtra("DAY",dia);
        intentNext.putExtra("MONTH",mes);
        intentNext.putExtra("YEAR",ano);

        if(check && !nombre.isEmpty() && !apellido.isEmpty()) startActivity(intentNext);
        else Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    //ItemOnSelected method for the spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ImageView imageView = findViewById(R.id.ImageViewMain);
        String item = adapterView.getItemAtPosition(i).toString();
        if(item.equalsIgnoreCase("male")){
            imageView.setImageResource(R.drawable.man);
        }else if(item.equalsIgnoreCase("female")){
            imageView.setImageResource(R.drawable.girl);
        }else{
            imageView.setImageResource(R.drawable.ico);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

