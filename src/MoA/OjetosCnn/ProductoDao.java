
package MoA.OjetosCnn;

import MoA.Conexion.Conexion;
import ViA.BotonConfigUsuario.VistaPConfUsuario.AgregarProductos.VAgregarProducto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductoDao {
    
    public void agregarProducto(VAgregarProducto a){
        /*
        try {
            //Conversion del texto fecha a tipo fecha
            
            java.util.Date fecha=new SimpleDateFormat("dd/MM/yy").parse(a.getFecha_Agregar_P().getText());
        } catch (ParseException ex) {
            System.out.println("Hay un error al convertir la fecha");
            ex.printStackTrace();
        }
         */   //Fin conversion
        String INSERTAR_PRODUCTO_SQL="INSERT INTO productos(Nombre_Producto, Precio, Ganancia, Distribuidor, Codigo_De_Barras, ITBIS_ADICIONAL,"
                + " NOMBRE_ITEBIS_ADICIONAL, Fecha_De_Caducidad, Max, Min, Detalles) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        Connection cnn=null;
        
        PreparedStatement stmt=null;
        
        
        
        try {
            
            
            
            cnn=Conexion.getConexion();
            stmt=cnn.prepareStatement(INSERTAR_PRODUCTO_SQL);
            stmt.setString(1, a.getNombre_Agregar_P().getText());
            stmt.setString(2, a.getPrecio_Agregar_P().getText());
            stmt.setString(3, a.getGanancia_Agregar_P().getText());
            stmt.setString(4, a.getDistribuidor_Agregar_P().getText());
            stmt.setString(5, a.getCodigo_Agregar_P().getText());
            stmt.setString(6, a.getItebis_Agregar_P().getText());
            stmt.setString(7, a.getNombre_Itebis_Adicional().getText());
            stmt.setString(8, a.getFecha_Agregar_P().getText());
            stmt.setString(9, a.getMin_Agregar_P().getText());
            stmt.setString(10, a.getMax_Agregar_P().getText());
            stmt.setString(11, a.getDetalles_Agregar_P().getText());
            
            stmt.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Hay un fallo en la primera clausula de agregar productos");
            ex.printStackTrace(System.out);
        } catch (SQLException ex) {
            System.out.println("Hay un fallo en la segunda clausula de gregar productos");
            ex.printStackTrace(System.out);
        } 
        
        System.out.println("Agregado");
        
    }
    
    
}
