package logica;

import java.awt.Graphics;
import javax.swing.JComponent;

public class ArbolAVL {

    public Nodo raiz;
    private int altura = 0;
    private int numNodo;
    private int contador;

    public void insertar(Comparable x) {
        numNodo = (int) x;
        raiz = insertar(x, raiz);
    }
    private Nodo insertar(Comparable x, Nodo nodo) {
        if (nodo == null) {
            contador += 1;
            nodo = new Nodo(x, null, null);
        } //si x es menor devuelve -1
        else if (x.compareTo(nodo.dato) < 0) {
            contador += 1;
            nodo.izquierdo = insertar(x, nodo.izquierdo);
            if (altura(nodo.izquierdo) - altura(nodo.derecho) == 2) {
                if (x.compareTo(nodo.izquierdo.dato) < 0) {
                    contador += 1;
                    System.out.println("rotacionSimpleDerecha");
                    nodo = rotacionSimpleDerecha(nodo);
                    /* Caso 1:
                     p==2 y q == 1
                     */
                } else {
                    System.out.println("rotacionDobleDerecha");
                    nodo = rotacionDobleDerecha(nodo);
                    /* Caso 2 :
                     p==2 y q==-1
                     */
                }
            }
        } else if (x.compareTo(nodo.dato) > 0) {
            nodo.derecho = insertar(x, nodo.derecho);
            if (altura(nodo.derecho) - altura(nodo.izquierdo) == 2) {
                contador += 1;
                if (x.compareTo(nodo.derecho.dato) > 0) {
                    contador += 1;
                    System.out.println("rotacionSimpleIzquierda");
                    nodo = rotacionSimpleIzquierda(nodo);
                    /* Caso 4:
                     p==-2 y q ==-1
                     */
                } else {
                    System.out.println("rotacionDobleIzquierda");
                    nodo = rotacionDobleIzquierda(nodo);
                    /* Caso 3 :
                     p==-2 y q == 1
                     */
                }
            }
        } else; // Duplicado; no hago nada
        nodo.altura = max(altura(nodo.izquierdo), altura(nodo.derecho)) + 1;
        return nodo;
    }

    private static int max(int alturaHijoIzquierdo, int alturaHijoDerecho) {
        return alturaHijoIzquierdo > alturaHijoDerecho ? alturaHijoIzquierdo : alturaHijoDerecho;
    }

    private static Nodo rotacionSimpleDerecha(Nodo nodo) {
        Nodo aux = nodo.izquierdo;
        nodo.izquierdo = aux.derecho;
        aux.derecho = nodo;
        nodo.altura = max(altura(nodo.izquierdo), altura(nodo.derecho)) + 1;
        aux.altura = max(altura(aux.izquierdo), nodo.altura) + 1;
        return aux;
    }

    private static Nodo rotacionSimpleIzquierda(Nodo nodo) {
        Nodo aux = nodo.derecho;
        nodo.derecho = aux.izquierdo;
        aux.izquierdo = nodo;
        nodo.altura = max(altura(nodo.izquierdo), altura(nodo.derecho)) + 1;
        aux.altura = max(altura(aux.derecho), nodo.altura) + 1;
        return aux;
    }

    private static Nodo rotacionDobleDerecha(Nodo nodo) {
        nodo.izquierdo = rotacionSimpleIzquierda(nodo.izquierdo);
        return rotacionSimpleDerecha(nodo);
    }

    private static Nodo rotacionDobleIzquierda(Nodo nodo) {
        nodo.derecho = rotacionSimpleDerecha(nodo.derecho);
        return rotacionSimpleIzquierda(nodo);
    }

    private static int altura(Nodo t) {
        return t == null ? -1 : t.altura;
    }

    public void imprimir() {
        imprimir(raiz);
    }

    private void imprimir(Nodo nodo) {
        if (nodo != null) {
            imprimir(nodo.derecho);
            System.out.print(nodo.dato);
            imprimir(nodo.izquierdo);
        }
    }

