/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.LinkedHashMap;
import java.util.List;
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
                    double t1=(double)ss/(ss+sn);
                    double t2=(double)sn/(ss+sn);
                    double gn1=1-Math.pow(t1,2)-Math.pow(t2,2);
                    
                    double t3=(double)ns/(ns+nn);
                    double t4=(double)nn/(ns+nn);
                    double gn2=1-Math.pow(t3,2)-Math.pow(t4,2);
                    double t5=(double)(ss+sn)/atri.size();
                    double t6=(double)(ns+nn)/atri.size();
                    gn=(double)Math.round((t5*gn1+t6*gn2)*1000)/1000d;
                    
                
                }else{
                    for(int i: atri){
                        if (i==1){
                            ss++;
                        }else{
                            nn++;
                        }
                    
                    }
                    double t1=(double)ss/atri.size();
                    double t2=(double)nn/atri.size();
                    
                    gn=1-Math.pow(t1,2)-Math.pow(t2,2);
                    //System.out.println(gn);
                    gn=(double)Math.round(gn*1000)/1000d;
                
                }

                return gn;
         
         }
         
         public void  actGini(String target){
             for(Map.Entry x:this.dataset.entrySet()){
                 String clave=String.valueOf(x.getKey());
                 if (clave.equals(target)){
                     double gn=obtenerGini(target, target);
                     Gini.replace(clave, gn);
                 
                 }else{
                     Gini.put(clave,obtenerGini(clave,target));
                 }

             }
         
         }
         
         public List<pacientes>SegmentarDatos(String Atributo){
             List<pacientes> pcts= new ArrayList<>();
             pacientes pctP=new pacientes();//Positivos
             pacientes pctN=new pacientes();//Negativos
             this.actGini(Atributo);
             String menorAtributo=obtenerMenor();
             if (Atributo.equals(menorAtributo)){
                 pcts=null;
             }else{
                  
                  ArrayList<Integer> atri=dataset.get(menorAtributo);
                  for(Map.Entry entrada: this.dataset.entrySet()){//mapa
                      if(!Atributo.equals(menorAtributo)){
                          String clave=String.valueOf(entrada.getKey());
                          //System.out.println(entrada.getValue());
                          Iterator it = atri.listIterator();
                          int x= (Integer)it.next();
                          while(it.hasNext()){//lista
                              //System.out.println(j);
                              int valor=(int)((ArrayList) entrada.getValue()).get(x);
                              if( x == 1){
                                  if(pctP.dataset.get(clave)!=null){pctP.dataset.get(clave).add(valor);}
                                  else{pctP.dataset.put(clave, new ArrayList<>());}
                                  //System.out.println(pctP.dataset.get(clave).size());95
                              
                              }else{
                                  if(pctN.dataset.get(clave)==null){pctN.dataset.put(clave, new ArrayList<>());}
                                  else{pctN.dataset.get(clave).add(valor);}
                                  //System.out.println(pctN.dataset.get(clave).size());202
                              
                              
                              }x=(Integer)it.next();
                              
                      }
////                      System.out.println(pctP.dataset.get(clave).size());   
////                      System.out.println(pctN.dataset.get(clave).size());
                      pctP.Gini.put(clave, 0.0);
                      pctN.Gini.put(clave, 0.0);
                      }
                      
                  }
             
             }
             pctP.dataset.remove(menorAtributo);
             pctN.dataset.remove(menorAtributo);
             pcts.add(pctP);
             pcts.add(pctN);
             return pcts;
         }
         
         private String obtenerMenor(){
             String atributo="";
             double menor = 0;
             for (Map.Entry entrada: this.Gini.entrySet()){
                 double valor=(double)entrada.getValue();
                 String clave=String.valueOf(entrada.getKey());
                 if (menor==0){menor=valor;};
                 
                 if (valor<menor){
                     atributo= clave;
                     menor=valor;   
                 }             
             }
             return atributo;
         }
         
    public static void main (String [] args){
         pacientes pc= pacientes.CargarDatos("src/Files/pacientes1.csv");
         
         //Se debe cambiar el path del archivo, ya que se encuentra en otra carpeta
         
//         System.out.println("Diccionario DataSet");
//         for (Map.Entry entry : pc.dataset.entrySet()) {
//            System.out.println("Clave : " + entry.getKey()
//                    +" ->"+ " Valor : " + entry.getValue()+"->"+" Dim : "+ ((ArrayList)entry.getValue()).size());
//        System.out.println("Diccionario Gini");
//         for (Map.Entry entry : pc.Gini.entrySet()) {
//            System.out.println("Clave : " + entry.getKey()
//                    +" ->"+ " Valor : " + entry.getValue());
//                    }
//       System.out.println(pc.obtenerGini("edad", "edad"));
        pc.actGini("muerte");
        pc.SegmentarDatos("edad");
//        
        System.out.println("Diccionario Gini");
         for (pacientes p : pc.SegmentarDatos("edad")) {
             
            for (Map.Entry x: p.dataset.entrySet()){
                System.out.println("Clave : " + x.getKey()
                   +" ->"+ " Valor : " + x.getValue());
                System.out.println(((ArrayList)x.getValue()).size());
            }
                    }
         System.out.println(pc.obtenerMenor());
    }
}
