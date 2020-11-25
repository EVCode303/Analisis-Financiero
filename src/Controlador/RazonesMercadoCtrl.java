package Controlador;

import Vista.RazonesMercado;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.awt.Toolkit;

public class RazonesMercadoCtrl extends KeyAdapter {

    private RazonesMercado ra;

    public RazonesMercadoCtrl(RazonesMercado ra) {
        this.ra = ra;
        ra.setVisible(true);
        initEvents();
    }

    private void initEvents() {
        ra.tfDenUtilidad.addKeyListener(this);
        ra.tfDenRazon.addKeyListener(this);
        ra.tfNumRazon.addKeyListener(this);
        ra.tfNumUtilidad.addKeyListener(this);
    }

    private void validateNumbers(KeyEvent e) {
        char validar = e.getKeyChar();

        if (Character.isLetter(validar)) {
            Toolkit.getDefaultToolkit().beep();
            e.consume();

            JOptionPane.showMessageDialog(ra, "Ingresar solo números");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(ra.tfDenUtilidad)) {
            String resultado = calcularValor(0);
            ra.lblResultUtil.setText(resultado);
        }

        if (e.getSource().equals(ra.tfDenRazon)) {
            String resultado = calcularValor(1);
            ra.lblResultRazon.setText(resultado);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        validateNumbers(e);
    }

    private String calcularValor(int opc) {
        Double num = 0.0, den = 0.0;
        switch (opc) {
            case 0:
                if (ra.tfNumUtilidad.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(ra, "Complete el numerador");
                    return "";
                }
                try {
                    num = Double.parseDouble(ra.tfNumUtilidad.getText());
                    den = Double.parseDouble(ra.tfDenUtilidad.getText());
                } catch (NumberFormatException e) {
                }
                break;
            case 1:
                if (ra.tfNumRazon.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(ra, "Complete el numerador");
                    return "";
                }
                try {
                    num = Double.parseDouble(ra.tfNumRazon.getText());
                    den = Double.parseDouble(ra.tfDenRazon.getText());
                } catch (NumberFormatException e) {
                }
                break;
        }

        return String.format("%.4f", num / den);
    }

}