    public int calcularAltura() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(Nodo actual) {
        if (actual == null) {
            return -1;
        } else {
            return 1 + Math.max(calcularAltura(actual.izquierdo), calcularAltura(actual.derecho));
        }
    }

    public void imprimirPorNiveles() {
        imprimirPorNiveles(raiz);
    }
    private void imprimirPorNiveles(Nodo nodo) {
        // Mediante la altura calcula el total de nodos posibles del arbol
        int max = 0;
        int nivel = calcularAltura();
        for (; nivel >= 0; nivel--) {
            max += Math.pow(2, nivel);
        }
        // Suma 1 para no utilizar la posicion 0 del array
        max++;    
        Nodo cola[] = new Nodo[max];
        // Carga en la pos 1 el nodo raiz
        cola[1] = nodo;
        int x = 1;
        for (int i = 2; i < max; i += 2, x++) {
            if (cola[x] == null) {
                cola[i] = null;
                cola[i + 1] = null;
            } else {
                cola[i] = cola[x].izquierdo;
                cola[i + 1] = cola[x].derecho;
            }
        }
        nivel = 0;
        int cont = 0;                       
        int cantidad = 1;                   
        int ultimaPosicion = 1;             
        // Cuando i es = a 2^nivel hay cambio de nivel
        // 2 ^ 0 = 1 que es el nodo raiz
        for (int i = 1; i < max; i++) {
            if (i == Math.pow(2, nivel)) {
                // Nodo raiz tiene nivel 1, por eso (nivel + 1)
                System.out.print("\n Nivel " + (nivel) + ": ");
                nivel++;
            }
            if (cola[i] != null) {
                System.out.print("[" + cola[i].dato + "]");
                cont++;
            }
            if (ultimaPosicion == i && cantidad == Math.pow(2, --nivel)) {
                if (cantidad == 1) {
                    System.out.print(" Cantidad de nodos: " + cont + " (raiz)");
                } else {
                    System.out.print(" Cantidad de nodos: " + cont);
                }
                cont = 0;
                cantidad *= 2;
                ultimaPosicion += (int) Math.pow(2, ++nivel);
            }
        }
    }
    // Imprime el arbol por niveles. Comienza por la raiz.
    public int balancearRaiz() {
        return balancear(raiz);
    }

    public int balancear(Nodo actual) {
        if (actual == null) {
            return 0;
        } else {
            return calcularAltura(actual.izquierdo) - calcularAltura(actual.derecho);
        }
    }
    public Nodo eliminar(Nodo aux, int dato) {
        if (aux == null) {
            return aux;
        }
        if (dato < aux.getDato()) {
            aux.izquierdo = eliminar(aux.izquierdo, dato);
        } else if (dato > aux.getDato()) {
            aux.derecho = eliminar(aux.derecho, dato);
        } else {
            if ((aux.izquierdo == null) || (aux.derecho == null)) {
                Nodo temp = null;
                if (temp == aux.izquierdo) {
                    temp = aux.derecho;
                } else {
                    temp = aux.izquierdo;
                }
                if (temp == null) {
                    temp = aux;
                    aux = null;
                } else {
                    aux = temp;
                }
            } else {
                Nodo temp = minValueNode(aux.derecho);
                aux.dato = temp.dato;
                aux.derecho = eliminar(aux.derecho, temp.getDato());
            }
        }
        if (aux == null) {
            return aux;
        }
        aux.altura = max(altura(aux.izquierdo), altura(aux.derecho)) + 1;
        int equilibrio = balancear(aux);

        if (equilibrio > 1 && balancear(aux.izquierdo) >= 0) {
            return rotacionSimpleDerecha(aux);
        }
        if (equilibrio > 1 && balancear(aux.izquierdo) < 0) {
            aux.izquierdo = rotacionSimpleIzquierda(aux.izquierdo);
            return rotacionSimpleDerecha(aux);
        }
        if (equilibrio < -1 && balancear(aux.derecho) <= 0) {
            return rotacionSimpleIzquierda(aux);
        }

        if (equilibrio < -1 && balancear(aux.derecho) > 0) {
            aux.derecho = rotacionSimpleDerecha(aux.derecho);
            return rotacionSimpleIzquierda(aux);
        }
        return aux;
    }

