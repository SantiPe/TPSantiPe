package com.santipe.tp_club;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Trabajo practico - Clubes
 * Clase para Importar y Exportar CSV hacia/desde la base de datos
 * @author Santiago Perrin <1santiagoperrin@gmail.com>
 */
public class CSV {
    /*
        BASADO EN:
        https://examples.javacodegeeks.com/core-java/sql/import-csv-file-to-mysql-table-java-example/
        https://www.journaldev.com/12014/opencsv-csvreader-csvwriter-example
    */
    
    public static boolean Importar(Connection conn, String filename) {
        try {
            // Generamos una instancia del lector CSV
            CSVReader reader = new CSVReader(new FileReader(filename), ',');
                
            // Creamos una nueva consulta
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Socios (Nombre, Apellido, Calle, Numero, Telefono, Documento) VALUES (?, ?, ?, ?, ?, ?)");
            
            String[] rowData = null;
            int i = 0;
            while ((rowData = reader.readNext()) != null) {
                // Nombre, Apellido, Calle, Numero, Telefono, Documento
                pstmt.setString(1, rowData[0].trim());
                pstmt.setString(2, rowData[1].trim());
                pstmt.setString(3, rowData[2].trim());
                pstmt.setInt(4, Integer.parseInt(rowData[3].trim()));
                pstmt.setString(5, rowData[4].trim());
                pstmt.setInt(6, Integer.parseInt(rowData[5].trim()));
                    
                pstmt.addBatch();
                    
                if (++i % 10 == 0) {
                    pstmt.executeBatch();
                }
            }
            
            pstmt.executeBatch();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean Exportar(Connection conn, String filename) {
        try {
            // Generamos un nuevo CSVWriter
            CSVWriter csvWriter = new CSVWriter(new FileWriter(filename, true), ',');
                     
            // Generamos una nueva consulta
            Statement stmt = conn.createStatement();
            
            // Executamos la consulta
            ResultSet rs = stmt.executeQuery("SELECT Id, Nombre, Apellido, Calle, Numero, Telefono, Documento FROM Socios");
            
            // Obtenemos todas las filas
            while (rs.next()) {
                String[] fila = {
                    String.valueOf(rs.getInt("Id")),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getString("Calle"),
                    String.valueOf(rs.getInt("Numero")),
                    rs.getString("Telefono"),
                    String.valueOf(rs.getInt("Documento")) 
                };
                csvWriter.writeNext(fila);
            }
            csvWriter.close();
            return true;
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener los socios de la base de datos.");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener los socios de la base de datos.");
            System.out.println(e.getMessage());
        }
        return false;
    }
}
