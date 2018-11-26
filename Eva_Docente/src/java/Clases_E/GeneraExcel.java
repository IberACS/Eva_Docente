/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_E;

import Sql_E.Conexion;
import Sql_E.Procedimientos;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author mac3
 */
public class GeneraExcel {

    private ArrayList<Evaluadores>Evaluaron;

    HSSFWorkbook libro = null;

    public HSSFWorkbook Genera(String Periodo) {

        try {
            Conexion con = new Conexion();
            Connection c;
            c = con.conexion_SCE2005();
            Procedimientos pr = new Procedimientos();
            //Workbook libro = new HSSFWorkbook();
            libro = new HSSFWorkbook();
            FileOutputStream archivo = new FileOutputStream("Listado.xls");
            Sheet hoja = libro.createSheet("Alumnos que han evaluado");

            Evaluaron= pr.Evaluaron(c, Periodo);

            for (int f = 0; f < Evaluaron.size(); f++) {//// se ejecutan el numero de iteraciones para la cantidad de filas
                Row fila = hoja.createRow(f);////se inican las filas con el objeto row for(int ce=0;ce<5;ce++){ ///Ya con la fila creada comenzaremos a agregar las celdas, 5 por cada fila...
                Cell celda = fila.createCell(0);
                Cell celda1 = fila.createCell(1);
                Cell celda2 = fila.createCell(2);
                Cell celda3 = fila.createCell(3);
                Cell celda4 = fila.createCell(4);
                if (f == 0) {///si la fila es la fila 0 entonces corresponde al encabezado
                    celda.setCellValue("Matricula");
                    celda1.setCellValue("Nombre");
                    celda2.setCellValue("Paterno");
                    celda3.setCellValue("Materno");
                    celda4.setCellValue("Carrera");

                    if (Evaluaron.size() == 1) {
                        fila = hoja.createRow(f+1);
                        celda = fila.createCell(0);
                        celda1 = fila.createCell(1);
                        celda2 = fila.createCell(2);
                        celda3 = fila.createCell(3);
                        celda4 = fila.createCell(4);
                        celda.setCellValue(Evaluaron.get(f).Matricula);
                        celda1.setCellValue(Evaluaron.get(f).Nombre);
                        celda2.setCellValue(Evaluaron.get(f).Paterno);
                        celda3.setCellValue(Evaluaron.get(f).Materno);
                        celda4.setCellValue(Evaluaron.get(f).Carrera);
                    }
                    //celda5.setCellValue("Carrera");
                } else {
                    //celda.setCellValue("Valor celda "+ce+","+f);
                    celda.setCellValue(Evaluaron.get(f - 1).Matricula);
                    celda1.setCellValue(Evaluaron.get(f - 1).Nombre);
                    celda2.setCellValue(Evaluaron.get(f - 1).Paterno);
                    celda3.setCellValue(Evaluaron.get(f - 1).Materno);
                    celda4.setCellValue(Evaluaron.get(f - 1).Carrera);
                    if(f==(Evaluaron.size()-1)){
                     fila = hoja.createRow(f+1);
                      celda = fila.createCell(0);
                        celda1 = fila.createCell(1);
                        celda2 = fila.createCell(2);
                        celda3 = fila.createCell(3);
                        celda4 = fila.createCell(4);
                        celda.setCellValue(Evaluaron.get(f).Matricula);
                        celda1.setCellValue(Evaluaron.get(f).Nombre);
                        celda2.setCellValue(Evaluaron.get(f).Paterno);
                        celda3.setCellValue(Evaluaron.get(f).Materno);
                        celda4.setCellValue(Evaluaron.get(f).Carrera);
                    }
                }
            }
            libro.write(archivo);
            //archivo.close();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return libro;
    }
    public static void main(String[] args) {
        GeneraExcel ex = new GeneraExcel();

        //ex.Genera();
    }

}
