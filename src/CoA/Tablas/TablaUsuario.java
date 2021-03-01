/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoA.Tablas;

import MoA.OjetosCnn.Usuario;
import MoA.OjetosCnn.UsuarioDao;
import ViA.BotonConfigUsuario.VistaPConfUsuario.VentanaConfUsuarios.ConfUsuarios;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class TablaUsuario {
    
    
    
    
        DefaultTableModel model=new DefaultTableModel();
        
        UsuarioDao dao=new UsuarioDao();
        
        ConfUsuarios ConfUsuarios=new ConfUsuarios();
        
        public TablaUsuario(){
            
            listarUsuario(ConfUsuarios.getTablaUser());
            
        }
    
    
    public void listarUsuario(JTable tabla){
        
        model=(DefaultTableModel) tabla.getModel();
        
        model.getDataVector().removeAllElements();
        
        tabla.updateUI();
        
        List<Usuario> lista=dao.listar();
        
        Object[] object=new Object[7];
        
        for(int i=0; i<lista.size(); i++){
            
            object[0]=lista.get(i).getIdUsuario();
            object[1]=lista.get(i).getNombre();
            object[2]=lista.get(i).getApellido();
            object[3]=lista.get(i).getPuesto();
            object[4]=lista.get(i).getPermisos();
            object[5]=lista.get(i).getUsuario();
            object[6]=lista.get(i).getClave();
            
            model.addRow(object);
       
        }
        
        ConfUsuarios.getTablaUser().setModel(model);
        
        
        
    }
    
}
