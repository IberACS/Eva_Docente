/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_E;

/**
 *
 * @author mac3
 */
public class RespuestasE {

    public RespuestasE() {
    }
    private int IdRespuesta;
    private int IdInscripcion;
    private double r1,r2,r3,rr4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15;
    private int IdAsigTESE;
    private int total;
    public RespuestasE(int total,double r1, double r2, double r3, double rr4, double r5, double r6, double r7, double r8, double r9, double r10, double r11, double r12, double r13, double r14, double r15) {
        this.total=total;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.rr4 = rr4;
        this.r5 = r5;
        this.r6 = r6;
        this.r7 = r7;
        this.r8 = r8;
        this.r9 = r9;
        this.r10 = r10;
        this.r11 = r11;
        this.r12 = r12;
        this.r13 = r13;
        this.r14 = r14;
        this.r15 = r15;
    }

    public RespuestasE(int IdInscripcion, int IdAsigTESE) {
        this.IdInscripcion = IdInscripcion;
        this.IdAsigTESE = IdAsigTESE;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
 
     
    public int getIdAsigTESE() {
        return IdAsigTESE;
    }

    public void setIdAsigTESE(int IdAsigTESE) {
        this.IdAsigTESE = IdAsigTESE;
    }

    public RespuestasE(int IdInscripcion) {
        this.IdInscripcion = IdInscripcion;
    }
    
    
    
    public int getIdRespuesta() {
        return IdRespuesta;
    }

    public void setIdRespuesta(int IdRespuesta) {
        this.IdRespuesta = IdRespuesta;
    }

    public int getIdInscripcion() {
        return IdInscripcion;
    }

    public void setIdInscripcion(int IdInscripcion) {
        this.IdInscripcion = IdInscripcion;
    }

    public double getR1() {
        return r1;
    }

    public void setR1(double r1) {
        this.r1 = r1;
    }

    public double getR2() {
        return r2;
    }

    public void setR2(double r2) {
        this.r2 = r2;
    }

    public double getR3() {
        return r3;
    }

    public void setR3(double r3) {
        this.r3 = r3;
    }

    public double getRr4() {
        return rr4;
    }

    public void setRr4(double rr4) {
        this.rr4 = rr4;
    }

    public double getR5() {
        return r5;
    }

    public void setR5(double r5) {
        this.r5 = r5;
    }

    public double getR6() {
        return r6;
    }

    public void setR6(double r6) {
        this.r6 = r6;
    }

    public double getR7() {
        return r7;
    }

    public void setR7(double r7) {
        this.r7 = r7;
    }

    public double getR8() {
        return r8;
    }

    public void setR8(double r8) {
        this.r8 = r8;
    }

    public double getR9() {
        return r9;
    }

    public void setR9(double r9) {
        this.r9 = r9;
    }

    public double getR10() {
        return r10;
    }

    public void setR10(double r10) {
        this.r10 = r10;
    }

    public double getR11() {
        return r11;
    }

    public void setR11(double r11) {
        this.r11 = r11;
    }

    public double getR12() {
        return r12;
    }

    public void setR12(double r12) {
        this.r12 = r12;
    }

    public double getR13() {
        return r13;
    }

    public void setR13(double r13) {
        this.r13 = r13;
    }

    public double getR14() {
        return r14;
    }

    public void setR14(double r14) {
        this.r14 = r14;
    }

    public double getR15() {
        return r15;
    }

    public void setR15(double r15) {
        this.r15 = r15;
    }

    
    
    

    
    @Override
    public String toString() {
        return "RespuestasE{" + "IdRespuesta=" + IdRespuesta + ", IdInscripcion=" + IdInscripcion + ", r1=" + r1 + ", r2=" + r2 + ", r3=" + r3 + ", rr4=" + rr4 + ", r5=" + r5 + ", r6=" + r6 + ", r7=" + r7 + ", r8=" + r8 + ", r9=" + r9 + ", r10=" + r10 + ", r11=" + r11 + ", r12=" + r12 + ", r13=" + r13 + ", r14=" + r14 + ", r15=" + r15 + '}';
    }
    
    
}
