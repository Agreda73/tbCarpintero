package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.AgrgarCarpintero;
import vista.AgregarCarpintero;

public class ctrlCarpintero  implements MouseListener{
        //1-Mandar a llamar a las otras capas
    private AgrgarCarpintero modelo;
    private AgregarCarpintero vista;
 
 
       //2- crear el constructor 
    public ctrlCarpintero(AgrgarCarpintero modelo, AgregarCarpintero vista){
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnAgrega.addMouseListener(this);
        modelo.Mostrar(vista.jtCarpintero);
        vista.btnActualiza.addMouseListener(this);
        vista.jtCarpintero.addMouseListener(this);
        vista.btnElimina.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.btnAgrega){
            modelo.setNombre(vista.txtNombr.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEda.getText()));
            modelo.setPeso(Integer.parseInt(vista.txtPes.getText()));
            modelo.setCorreo(vista.txtCorre.getText());

            
            modelo.Guardar();   
            modelo.Mostrar(vista.jtCarpinteros);
        }
        
        if(e.getSource() == vista.btnElimina){
            modelo.Eliminar(vista.jtCarpinteros);
            modelo.Mostrar(vista.jtCarpinteros);
        }
        
        if(e.getSource() == vista.jtCarpintero){
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnActualiza){
            modelo.setNombre(vista.txtNombr.getText());
            modelo.setEdad(Integer.parseInt(vista.txtEda.getText()));
            modelo.setPeso(Integer.parseInt(vista.txtPes.getText()));
            modelo.setCorreo(vista.txtCorreo.getText());

            
            modelo.Guardar();   
            modelo.Mostrar(vista.jtCarpinteros);
        }
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
