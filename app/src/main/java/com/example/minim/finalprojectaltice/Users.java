package com.example.minim.finalprojectaltice;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Users {

     private Calendar calendario = new GregorianCalendar();
     private String nombre;
     private String apellido;
     private String genero;
     private int diaNacimiento;
     private int mesNacimiento;
     private int anoNacimiento;
     private int edad;
     private final int actualDay = calendario.get(Calendar.DAY_OF_MONTH);
     private final int actualMonth = calendario.get(Calendar.MONTH)+1;
     private final int actualYear =  calendario.get(Calendar.YEAR);

     public Users(){}

     public void setNombre(String nombre,String apellido){
          this.nombre = nombre;
          this.apellido = apellido;
     }
     public void setFechaNacimiento(int day,int month,int year){
          diaNacimiento = day;
          mesNacimiento = month;
          anoNacimiento = year;

     }
     public int getAge(){
          if(mesNacimiento > actualMonth){
               edad = (actualYear - anoNacimiento) - 1;
          }else if(mesNacimiento == actualMonth && diaNacimiento > actualDay){
               edad = (actualYear - anoNacimiento) - 1;
          }else{
               edad = actualYear - anoNacimiento;
          }
         return edad;
     }
     public String getNombre(){
          return nombre;
     }
     public String getApellido(){
          return apellido;
     }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
