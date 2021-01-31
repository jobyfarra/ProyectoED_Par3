/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Daniela
 */
public class ArbolDecision {
       private Node root;
       

    public ArbolDecision(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    
    private class Node<E> {
    private pacientes datosPacientes;
    private String atributoDominante;//atributo con menor gini de datosPacientes
    private String decision;//SI O NO, depende de DEATH_EVENT
    private Node left, right, parent;

    
    
        public pacientes getDatosPacientes() {
            return datosPacientes;
        }

        public void setDatosPacientes(pacientes datosPacientes) {
            this.datosPacientes = datosPacientes;
        }


        public String getAtributoDominante() {
            return atributoDominante;
        }

        public void setAtributoDominante(String atributoDominante) {
            this.atributoDominante = atributoDominante;
        }

        public String getDecision() {
            return decision;
        }

        public void setDecision(String decision) {
            this.decision = decision;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
        
        
        
//        public ArbolDecision crearArbol(Node<E> datoPacientes){//add
//
//        }
//        
//        private Node<E> crearArbol(Node<E> n){
//            if(n.getAtributoDominante()==null){
//                return n;
//            }
//            else{
//                n.getLeft().setAtributoDominante(atributoDominante);        
//            }
//            
//        }        

      
        
        
        

    
    
}

}
