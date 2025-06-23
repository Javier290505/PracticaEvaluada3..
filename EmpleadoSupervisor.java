/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicaprogramada3;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author jareg
 */
public class EmpleadoSupervisor extends JFrame {
    private ArrayList<Empleados> empleados = new ArrayList<>();
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public EmpleadoSupervisor() {
        setTitle("Gestión de Empleados - RH Global Services S.A.");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Cédula", "Puesto", "Departamento", "Salario", "Fecha Ingreso", "Aguinaldo"}, 0);
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelFormulario = new JPanel(new GridLayout(7, 2));
        JTextField tfNombre = new JTextField();
        JTextField tfCedula = new JTextField();
        JTextField tfPuesto = new JTextField();
        JTextField tfDepartamento = new JTextField();
        JTextField tfSalario = new JTextField();
        JTextField tfFechaIngreso = new JTextField();

        panelFormulario.add(new JLabel("Nombre:")); panelFormulario.add(tfNombre);
        panelFormulario.add(new JLabel("Cédula:")); panelFormulario.add(tfCedula);
        panelFormulario.add(new JLabel("Puesto:")); panelFormulario.add(tfPuesto);
        panelFormulario.add(new JLabel("Departamento:")); panelFormulario.add(tfDepartamento);
        panelFormulario.add(new JLabel("Salario:")); panelFormulario.add(tfSalario);
        panelFormulario.add(new JLabel("Fecha Ingreso:")); panelFormulario.add(tfFechaIngreso);

        JButton btnAgregar = new JButton("Agregar Empleado");
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCargar = new JButton("Cargar");

        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnCargar);

        add(panelFormulario, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            try {
                String nombre = tfNombre.getText();
                String cedula = tfCedula.getText();
                String puesto = tfPuesto.getText();
                String departamento = tfDepartamento.getText();
                double salario = Double.parseDouble(tfSalario.getText());
                String fechaIngreso = tfFechaIngreso.getText();

                Empleados emp = new Empleados(nombre, cedula, puesto, departamento, salario, fechaIngreso);
                empleados.add(emp);
                modeloTabla.addRow(emp.toArray());
                tfNombre.setText(""); tfCedula.setText(""); tfPuesto.setText("");
                tfDepartamento.setText(""); tfSalario.setText(""); tfFechaIngreso.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar empleado: " + ex.getMessage());
            }
        });

        btnGuardar.addActionListener(e -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("empleados.dat"))) {
                oos.writeObject(empleados);
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        });

        btnCargar.addActionListener(e -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("empleados.dat"))) {
                empleados = (ArrayList<Empleados>) ois.readObject();
                modeloTabla.setRowCount(0);
                for (Empleados emp : empleados) {
                    modeloTabla.addRow(emp.toArray());
                }
                JOptionPane.showMessageDialog(this, "Datos cargados correctamente.");
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar: " + ex.getMessage());
            }
        });
    }
}
