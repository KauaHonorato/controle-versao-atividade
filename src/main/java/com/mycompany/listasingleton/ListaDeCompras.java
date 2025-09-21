/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listasingleton;
import java.util.*;
/**
 *
 * @author olive
 */

//classe do singleton


public class ListaDeCompras {

   
    private static ListaDeCompras instancia; // o atributo estatico privado
    private List<Item> itens;

   
    private ListaDeCompras() { // construtor privado
        itens = new ArrayList<>();
    }

                
    public static synchronized ListaDeCompras getInstancia() { // metodo publico e estatico
        if (instancia == null) {
            instancia = new ListaDeCompras();
        }
        return instancia;
    }

    
    public synchronized void adicionarItem(Item item) {
        itens.add(item);
    }

    public synchronized List<Item> getItens() {
        return new ArrayList<>(itens);
    }
}

//