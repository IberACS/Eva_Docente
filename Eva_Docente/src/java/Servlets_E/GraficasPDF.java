/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_E;

import Clases_E.Conversor;
import Sql_E.Conexion;
import Sql_E.Procedimientos;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mac3
 */
public class GraficasPDF extends HttpServlet {
    private ArrayList Persona;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setContentType("text/html;charset=iso-8859-1");
        //response.setContentType("text/html;charset=UTF-8");

           String PDA2=request.getParameter("PDA2");
           String PPC2=request.getParameter("PPC2");
           String PAA2= request.getParameter("PAA2");
           String PEM2= request.getParameter("PEM2");
           String PM2= request.getParameter("PM2");
           String PE2= request.getParameter("PE2");
           String PC2= request.getParameter("PC2");
           String PG2= request.getParameter("PG2");
           String PTLA2=request.getParameter("PTLA2");
           String PS2 = request.getParameter("PS2");
           String RG2 = request.getParameter("RG2");
           System.out.println("rg2: "+RG2);
           String año=request.getParameter("period").substring(0, 4);
           Conversor  conv = new Conversor();
           HttpSession session= request.getSession();
           String s=(String) session.getAttribute("Administrador");
        if(null!=s){
        
            try {
           
            Connection con;
            Conexion co= new Conexion();
            con=co.conexion_SCE2005();
            Procedimientos pr= new Procedimientos();
            Document document = new Document(PageSize.LETTER, 40, 41, 20, 25);/////////(tamaño de la hoja,margen izq,margen der,margen sup, margen inf)
            //BaseFont baseFont = BaseFont.createFont("/Users/mac3/Documents/NetBeansProjects/Eva_Docente/src/java/Servlets_E/Gotham-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
            BaseFont baseFont = BaseFont.createFont("C:\\Fuentes\\Gotham-Book.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
            Font fuente= new Font(baseFont);      
            Font fuenten= new Font(baseFont); 
            Font fuentec= new Font(baseFont);
            fuentec.setSize(10);
            fuente.setSize(10);
            fuenten.setSize(10);  
            fuenten.setStyle(Font.BOLD);
            fuentec.setColor(BaseColor.WHITE);
            java.util.Date fecha = new Date();   
            DateFormat fe = DateFormat.getDateInstance(DateFormat.LONG);
            Calendar fecha2 = Calendar.getInstance();
            int mes=fecha2.get(Calendar.MONTH)+1;
            //int año=fecha2.get(Calendar.YEAR);
            Grafica ObGra = new Grafica();
              

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
                        pdfWriter.setPageEvent(conv);  
                        document.open();
                        document.addTitle("Resultados de Evaluación Docente ");
                        Image image = Image.getInstance(this.getClass().getResource( "logo.jpg")  );
                        image.scaleAbsoluteWidth(90f);
                        image.scaleAbsoluteHeight(43f);
                        Image grafica=Image.getInstance(ObGra.Imagen());///////////////////////////////SE OBTIENE LA IMAGEN DE LA GRAFICA
                        grafica.scaleAbsoluteWidth(400f);
                        grafica.scaleAbsoluteHeight(250f);
                        

                        //image.setAbsolutePosition(453f,661f);
                        float[] medidaCeldas = {4.2f, 9.8f, 7f};
                        PdfPTable tabla = new PdfPTable(3);
                        tabla.setWidthPercentage(100f);
                        tabla.setWidths(medidaCeldas);
                       
                        PdfPCell cell = new PdfPCell(new Paragraph("Nombre del documento: Formato de \nResultados de la Evaluación por Docente",fuente));
                        cell.addElement(image);
                        cell.setRowspan(3);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        //cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        tabla.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("Nombre del documento: Formato de \nResultados de la Evaluación por Docente",fuente));
                        cell.setRowspan(2);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("Código: TEST/D-AC-PO-006-01",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("Revisión: 0",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("Referencia a la Norma ISO 9001:2008   \n5.2,7.2.3, 8.2.1",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(String.valueOf("Página 1 de 2"),fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla.addCell(cell);

                        document.add(tabla);
                        Paragraph parrafov = new Paragraph();
                        parrafov.add(String.valueOf("                                                                                                                                   "));
                        document.add(parrafov);
                        Paragraph parrafo = new Paragraph(String.valueOf("RESULTADOS DE LA EVALUACIÓN DE LOS PROFESORES\n AÑO (1) "+año+", APLICACIÓN DE (2) "+ conv.Fecha(String.valueOf(mes))+"\nCUESTIONARIO PARA ALUMNOS "),fuenten);
                        //Paragraph parrafo = new Paragraph(String.valueOf("RESULTADOS DE LA EVALUACIÓN DE LOS PROFESORES\n AÑO (1) 2016, APLICACIÓN DE (2) "+ conv.Fecha(String.valueOf(mes))+"\nCUESTIONARIO PARA ALUMNOS "),fuenten);
                        parrafo.setAlignment(Paragraph.ALIGN_CENTER); 
                        document.add(parrafo);
                        document.add(parrafov);
                        Paragraph parrafo2= new Paragraph("(3)",fuente);
                        parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
                        document.add(parrafo2);
                        grafica.setAlignment(Element.ALIGN_CENTER);
                        document.add(grafica);
//                        Paragraph parrafo3= new Paragraph("",fuente);
//                        parrafo3.setAlignment(Paragraph.ALIGN_CENTER);
                        
                        float[] medidaCeldas2 = {10.5f,10.5f};
                        PdfPTable tabla2 = new PdfPTable(2);///numero de columnas
                        tabla2.setWidthPercentage(80f);
                        tabla2.setWidths(medidaCeldas2);
                        
                        fuente.setSize(8);

                        cell = new PdfPCell(new Paragraph("A Dominio de la disciplina",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(PDA2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("B Planificación del Curso",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(PPC2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("C Ambientes de Aprendizaje",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(PAA2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("D Estrategias, Métodos y Técnicas de Aprendizaje",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(PEM2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("E Motivación",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(PM2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                       cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("F Evaluación del aprendizaje",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(PE2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("G Comunicación",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(PC2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("H Gestión del Curso",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(PG2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("I Tecnologia de la Información y de la Comunicación",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(PTLA2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("J Satisfacción General",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(PS2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("T Resultado Global",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph(RG2,fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        tabla2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        tabla2.addCell(cell);
                        document.add(parrafov);
//                        Paragraph p= new Paragraph(String.valueOf("]"),fuente);
//                        p.setAlignment(Element.ALIGN_CENTER);
                        document.add(tabla2);
//                        document.add(p);
                        document.add(parrafov);
                        
                        float[] medidaCeldas3 = {10.5f,10.5f};
                        PdfPTable tabla3 = new PdfPTable(2);///numero de columnas
                        tabla3.setWidthPercentage(80f);
                        tabla3.setWidths(medidaCeldas3);
                        fuente.setSize(10);
                        fuenten.setSize(10);
                        
                        cell = new PdfPCell(new Paragraph("ELABORÓ (4)",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla3.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("REVISÓ Y AUTORIZÓ (5)",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla3.addCell(cell);
                        
                        Persona=pr.Persona(con,Integer.parseInt(s));    
                        
                        cell = new PdfPCell(new Paragraph("\n "+Persona.get(3) +" "+Persona.get(0)+" "+Persona.get(1) +" "+Persona.get(2) +" \n" +
                        "Jefe(a) del Departamento de Desarrollo \nAcadémico  \n   ",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla3.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("\nRoberto González Galán\n" +
                        "Subdirector de Apoyo y Desarrollo \nAcadémico   \n  ",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla3.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("\n__________________________\n" +
                                                          "  NOMBRE, PUESTO Y FIRMA ",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla3.addCell(cell);
                        
                        cell = new PdfPCell(new Paragraph("\n__________________________\n" +
                                                          "  NOMBRE, PUESTO Y FIRMA ",fuente));
                        cell.setRowspan(1);/// numero de filas que ocupa la celda
                        cell.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla3.addCell(cell);
                        document.add(tabla3);
                        document.add(parrafov);
                        fuente.setSize(8);
                       // document.add(new Paragraph (String.valueOf(" TEST/D-AC-PO-006-01                                                                                                                                                                                            Rev. 0"),fuente));
                       
                        document.add(parrafov);
                        document.add(parrafov);
                        document.add(parrafov);
                        document.add(parrafov);
                        
                        float[] medidaCeldas4 = {4.2f, 9.8f, 7f};
                        PdfPTable tabla4 = new PdfPTable(3);
                        tabla4.setWidthPercentage(100f);
                        tabla4.setWidths(medidaCeldas4);
                        fuente.setSize(10);
                        PdfPCell cell1 = new PdfPCell(new Paragraph("Nombre del documento: Formato de \nResultados de la Evaluación por Docente",fuente));
                        cell1.addElement(image);
                        cell1.setRowspan(3);/// numero de filas que ocupa la celda
                        cell1.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        //cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        tabla4.addCell(cell1);
                        
                        cell1 = new PdfPCell(new Paragraph("Nombre del documento: Formato de \nResultados de la Evaluación por Docente",fuente));
                        cell1.setRowspan(2);/// numero de filas que ocupa la celda
                        cell1.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla4.addCell(cell1);
                        
                        cell1 = new PdfPCell(new Paragraph("Código: TEST/D-AC-PO-006-01",fuente));
                        cell1.setRowspan(1);/// numero de filas que ocupa la celda
                        cell1.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla4.addCell(cell1);
                        
                        cell1 = new PdfPCell(new Paragraph("Revisión: 0",fuente));
                        cell1.setRowspan(1);/// numero de filas que ocupa la celda
                        cell1.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla4.addCell(cell1);
                        
                        cell1 = new PdfPCell(new Paragraph("Referencia a la Norma ISO 9001:2008   \n5.2,7.2.3, 8.2.1",fuente));
                        cell1.setRowspan(1);/// numero de filas que ocupa la celda
                        cell1.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla4.addCell(cell1);
                        
                        cell1 = new PdfPCell(new Paragraph(String.valueOf("Página 2 de 2"),fuente));
                        cell1.setRowspan(1);/// numero de filas que ocupa la celda
                        cell1.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla4.addCell(cell1);
                        document.add(tabla4);
                        fuenten.setSize(10);
                        fuenten.setStyle(Font.BOLD);
                        Paragraph p1= new Paragraph("\n \n \n \n \n \n \n \n");
                        Chunk ck = new Chunk("INSTRUCTIVO DE LLENADO \n \n",fuenten);
                        p1.add(ck);
                        p1.setAlignment(Element.ALIGN_CENTER);
                        document.add(p1);
                        
                        float[] medidaCeldas5 = {10.5f, 10.5f};
                        PdfPTable tabla5 = new PdfPTable(2);
                        tabla5.setWidthPercentage(100f);
                        
                        PdfPCell cell2 = new PdfPCell(new Paragraph("NÚMERO",fuenten));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla5.addCell(cell2);
                        
                        cell2= new PdfPCell(new Paragraph ("DESCRIPCIÓN",fuenten));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla5.addCell(cell2);
                        
                        fuente.setSize(10);
                        fuente.setStyle(Font.NORMAL);
                        cell2= new PdfPCell(new Paragraph ("1",fuente));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla5.addCell(cell2);
                        
                        cell2= new PdfPCell(new Paragraph ("Anotar el año en que se realizó la Evaluación Docente.\n",fuente));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla5.addCell(cell2);
                        
                        cell2= new PdfPCell(new Paragraph ("2",fuente));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla5.addCell(cell2);
                        
                        cell2= new PdfPCell(new Paragraph ("Anotar el mes en que se realizó la Evaluación Docente.",fuente));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla5.addCell(cell2);
                        
                        cell2= new PdfPCell(new Paragraph ("3",fuente));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla5.addCell(cell2);
                        
                        cell2= new PdfPCell(new Paragraph ("Copiar y pegar los datos del docente y la gráfica de resultados del Sistema de Evaluación Docente.",fuente));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla5.addCell(cell2);
                        
                        cell2= new PdfPCell(new Paragraph ("4",fuente));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla5.addCell(cell2);
                        
                        cell2= new PdfPCell(new Paragraph ("Anotar nombre, puesto y firma de quien elabora.",fuente));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla5.addCell(cell2);

                        cell2= new PdfPCell(new Paragraph ("5",fuente));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla5.addCell(cell2);

                        cell2= new PdfPCell(new Paragraph ("Anotar nombre, puesto y firma de quien autoriza.",fuente));
                        cell2.setRowspan(1);/// numero de filas que ocupa la celda
                        cell2.setColspan(1);/////// numero de columnas que ocupa la celda
                        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                        tabla5.addCell(cell2);
                        document.add(tabla5);
                        

                        document.close();
                        response.setHeader("Expires", "0");
                        response.setHeader("Cache-Control",
                         "must-revalidate, post-check=0, pre-check=0");
                        response.setHeader("Pragma", "public");
                        // Configuramos el content type
                        response.setContentType("application/pdf");
                        response.setContentLength(baos.size());
//                        // Esccribir el ByteArrayOutputStream a el ServletOutputStream
                        OutputStream os = response.getOutputStream();
                        baos.writeTo(os);
                        os.flush();
                        os.close();
                        //ObGra.Borra();
            
        }
       
        catch(DocumentException e){
            log ("Error al crear documento: "+e.getLocalizedMessage());
        }
        catch(IOException a){
            log("Error al leer archivo: "+a.getMessage());
        }
        
    }
          else{
            PrintWriter out = response.getWriter();
                 response.setContentType("text/html");  
                 out.println("<script type=\"text/javascript\">");  
                 out.println("alert('Sesión expirada');");  
                 out.println("</script>");
                 out.println("<script language='javascript'>window.close();</script>");  
                 log("sesión expirada");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       this.doPost(request, response);
    }
    
}
