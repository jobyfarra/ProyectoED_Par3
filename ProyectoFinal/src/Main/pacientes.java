/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



/**
 *
 * @author User
 */
public class pacientes {
         private Map <String,ArrayList<Integer>> dataset = new LinkedHashMap<String,ArrayList<Integer>>();
         private Map <String,Double> Gini = new LinkedHashMap <String,Double> ();
         
         public void  CargarDatos(String nombreA) {
             try {
                 BufferedReader  br =new BufferedReader(new FileReader(nombreA));
                 String encabezado= br.readLine();//se captura el headline de los atributos
                 String[] atributos=encabezado.split(",");
                 for (String x: atributos){
                    dataset.put( x ,new ArrayList <Integer>());
                    Gini.put(x, 0.0);
                 }
                String linea = br.readLine();
                 while(linea!=null){//se lee las 299 entradas del archivo
                     String[] datos =linea.split(",");
                     for(int j=0;j<datos.length;j++){
                        dataset.get(atributos[j]).add(Integer.parseInt(datos[j]));//se cargan las ArrayLists con los valores de las entradas
                     }
                    linea=br.readLine();
                 }
                 
             } catch (Exception ex) {
                 
             }
         
         
         
         }
  
    public static void main (String [] args){
         pacientes pc=new pacientes();
         //Se debe cambiar el path del archivo, ya que se encuentra en otra carpeta
         pc.CargarDatos("D:\\Trabajos\\ESPOL\\Estructuras\\ProyectoFinal\\ProyectoED_Par3\\ProyectoFinal\\src\\Files\\pacientes1.csv");
         System.out.println("Diccionario DataSet");
         for (Map.Entry entry : pc.dataset.entrySet()) {
            System.out.println("Clave : " + entry.getKey()
                    +" ->"+ " Valor : " + entry.getValue()+"->"+" Dim : "+ ((ArrayList)entry.getValue()).size());
        }System.out.println("Diccionario Gini");
         for (Map.Entry entry : pc.Gini.entrySet()) {
            System.out.println("Clave : " + entry.getKey()
                    +" ->"+ " Valor : " + entry.getValue());
                    }
    }
}
