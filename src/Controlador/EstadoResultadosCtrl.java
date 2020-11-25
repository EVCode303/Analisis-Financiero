/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CuentasER;
import Vista.AgregarCuentasER;
import Vista.EstadoResultados;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Eduardo Varela
 */
public class EstadoResultadosCtrl extends MouseAdapter{

    private EstadoResultados ER;
    private CuentasER cuentas;
    private AgregarCuentasER Acc;
    
    public EstadoResultadosCtrl(EstadoResultados ER, CuentasER cuentas, AgregarCuentasER Acc) {
        this.ER = ER;
        this.cuentas = cuentas;
        this.Acc = Acc;
        setAccountsValues();
        ER.setVisible(true);
        ER.btnRegresar.addMouseListener(this);
    }

    private void setAccountsValues() {
        int utilidadBruta, gastosOperativos, utilidadEjercicio;
        ER.lblEmpresa.setText(cuentas.getNombreEmpresa());
        ER.lblPeriodo.setText(cuentas.getPeriodo());
        ER.lblVentas.setText("C$ "+cuentas.getVentas());
        ER.lblCostosVentas.setText("C$ "+cuentas.getCostoVentas());
        
        utilidadBruta = Integer.parseInt(cuentas.getVentas()) - Integer.parseInt(cuentas.getCostoVentas());
        
        ER.lblUtilidadBruta.setText("C$ "+String.valueOf(utilidadBruta));
        ER.lblGastosAdmin.setText("C$ "+cuentas.getGastosAdmin());
        ER.lblGastosVentas.setText("C$ "+cuentas.getGastosVentas());
        
        gastosOperativos = Integer.parseInt(cuentas.getGastosAdmin()) + Integer.parseInt(cuentas.getGastosVentas());
        utilidadEjercicio = utilidadBruta - gastosOperativos;
        
        ER.lblGastosOperación.setText("C$ "+String.valueOf(gastosOperativos));
        ER.lblUtilidadEjercicio.setText("C$ "+String.valueOf(utilidadEjercicio));
    }
    
    @Override
    public void mouseClicked (MouseEvent e) {
        Acc.setVisible(true);
        ER.setVisible(false);
    }
}
