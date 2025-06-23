/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicaprogramada3;

import java.io.Serializable;
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
public class Empleados implements Serializable {
    private String nombre;
    private String cedula;
    private String puesto;
    private String departamento;   
    private double salario;
    private String fechaIngreso;

        public Empleados(String nombre, String cedula, String puesto, String departamento, double salario, String fechaIngreso) {
            this.nombre = nombre;
            this.cedula = cedula;
            this.puesto = puesto;
            this.departamento = departamento;
            this.salario = salario;
            this.fechaIngreso = fechaIngreso;
        }

    public Object[] toArray() {
        return new Object[]{nombre, cedula, puesto, departamento, salario, fechaIngreso, calcularAguinaldo()};
    }

    public double calcularAguinaldo() {
        return salario * 12 / 12; // Aguinaldo mensual promedio
    }
}
    