    private Nodo minValueNode(Nodo nodo) {
        Nodo aux = nodo;
        while (aux.izquierdo != null) {
            aux = aux.izquierdo;
        }
        return aux;
    }

    public int darNumeroNodo() {
        return numNodo;
    }

    public int numCont() {
        return contador;
    }

    public int darcalcularAltura() {
        return calcularAltura(raiz);
    }

    public void paint(Graphics g, JComponent panel) {
        darUbicacion(panel, raiz);
        if (raiz != null) {
            raiz.paint(g);
        }
    }

    private boolean contiene(Nodo nodo, int dato) {
        if (nodo == null) {
            return false;
        } else {
            return nodo.getDato() == dato || contiene(nodo.getHijoIzq(), dato) || contiene(nodo.getHijoDer(), dato);
        }
    }

    private int darNiveles(Nodo nodo, int suma, int dat) {
        if (nodo.getDato() == dat) {
            return suma;
        } else if (contiene(nodo.getHijoIzq(), dat)) {
            suma = 1 + darNiveles(nodo.getHijoIzq(), suma, dat);
        } else if (contiene(nodo.getHijoDer(), dat)) {
            suma = 1 + darNiveles(nodo.getHijoDer(), suma, dat);
        }
        return suma;
    }

    private void actualizarAltura(Nodo nodo, int cont) {
        if (nodo != null) {
            cont++;
            if (nodo.esHoja()) {
                if (cont > altura) {
                    altura = cont;
                }
            } else {
                actualizarAltura(nodo.getHijoIzq(), cont);
                actualizarAltura(nodo.getHijoDer(), cont);
            }
        }
    }

    //Cuadra la posicion de los nodos
    //da las coordenadas que utiliza el metodo pintar
    private void darUbicacion(JComponent panel, Nodo nodo) {
        if (nodo != null) {
            //Cuantos niveles tiene el arbol
            int n = darNiveles(raiz, 0, nodo.getDato());
            //Comprueba la altura
            this.actualizarAltura(raiz, 0);
            //Hace una division de posiciones
            int quadrant = panel.getHeight() / altura;

            //Da la posicion y del nodo
            int pos_y = quadrant * n;

            //Da la posicion x del nodo           
            int pos_x;

            if (nodo == raiz) {
                pos_x = getIncrementoX(nodo, panel.getWidth()) - 5;
                nodo.setX(pos_x);
            }
            if (nodo.getHijoIzq() != null) {
                pos_x = nodo.getX() - getIncrementoX(nodo.getHijoIzq(), panel.getWidth());
                nodo.getHijoIzq().setX(pos_x);
            }
            if (nodo.getHijoDer() != null) {
                pos_x = nodo.getX() + getIncrementoX(nodo.getHijoDer(), panel.getWidth());
                nodo.getHijoDer().setX(pos_x);
            }

            //Asigna posicion a los hijos
            if (nodo.getHijoIzq() != null) {
                darUbicacion(panel, nodo.getHijoIzq());
            }
            if (nodo.getHijoDer() != null) {
                darUbicacion(panel, nodo.getHijoDer());
            }

            //Asigna posicion al padre
            nodo.setY(pos_y);
        }
    }

    private int getIncrementoX(Nodo nodo, int ancho) {
        int n = darNiveles(raiz, 0, nodo.getDato());
        //Asigna el numero de nodos por nivel
        int dos_n = (int) Math.pow(2, n);
        //Calcula la posicion del nodo      
        return (int) ((ancho / dos_n) / 2);
    }
}
