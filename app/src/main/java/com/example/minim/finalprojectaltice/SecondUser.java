package com.example.minim.finalprojectaltice;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SecondUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private int day, month, year;
    private TextView name,lastName;
    private Spinner mySpinner;
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_user);

        name = findViewById(R.id.txtName1);
        lastName = findViewById(R.id.txtLastName1);
        mySpinner =  findViewById(R.id.Gender);

        startSpinner();
    }




    public void setFecha(View view) {
        final Calendar calendar = Calendar.getInstance();
        final int actualDay = calendar.get(Calendar.DAY_OF_MONTH);
        final int actualMonth = calendar.get(Calendar.MONTH);
        final int actualYear = calendar.get(Calendar.YEAR);
        final EditText fillDate = findViewById(R.id.getFechaId);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                String fecha =dia + "/" + (mes + 1) + "/" + ano;
                fillDate.setText(fecha);
                SecondUser.this.day = dia;
                SecondUser.this.year = ano;
                SecondUser.this.month = mes + 1;

                if (actualYear > ano) {
                    check = true;
                }else if(actualYear == ano && actualMonth > mes){
                    check = true;
                }else if(actualYear == ano && actualMonth == mes && dia <= actualDay){
                    check = true;
                }
                else {
                    Toast.makeText(SecondUser.this, "Ingrese una fecha del presente pa' tra'", Toast.LENGTH_LONG).show();
                }
            }
        }, day, month, year);
        datePickerDialog.show();
    }


    public void startSpinner() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Generos,
                android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ImageView imageView = findViewById(R.id.ImageViewMain);
        String item = adapterView.getItemAtPosition(i).toString();
        if(item.equalsIgnoreCase("Male")){
            imageView.setImageResource(R.drawable.man);
        }else if(item.equalsIgnoreCase("Female")){
            imageView.setImageResource(R.drawable.girl);
        }else{
            imageView.setImageResource(R.drawable.ico);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void saveAndSend(View v) {
        Intent intentNext = new Intent(this, CalculateAge.class);
        Intent intentBefore = getIntent();
        String message = "Verifique que los datos sean correctos y  esten completos";

        //Data from User 2
        String nombreUser2 = name.getText().toString();
        String apellidoUser2 = lastName.getText().toString();
        String genderUser2 = mySpinner.getSelectedItem().toString();
        String diaUser2 =  String.valueOf(day);
        String mesUser2 = String.valueOf(month);
        String anoUser2 = String.valueOf(year);

        //Getting data from User1
        String nombreUser1 = intentBefore.getStringExtra("NAME");
        String apellidoUser1 = intentBefore.getStringExtra("LAST_NAME");
        String diaUser1 = intentBefore.getStringExtra("DAY");
        String mesUser1 = intentBefore.getStringExtra("MONTH");
        String anoUser1 = intentBefore.getStringExtra("YEAR");
        String genderUser1 = intentBefore.getStringExtra("GENDER");




        //Sending User1 data
        intentNext.putExtra("NAME1", nombreUser1);
        intentNext.putExtra("LAST_NAME1",apellidoUser1);
        intentNext.putExtra("GENDER1",genderUser1);
        intentNext.putExtra("DAY1",diaUser1);
        intentNext.putExtra("MONTH1",mesUser1);
        intentNext.putExtra("YEAR1",anoUser1);

        //Sending User2 data
        intentNext.putExtra("NAME2",nombreUser2);
        intentNext.putExtra("LAST_NAME2",apellidoUser2);
        intentNext.putExtra("GENDER2",genderUser2);
        intentNext.putExtra("DAY2",diaUser2);
        intentNext.putExtra("MONTH2", mesUser2);
        intentNext.putExtra("YEAR2",anoUser2);




        if (check && !nombreUser2.isEmpty() && !apellidoUser2.isEmpty()) startActivity(intentNext);
        else Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}
