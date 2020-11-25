package Main;

import Controlador.RazonesMercadoCtrl;
import Vista.RazonesMercado;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainRa {
    
    private static RazonesMercadoCtrl ctrl;
    private static RazonesMercado ra;
    
    public static void main(String[] args) {
        initComps();
    }
    
    private static void initComps(){
        ra = new RazonesMercado();
        ctrl = new RazonesMercadoCtrl(ra);
        
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