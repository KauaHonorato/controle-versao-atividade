/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listasingleton.view;

import com.mycompany.listasingleton.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author olive
 */


public class TelaListaCompras extends JFrame {

    private JTextField campoNome;
    private JTextField campoQuantidade;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaVisual;

    public TelaListaCompras() {
        setTitle("Lista de Compras");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

       
        Color corFundo = new Color(255, 255, 255); // branco
        Color corLaranja = new Color(255, 140, 0); // laranja
        Color corPreto = new Color(30, 30, 30);    // preto

   
        JPanel painelEntrada = new JPanel(new GridLayout(3, 2, 8, 8));
        painelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelEntrada.setBackground(corFundo);

        JLabel labelItem = new JLabel("Item:");
        labelItem.setForeground(corPreto);
        labelItem.setFont(new Font("SansSerif", Font.BOLD, 14));
        painelEntrada.add(labelItem);

        campoNome = new JTextField();
        campoNome.setBackground(Color.WHITE);
        campoNome.setForeground(corPreto);
        campoNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
        painelEntrada.add(campoNome);

        JLabel labelQtd = new JLabel("Quantidade:");
        labelQtd.setForeground(corPreto);
        labelQtd.setFont(new Font("SansSerif", Font.BOLD, 14));
        painelEntrada.add(labelQtd);

        campoQuantidade = new JTextField();
        campoQuantidade.setBackground(Color.WHITE);
        campoQuantidade.setForeground(corPreto);
        campoQuantidade.setFont(new Font("SansSerif", Font.PLAIN, 14));
        painelEntrada.add(campoQuantidade);

        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setBackground(corLaranja);
        botaoAdicionar.setForeground(Color.WHITE);
        botaoAdicionar.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoAdicionar.setFocusPainted(false);
        painelEntrada.add(botaoAdicionar);

        add(painelEntrada, BorderLayout.NORTH);

        
        modeloLista = new DefaultListModel<>();
        listaVisual = new JList<>(modeloLista);
        listaVisual.setBackground(Color.WHITE);
        listaVisual.setForeground(corPreto);
        listaVisual.setFont(new Font("Monospaced", Font.PLAIN, 14));
        listaVisual.setSelectionBackground(corLaranja);
        listaVisual.setSelectionForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(listaVisual);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(corLaranja, 2, true),
                "Itens da Lista",
                0, 0, new Font("SansSerif", Font.BOLD, 14),
                corLaranja
        ));
        add(scrollPane, BorderLayout.CENTER);

     
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });
    }

    private void adicionarItem() {
        String nome = campoNome.getText().trim();
        String qtdTexto = campoQuantidade.getText().trim();

        if (nome.isEmpty() || qtdTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        try {
            int quantidade = Integer.parseInt(qtdTexto);
            Item item = new Item(nome, quantidade);

            
            ListaDeCompras.getInstancia().adicionarItem(item);

          
            modeloLista.clear();
            for (Item i : ListaDeCompras.getInstancia().getItens()) {
                modeloLista.addElement(i.toString());
            }

            
            campoNome.setText("");
            campoQuantidade.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade deve ser um número!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaListaCompras().setVisible(true);
        });
    }
}


