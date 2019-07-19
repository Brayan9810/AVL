package interfaz;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import logica.ArbolAVL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InterfazArbolAVL extends JFrame {

    private final ArbolAVL arbolAVL;
    private final PanelCentral panelCentral;
    private final JButton btnAñadir;
    private final JButton btnLimpiar;
    private final JButton btnSalir;
    private final JButton btnEliminar;
    private final JLabel jLabel1;
    private final JPanel pnlComandos;
    private final JPanel pnlVisorNodoAVL;
    private final JTextField txtIngresoNodo;
    private final PanelAVL comunicacionAVL;

    public InterfazArbolAVL() {
        // Crea la clase principal
        arbolAVL = new ArbolAVL();
        //crea un arbol inicial e intenta insertar 50 numeros entre 0 y 100
        Random rnd = new Random();
                
        setTitle("Arbol AVL");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        // Construye la forma
        setLayout(new BorderLayout());
        setSize(1440, 850);
        panelCentral = new PanelCentral(this);
        add(panelCentral, BorderLayout.EAST);

        pnlVisorNodoAVL = new JPanel();
        pnlComandos = new JPanel();
        txtIngresoNodo = new JTextField();
        jLabel1 = new JLabel();
        btnAñadir = new JButton();
        btnLimpiar = new JButton();
        btnSalir = new JButton();
        btnEliminar=new JButton();

        pnlVisorNodoAVL.setLayout(null);
        add(pnlVisorNodoAVL, BorderLayout.CENTER);
        pnlVisorNodoAVL.setBounds(110, 80, 1200, 615);

        pnlComandos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlComandos.setLayout(null);
        pnlComandos.add(txtIngresoNodo);
        txtIngresoNodo.setBounds(130, 10, 110, 30);

        jLabel1.setText("Ingrese un numero:");
        pnlComandos.add(jLabel1);
        jLabel1.setBounds(10, 10, 130, 30);

        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        pnlComandos.add(btnAñadir);
        btnAñadir.setBounds(250, 10, 80, 30);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        pnlComandos.add(btnLimpiar);
        btnLimpiar.setBounds(335, 10, 80, 30);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlComandos.add(btnSalir);
        btnSalir.setBounds(510, 10, 80, 30);
        
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
            
        pnlComandos.add(btnEliminar);
        btnEliminar.setBounds(420, 10, 80, 30);

        add(pnlComandos);
        pnlComandos.setBounds(10, 11, 480, 47);

        comunicacionAVL = new PanelAVL(arbolAVL);
        comunicacionAVL.setBounds(0, 0, pnlVisorNodoAVL.getWidth(), pnlVisorNodoAVL.getHeight());
        pnlVisorNodoAVL.add(comunicacionAVL);
        for(int i=0;i<40;i++){
            /*Integer elemento=(int)(rnd.nextDouble()*100);
            int numero=(int) elemento;*/
            comunicacionAVL.añadirNodo(i);
            String[] tablaAVL=darDatosTablaAVL();
            panelCentral.actualizarTablaAVL(tablaAVL);
        }      
    }
    String[] darDatosTablaAVL() {
        String[] datos = {Integer.toString(arbolAVL.darNumeroNodo()),
            Integer.toString(arbolAVL.balancearRaiz()),
            Integer.toString(arbolAVL.numCont())
        };
        return datos;
    }
    void actualizarTabla() {
        String[] tablaAVL = darDatosTablaAVL();
        panelCentral.actualizarTablaAVL(tablaAVL);
    } 
    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int nodo = Integer.parseInt(txtIngresoNodo.getText());
            comunicacionAVL.añadirNodo(nodo);
            comunicacionAVL.repaint();
            txtIngresoNodo.setText("");
            System.out.println("\n");
            arbolAVL.imprimirPorNiveles();
            actualizarTabla();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
            try {
            int nodo = Integer.parseInt(txtIngresoNodo.getText());
            comunicacionAVL.eliminarNodo(nodo);
            txtIngresoNodo.setText("");
            comunicacionAVL.repaint();            
            System.out.println("\n");
            //actualizarTabla();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new InterfazArbolAVL().setVisible(true);
    }
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    public static void main(String[] args) {
        InterfazArbolAVL interfaz = new InterfazArbolAVL();
        interfaz.setVisible(true);        
    }
}
