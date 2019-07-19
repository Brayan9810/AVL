package logica;

import java.awt.Color;
import java.awt.Graphics;


public class Nodo {

    public Comparable dato;      	 
    public Nodo izquierdo;            
    public Nodo derecho;              
    public int altura;                   
    private int x;
    private int y;
 
    public Nodo(Comparable dato) {
        this(dato, null, null);
    }
    public void paint(Graphics lapiz) {
        
        lapiz.setColor(Color.black);        
        lapiz.fillOval(x, y, 30, 30);
        lapiz.setColor(Color.white);
        lapiz.drawOval(x, y, 30, 30);
        lapiz.setColor(Color.white);
        lapiz.drawString(dato + "", x + 12, y + 20);
        lapiz.setColor(Color.black);
        if (izquierdo != null) {
            lapiz.drawLine(x + 3, y + 23, izquierdo.getX() + 23, izquierdo.getY() + 3);
            izquierdo.paint(lapiz);
        }
        if (derecho != null) {
            lapiz.drawLine(x + 27, y + 23, derecho.getX() + 3, derecho.getY() + 3);
            derecho.paint(lapiz);
        }
    }
    public Nodo(Comparable dato, Nodo izq, Nodo der) {
        this.dato = dato;
        this.izquierdo = izq;
        this.derecho = der;
        altura = 0; 
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDato() {
        return (int) dato;
    }
    
    public boolean esHoja() {
        return derecho == null && izquierdo == null;
    }

    public Nodo getHijoIzq() {
        return izquierdo;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        this.izquierdo = hijoIzquierdo;
    }

    public Nodo getHijoDer() {
        return derecho;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        this.derecho = hijoDerecho;
    }
}
