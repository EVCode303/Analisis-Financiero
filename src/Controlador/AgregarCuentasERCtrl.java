package Controlador;

import Modelo.CuentasER;
import Vista.AgregarCuentasER;
import Vista.EstadoResultados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AgregarCuentasERCtrl implements ActionListener {

    private AgregarCuentasER view;
    private DefaultTableModel tableModel;
    private DefaultTableCellRenderer render;
    private DefaultComboBoxModel<String> cbxModel;

    public AgregarCuentasERCtrl(AgregarCuentasER view) {
        this.view = view;
        this.view.setVisible(true);
        initEventsAndActions();
    }

    private void initEventsAndActions() {
        // Eventos
        view.btnAgregarCuenta.addActionListener(this);
        view.btnGenerarEstado.addActionListener(this);
        view.btnReiniciar.addActionListener(this);

        // Acciones
        // establecer modelo de datos de la tabla
        initCbx();
        initTable();
    }
    
    private void initCbx() {
        String items[] = { "Ventas", "Costo de ventas", "Gastos de admon", "Gastos de venta" };
        cbxModel = new DefaultComboBoxModel<>(items);
        view.cbxCuenta.setModel(cbxModel);
    }

    private void initTable() {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("No");
        tableModel.addColumn("Cuenta");
        tableModel.addColumn("Saldo");

        view.tbCuentas.setModel(tableModel);

        render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        render.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            view.tbCuentas.getColumnModel().getColumn(i).setCellRenderer(render);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        switch (btn.getText()) {
            case "Agregar cuenta":
                String cuenta, saldo;
                cuenta = (String)view.cbxCuenta.getSelectedItem();
                saldo = view.tfSaldo.getText().trim();
                if (!verificarVacio(cuenta) && !verificarVacio(saldo)) {
                    JOptionPane.showMessageDialog(view, "Complete los campos");
                    return;
                }
                agregarCuenta(cuenta, saldo);
                break;
            case "Generar Estado":
                String nombreEmpresa, periodo;
                nombreEmpresa = view.tfEmpresa.getText().trim();
                periodo = view.tfPeriodo.getText().trim();
                if (!verificarVacio(nombreEmpresa) && !verificarVacio(periodo)) {
                    JOptionPane.showMessageDialog(view, "Complete los campos");
                    return;
                }
                generarEstado(nombreEmpresa, periodo);
                break;
            case "Reiniciar": 
                reiniciar();
                break;
        }
    }

    private boolean verificarVacio(String campo) {
        return !campo.equals("");
    }

    private void agregarCuenta(String cuenta, String saldo) {
        if (!validaciones(0, cuenta) || !validaciones(2, saldo)) {
            JOptionPane.showMessageDialog(view, "Formatos incorrectos");
            return;
        }

        int ultimoNo = 0;
        String seleccionado = "";
        seleccionado = (String)cbxModel.getSelectedItem();
        cbxModel.removeElement(seleccionado);
        
        ultimoNo = tableModel.getRowCount() + 1;
        
        Object[] acc = {ultimoNo, cuenta, saldo};
        tableModel.addRow(acc);
        cleanAndFocus();
    }

    private void cleanAndFocus() {
        view.cbxCuenta.setSelectedItem(0);
        view.tfSaldo.setText("");
        view.cbxCuenta.requestFocus();
    }
    
    private void reiniciar() {
        initTable();
        initCbx();
    }

    private void generarEstado(String nombreEmpresa, String periodo) {
        if (!validaciones(0, nombreEmpresa) || !validaciones(1, periodo)) {
            JOptionPane.showMessageDialog(view, "Formatos del periodo y nombre de la empresa incorrectos");
            return;
        }
        
        if (cbxModel.getSize() > 0) {
            JOptionPane.showMessageDialog(view, "Faltan cuentas por agregar");
            return;
        }

        CuentasER cuentas = new CuentasER();
        cuentas.setVentas(view.tbCuentas.getValueAt(0, 2).toString());
        cuentas.setCostoVentas(view.tbCuentas.getValueAt(1, 2).toString());
        cuentas.setGastosAdmin(view.tbCuentas.getValueAt(2, 2).toString());
        cuentas.setGastosVentas(view.tbCuentas.getValueAt(3, 2).toString());
        cuentas.setNombreEmpresa(view.tfEmpresa.getText().trim());
        cuentas.setPeriodo(view.tfPeriodo.getText().trim());
        EstadoResultadosCtrl er = new EstadoResultadosCtrl(new EstadoResultados(), cuentas, view);
        view.setVisible(false);
    }

    // Validaciones de los campos usando Expresiones regulares
    private boolean validaciones(int opc, String texto) {
        switch (opc) {
            case 0:
                // Validación del nombre de la empresa y nombre de la cuenta
                return texto.matches("^([A-Z|a-z]+)(([ ][A-Z|a-z]+)+)?$");
            case 1:
                // Validación del Periodo
                return texto.matches("^[0-3][0-9]/[0-1][0-9]/[1-2][0-9]{3}$");
            case 2:
                // Validación del saldo de cuenta
                return texto.matches("^([0-9]+)([.][0-9]+)?$");
        }
        return false;
    }
}
