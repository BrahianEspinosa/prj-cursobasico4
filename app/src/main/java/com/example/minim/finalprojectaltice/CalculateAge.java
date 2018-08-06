package com.example.minim.finalprojectaltice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class CalculateAge extends AppCompatActivity {

    private TextView name1,lastName1,age1,name2,lastName2,age2,finalText;
    private String nameUser1;
    private String nameUser2;
    private int dayUser1,monthUser1,yearUser1,ageUser1,dayUser2,monthUser2,yearUser2,ageUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_edades);

        name1 = findViewById(R.id.nameUser1);
        lastName1 = findViewById(R.id.lastNameUser1);
        age1 = findViewById(R.id.ageUser1);

        name2 = findViewById(R.id.nameUser2);
        lastName2 = findViewById(R.id.lastNameUser2);
        age2 = findViewById(R.id.ageUser2);

        finalText= findViewById(R.id.final_text);


        setUserAndDisplayData();
        calculateCompleteAge();

    }



    private void setUserAndDisplayData(){
        //Setting data for User 1
        ImageView imageViewUser1 = findViewById(R.id.ImageViewMainUser1);
        ImageView imageViewUser2 = findViewById(R.id.ImageViewMainUser2);
        Users usuarioUno = new Users();
        Users usuarioDos = new Users();
        Intent getUserData = getIntent();
        String dia1 = getUserData.getStringExtra("DAY1");
        String mes1 = getUserData.getStringExtra("MONTH1");
        String ano1 = getUserData.getStringExtra("YEAR1");


        nameUser1 = getUserData.getStringExtra("NAME1");
        String lastNameUser1 = getUserData.getStringExtra("LAST_NAME1");
        String genderUser1 = getUserData.getStringExtra("GENDER1");
        dayUser1 = Integer.parseInt(dia1);
        monthUser1 = Integer.parseInt(mes1);
        yearUser1 = Integer.parseInt(ano1);

        //Setting data in the TextView
        usuarioUno.setFechaNacimiento(dayUser1,monthUser1,yearUser1);
        ageUser1 = usuarioUno.getAge();
        String edad1 = String.valueOf(ageUser1);
        name1.setText(nameUser1);
        lastName1.setText(lastNameUser1);
        age1.setText(edad1);


        //Setting data from User 2
        String dia2 = getUserData.getStringExtra("DAY2");
        String mes2 = getUserData.getStringExtra("MONTH2");
        String ano2 = getUserData.getStringExtra("YEAR2");


        nameUser2 = getUserData.getStringExtra("NAME2");
        String lastNameUser2 = getUserData.getStringExtra("LAST_NAME2");
        String genderUser2 = getUserData.getStringExtra("GENDER2");
        dayUser2 = Integer.parseInt(dia2);
        monthUser2 = Integer.parseInt(mes2);
        yearUser2 = Integer.parseInt(ano2);

        //Setting data in the TextView
        usuarioDos.setFechaNacimiento(dayUser2,monthUser2,yearUser2);
        ageUser2 = usuarioDos.getAge();
        String edad2 = String.valueOf(ageUser2);
        name2.setText(nameUser2);
        lastName2.setText(lastNameUser2);
        age2.setText(edad2);

        if(genderUser1.equalsIgnoreCase("Male")){
            imageViewUser1.setImageResource(R.drawable.man);
        }else if (genderUser1.equalsIgnoreCase("Female")){
            imageViewUser1.setImageResource(R.drawable.girl);
        }else{
            imageViewUser1.setImageResource(R.drawable.ico);
        }

        if(genderUser2.equalsIgnoreCase("Male")){
            imageViewUser2.setImageResource(R.drawable.man);
        }else if (genderUser2.equalsIgnoreCase("Female")){
            imageViewUser2.setImageResource(R.drawable.girl);
        }else{
            imageViewUser2.setImageResource(R.drawable.ico);
        }


    }

    private void calculateCompleteAge(){
        int ArregloMeses[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        int diferenciaEdad;
        int diferenciaMeses;
        int diferenciaDias;
        int meses=12;
        String message = " ";



        //Calculando diferencia de fechas y años
        if(yearUser1 < yearUser2){
            diferenciaEdad = yearUser2 - yearUser1;
            //Calcular diferencia de meses
            if(monthUser1 > monthUser2){
                diferenciaMeses= (meses + monthUser2) - monthUser1;
                diferenciaEdad-=1;
            }else diferenciaMeses = monthUser2 - monthUser1;

        }else if(yearUser2 < yearUser1){
            diferenciaEdad = yearUser1 - yearUser2;
            //Calcular diferencia de meses
            if(monthUser2 > monthUser1){
                diferenciaMeses= (meses + monthUser1) - monthUser2;
                diferenciaEdad-=1;
            }else diferenciaMeses = monthUser1 - monthUser2;
        }else{
            diferenciaEdad=0;
            if(monthUser2 > monthUser1)diferenciaMeses = monthUser2 - monthUser1;
            else diferenciaMeses = monthUser1 - monthUser2;
        }





        //Calculando aproximado de dias
        if(dayUser1 > dayUser2){
            int dias = (ArregloMeses[monthUser1-1] - dayUser1) + (ArregloMeses[monthUser2-1] - dayUser2);
            if(dias < 31){
                if(diferenciaMeses <= 0)diferenciaMeses = 12;
                diferenciaMeses-=1;
                diferenciaDias = dias;
            }else diferenciaDias = dayUser1 - dayUser2;

        }else if(dayUser2 > dayUser1){
            int dias = (ArregloMeses[monthUser1-1] - dayUser1) + (ArregloMeses[monthUser2-1] - dayUser2);
            if(dias < 31){
                if(diferenciaMeses <= 0)diferenciaMeses = 12;
                diferenciaMeses-=1;
                diferenciaDias = dias;
            }else diferenciaDias = dayUser2 - dayUser1;
        }else{
            diferenciaDias=0;
        }


        //Mensaje
        if(ageUser1 > ageUser2){
            message+=nameUser1+" es mayor que "+nameUser2+" por "+diferenciaEdad + " años \n" +  "y " + diferenciaMeses +" meses\n"
                    + " y un aproximado de "+diferenciaDias+" dias";
        }else if(ageUser2 > ageUser1){
            message+=nameUser2+" es mayor que "+nameUser1+" por "+ diferenciaEdad + " años \n" +  "y " + diferenciaMeses +" meses\n"
                    + " y un aproximado de "+diferenciaDias+" dias";
        }else{
            message+="Tienen "+diferenciaMeses+" meses de diferencia\n"
                    + " y un aproximado de "+diferenciaDias+" dias";
        }
        finalText.setText(message);

    }



}
