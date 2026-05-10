package com.techlab;

import com.techlab.excepciones.FormatoInvalidoException;
import com.techlab.excepciones.ScannerException;
import java.util.Scanner;

public class InputScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input == null || input.trim().isEmpty()) {
            throw new ScannerException("La entrada no puede estar vacía.");
        }
        return input;
    }

    public static int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new FormatoInvalidoException("El valor ingresado no es un número entero válido.");
        }
    }

    public static double readDouble(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new FormatoInvalidoException("El valor ingresado no es un número decimal válido.");
        }
    }

    public static boolean readBoolean(String prompt) {
        System.out.print(prompt + " (s/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("s")) return true;
        if (input.equals("n")) return false;
        throw new FormatoInvalidoException("Debe ingresar 's' o 'n'.");
    }

    public static long readLong(String prompt) {
        System.out.print(prompt);
        try {
            return Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new FormatoInvalidoException("El valor ingresado no es un número largo válido.");
        }
    }
}
