package interfaz;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Es el panel que muestra la tabla con los datos de las rotaciones realizadas
 * al insertar un nodo , el dato del nodo insertado y el balanceo de la raiz.
 */
public class PanelTabla extends JPanel {

    // --------------------------------------------------------
    // Atributos
    // --------------------------------------------------------
    /**
     * Panel principal de este panel
     */
    private PanelCentral principal;
    private DefaultTableModel modeloTabla;
    private final JTable tabla;


    public PanelTabla(PanelCentral principalP) {

        principal = principalP;
        setSize(200, 200);

        modeloTabla = new DefaultTableModel();
        tabla = new JTable(modeloTabla) {
            @Override
            public boolean isCellEditable(int col, int row) {
                return false;
            }
        };
        setLayout(new BorderLayout());

        modeloTabla.addColumn("Nodo insertado");
        modeloTabla.addColumn("Valor balanceo");
        modeloTabla.addColumn("Cantidad de if que verifica");

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(getWidth() * 1 / 14);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(getWidth() * 3 / 14);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(getWidth() * 3 / 14);

        JScrollPane panelScroll = new JScrollPane(tabla);
        add(panelScroll, BorderLayout.CENTER);
    }

    void actualizarTabla(String[] tabla) {
        modeloTabla.addRow(tabla);
    }
}
