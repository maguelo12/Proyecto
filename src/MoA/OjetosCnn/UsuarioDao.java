
package MoA.OjetosCnn;

import CoA.Tablas.TablaUsuario;
import MoA.Conexion.Conexion;
import ViA.BotonConfigUsuario.VistaPConfUsuario.VentanaConfUsuarios.ConfUsuarios;
import ViA.BotonConfigUsuario.VistaPConfUsuario.VentanaConfUsuarios.VAgregarUsuario.VAgregarUsuario;
import ViA.BotonConfigUsuario.VistaPConfUsuario.VentanaConfUsuarios.VEliminarUsuario.ConfEliminar;
import ViA.BotonConfigUsuario.VistaPConfUsuario.VentanaConfUsuarios.VEliminarUsuario.VEliminarUsuario;
import java.sql.*;
import ViA.Loggin.VistaLoggin;
import ViA.VPrincipal.VentanaPrincipal;
//import java.awt.List; Esta pinche puta verg4 no va "=.=;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.*;

public class UsuarioDao {
   
    private  final String ConfirmarUsuario="SELECT * FROM usuarios WHERE Usuario=? AND Clave=?";
    
    private final String RegistarUsaurio="INSERT INTO Usuarios(Nombre, Apellido, Puesto, Permisos, Usuario, Clave) values (?, ?, ?, ?, ?, ?)";
    
    private VistaLoggin VistaLoggin;
    
    
    
    
    //Confirmar usuario del loggin en la base de datos
    public void ConfirmarUsuario(VistaLoggin vs){
        
        
        PreparedStatement stmt=null;
        ResultSet rs=null;
        Connection cnn=null;
        
        //String pass=new String(vs.getTxtpass().getText());
        
        
        
        try {
            cnn=Conexion.getConexion();
            stmt=cnn.prepareStatement(ConfirmarUsuario);
            
            stmt.setString(1, vs.getTxtUsuario().getText());
            stmt.setString(2, vs.getTxtpass().getText());
            rs=stmt.executeQuery();
                
                if(rs.next()){
                
                vs.setVisible(false);
                
                VentanaPrincipal v=new VentanaPrincipal();
                
                v.setVisible(true);
                }else{
                    
                    JOptionPane.showMessageDialog(null, "Chequea bien, pendejo!");
                    
                }
                
              
           
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Hay un error en lo primero");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Hay un error en la segunda cosa :v");
        }
        
        
        
    }
    
    

    
    public List listar(){
        
         String SELECT_SQL ="SELECT * from usuarios ORDER BY Nombre ASC";
        
        Connection cnn=null;
        
        PreparedStatement stmt=null;
        
        ResultSet rs=null;
                
        List<Usuario> datos=new ArrayList<>();
        
        try {
            cnn=Conexion.getConexion();
            stmt=cnn.prepareStatement(SELECT_SQL);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                
                Usuario user=new Usuario();
                
                user.setIdUsuario(rs.getInt(1));
                user.setNombre(rs.getString(2));
                user.setApellido(rs.getString(3));
                user.setPuesto(rs.getString(4));
                user.setPermisos(rs.getString(5));
                user.setUsuario(rs.getString(6));
                user.setClave(rs.getString(7));
                
                datos.add(user);
                
            }
            
            
            
            //return datos;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Hay un pinche fallo por aqui arriba en el select");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Hay un fallo en la segunda cosa del select");
        }
        
        return datos;
    }
    
    
    //Aqui :v agregar Usuario a la base de datos
    
    public void registrarUsuario(VAgregarUsuario us){
        
        Connection cnn=null;
        
        PreparedStatement stmt=null;
        
        try {
            cnn=Conexion.getConexion();
            stmt=cnn.prepareStatement(RegistarUsaurio);
            
            stmt.setString(1, us.getTxtNombreUsuario().getText());
            stmt.setString(2, us.getTxtApellidoUsuario().getText());
            stmt.setString(3, us.getTxtPuestoUsuario().getText());
            stmt.setString(4, us.getTxtPermisoUsuario().getText());
            stmt.setString(5, us.getTxtUsuario().getText());
            stmt.setString(6, us.getTxtUsuarioClave().getText());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registrado!");
            
            TablaUsuario tabla=new TablaUsuario();
            
            ConfUsuarios user=new ConfUsuarios();
            
            tabla.listarUsuario(user.getTablaUser());
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Hay un fallo en la primera clausula del registroUsuario");
            ex.printStackTrace(System.out);
            
        } catch (SQLException ex) {
            System.out.println("Hay un fallo en la segunda clausula del registroUsuario");
            ex.printStackTrace(System.out);
            
        }
        
    }
    
    
    public void listarUserEliminar(VEliminarUsuario vs, ConfEliminar cf){
        
        String SELECTSQL="SELECT * FROM usuarios WHERE idusuarios=?";
        
        Connection cnn=null;
        
        PreparedStatement stmt=null;
        
        ResultSet rs=null;
        
        
        
        try {
            cnn=Conexion.getConexion();
            stmt=cnn.prepareStatement(SELECTSQL);
            stmt.setString(1, vs.getTxtIdUsuario().getText());
            rs=stmt.executeQuery();
            
            //vs.getTxtIdUsuario().getText()
            
            
            
            if(rs.next()){
                
                cf.setVisible(true);
                
                cf.getTxtId().setText(rs.getString(1));
                cf.getTxtNombre().setText(rs.getString(2));
                cf.getTxtApellido().setText(rs.getString(3));
                cf.getTxtApuesto().setText(rs.getString(4));//La diferencia de la variable "Puesto con el patron OJO"
                cf.getTxtUsuario().setText(rs.getString(6));

                System.out.println("Si hay un usuario");
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "Este usuario no se encuentra");
                
            }
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
            System.out.print("Hay un error en la primera clausula de 'listarUserEliminar' ");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.print("Hay un error en la segunda clausual de 'listarUserEliminar' ");
        }
        
    }
    
   public void eliminarUsuario(VEliminarUsuario vs, ConfEliminar cf){
       
       String SQLEDELETE="DELETE FROM usuarios where idusuarios=?";
       
       
       
       Connection cnn=null;
       PreparedStatement stmt=null;
       
        try {
            cnn=Conexion.getConexion();
            stmt=cnn.prepareStatement(SQLEDELETE);
            stmt.setString(1, cf.getTxtId().getText());
            
            stmt.executeUpdate();
            
            System.out.println(cf.getTxtId().getText());
            
            JOptionPane.showMessageDialog(null, "Usuario eliminado!");
            
            cf.setVisible(false);
            vs.setVisible(false);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Hay un error en la primera clausula de eliminar usuario");
            ex.printStackTrace(System.out);
            
        } catch (SQLException ex) {
            System.out.println("Hay un error en la segunda clausula de eliminar usuario");
            ex.printStackTrace(System.out);
        }
       
       
       
       
   } 
    
    
 
    
}//Finito :v (Reference)

