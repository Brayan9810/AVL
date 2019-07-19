package interfaz;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Es el panel central donde se encuentran los paneles de agregar y la tabla.
 */
public class PanelCentral extends JPanel {

    private final InterfazArbolAVL principal;
    private final PanelTabla panelTablaAVL;

    public PanelCentral(InterfazArbolAVL principalP) {
        principal = principalP;

        // Construye el panel
        setLayout(new BorderLayout());
        setSize(100, 100);

        panelTablaAVL = new PanelTabla(this);
        panelTablaAVL.setBorder(BorderFactory.createTitledBorder("Tabla AVL"));
        add(panelTablaAVL, BorderLayout.NORTH);
    }
    void actualizarTabla() {
        principal.actualizarTabla();
    }

    void actualizarTablaAVL(String[] tabla) {
        panelTablaAVL.actualizarTabla(tabla);
    }

}
