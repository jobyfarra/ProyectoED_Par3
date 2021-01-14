/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;

import java.io.FileReader;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.Map;



/**
 *
 * @author User
 */
public class pacientes {
         private Map <String,ArrayList<Integer>> dataset = new LinkedHashMap<>();
         private Map <String,Double> Gini = new LinkedHashMap <> ();
         
         public static pacientes  CargarDatos(String nombreA) {
             pacientes p1= new pacientes();
             try {
                 BufferedReader  br =new BufferedReader(new FileReader(nombreA));
                 String encabezado= br.readLine();//se captura el headline de los atributos
                 String[] atributos=encabezado.split(",");
                 for (String x: atributos){
                    p1.dataset.put( x ,new ArrayList <Integer>());
                    p1.Gini.put(x, 0.0);
                 }
                String linea = br.readLine();
                 while(linea!=null){//se lee las 299 entradas del archivo
                     String[] datos =linea.split(",");
                     for(int j=0;j<datos.length;j++){
                        p1.dataset.get(atributos[j]).add(Integer.parseInt(datos[j]));//se cargan las ArrayLists con los valores de las entradas
                     }
                    linea=br.readLine();
                 }                 
             } catch (Exception ex) {
                 System.out.println("No se encontro el Archivo");  
             }        
             return p1;
         }
         
         private double obtenerGini(String Atributo, String Target){
                double gn;
                int ss=0;
                int sn=0;
                int ns=0;
                int nn=0;
                ArrayList<Integer> atri=this.dataset.get(Atributo);
                ArrayList<Integer> trgt=this.dataset.get(Target);
                
                if (Atributo!=Target){
                    for(int j=0;j<atri.size();j++){                      
                        switch (atri.get(j)){
                            case 1:
                                switch(trgt.get(j)){
                                    case 1:
                                        ss++;
                                        break;
                                    case 0:
                                        sn++;
                                        break;
                                
                                }
                                break;
                            case 0:
                                switch(trgt.get(j)){
                                    case 1:
                                        ns++;
                                        break;
                                    case 0:
                                        nn++;
                                        break;                               
                                }
                                break;                               
                        } 
                    }
                    double gn1=1-Math.pow(ss/(ss+sn),2)-Math.pow(sn/(ss+sn),2);
                    System.out.println(gn1);
                    double gn2=1-Math.pow(ns/(ns+nn),2)-Math.pow(nn/(ns+nn),2);
                    System.out.println(gn2);
                    gn=((ss+sn)/atri.size())*gn1+((ns+nn)/atri.size())*gn2;
                    
                
                }else{
                    for(int i: atri){
                        if (i==1){
                            ss++;
                        }else{
                            nn++;
                        }
                    
                    }
                    
                    gn=1-Math.pow(ss/(atri.size()),2)-Math.pow(nn/atri.size(),2);
                    System.out.println();
                
                }
                
                
                
                return gn;
         
         }
         
         public void  actGini(String Atributo){
         
         
         }
  
    public static void main (String [] args){
         pacientes pc= pacientes.CargarDatos("src/Files/pacientes1.csv");
         //Se debe cambiar el path del archivo, ya que se encuentra en otra carpeta
         
//         System.out.println("Diccionario DataSet");
//         for (Map.Entry entry : pc.dataset.entrySet()) {
//            System.out.println("Clave : " + entry.getKey()
//                    +" ->"+ " Valor : " + entry.getValue()+"->"+" Dim : "+ ((ArrayList)entry.getValue()).size());
//        }System.out.println("Diccionario Gini");
//         for (Map.Entry entry : pc.Gini.entrySet()) {
//            System.out.println("Clave : " + entry.getKey()
//                    +" ->"+ " Valor : " + entry.getValue());
//                    }
        System.out.println(pc.obtenerGini("anemia", "anemia"));
    }
}
