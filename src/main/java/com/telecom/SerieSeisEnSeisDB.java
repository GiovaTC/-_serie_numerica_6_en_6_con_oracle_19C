package com.telecom;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SerieSeisEnSeisDB {

    public static void main(String[] args) {
        String filePath = "serie_6_en_6.txt";

        int numero = 0;
        int cantidad = 80;

        try {
            FileWriter writer = new FileWriter(filePath);
            Connection conn = conectarOracle();

            if (conn == null) {
                System.out.println("❌ No se pudo conectar a Oracle");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO SERIE_NUMEROS_19c (VALOR) VALUES (?)"
            );

            System.out.println("📝 Generando serie y guardando...");

            for (int i = 0; i < cantidad; i++) {
                writer.write(numero + "\n");

                stmt.setInt(1, numero);
                stmt.executeUpdate();

                numero += 6;
            }

            writer.close();
            stmt.close();
            conn.close();

            System.out.println("✅ Serie guardada en archivo: " + filePath);
            System.out.println("✅ Serie insertada en Oracle correctamente");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    private static Connection conectarOracle() {
        try {
            String url = "jdbc:oracle:thin//@localhost:1521/orcl"; // Ajustar según tu BD
            String user = "system";
            String pass = "Tapiero123";

            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}