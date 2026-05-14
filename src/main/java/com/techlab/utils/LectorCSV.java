package com.techlab.utils;
import com.techlab.productos.*;
import com.techlab.tienda.Tienda;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
public class LectorCSV {
    public static void cargarProductos(Tienda tienda, String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            boolean primeraLinea = true;
            int nextId = tienda.getProductos().size() + 1;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] campos = linea.split(",");
                if (campos.length < 7) continue;
                int tipo = Integer.parseInt(campos[0].trim());
                String nombre = campos[1].trim();
                double precio = Double.parseDouble(campos[2].trim());
                String desc = campos[3].trim();
                String marca = campos[4].trim();
                int stock = Integer.parseInt(campos[5].trim());
                int stockMin = Integer.parseInt(campos[6].trim());
                Producto p = null;
                switch (tipo) {
                    case 1 -> p = new Producto(nextId++, nombre, precio, desc, marca);
                    case 2 -> {
                        int ml = Integer.parseInt(campos[7].trim());
                        boolean alcohol = Boolean.parseBoolean(campos[8].trim());
                        p = new Bebida(nextId++, nombre, precio, desc, marca, ml, alcohol);
                    }
                    case 3 -> {
                        String talle = campos[7].trim();
                        String color = campos[8].trim();
                        p = new Ropa(nextId++, nombre, precio, desc, marca, talle, color);
                    }
                    case 4 -> {
                        LocalDate fecha = LocalDate.parse(campos[7].trim());
                        p = new ProductoPerecedero(nextId++, nombre, precio, desc, marca, fecha);
                    }
                }
                if (p != null) {
                    tienda.agregarProducto(p, stock, stockMin);
                }
            }
            System.out.println(CONST.GREEN + "CSV cargado exitosamente (" + (nextId - 1) + " productos)." + CONST.RESET);
        } catch (IOException | NumberFormatException e) {
            System.err.println(CONST.RED + "Error al leer el CSV: " + e.getMessage() + CONST.RESET);
        }
    }
}

