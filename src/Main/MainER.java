package Main;

import Controlador.AgregarCuentasERCtrl;
import Vista.AgregarCuentasER;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainER {
    
    private static AgregarCuentasER view;
    private static AgregarCuentasERCtrl ERCtrl;
    
    public static void main(String[] args) {
        initComps();
    }
    
    private static void initComps(){
        view = new AgregarCuentasER();
        ERCtrl = new AgregarCuentasERCtrl(view);
        
        setLookAndFeel();
    }
    
    private static void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }
    }
}