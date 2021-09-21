
package proyecto_1;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto_1.datos.Clientes;
import proyecto_1.datos.Productos;
import proyecto_1.datos.Sucursales;
import proyecto_1.datos.Vendedores;
import proyecto_1.datos.Ventas;
import proyecto_1.logica.LogicaSucursales;
import proyecto_1.vistas.VistaAdmin;
import proyecto_1.vistas.VistaAdminActualizarSucursal;
import proyecto_1.vistas.VistaAdminActualizarProducto;
import proyecto_1.vistas.VistaAdminActualizarVendedor;
import proyecto_1.vistas.VistaAdminAutenticacion;
import proyecto_1.vistas.VistaAdminCrearSucursal;
import proyecto_1.vistas.VistaAdminCrearProducto;
import proyecto_1.vistas.VistaAdminCrearVendedor;




public class Proyecto_1 {
    

   
    public  static Sucursales sucursal[]= new Sucursales [50];
    public  static Productos[] producto= new Productos [200];
    public  static Clientes[] cliente= new Clientes [100];
    public  static Vendedores[] vendedor= new Vendedores [400];
    public  static Ventas[] venta = new Ventas [1000];
    
    
    
    
    
    
    public static void main(String[] args) {
        
        
      VistaAdmin adminPrueba = new  VistaAdmin();
       //VistaAdminAutenticacion adminPrueba1=new VistaAdminAutenticacion();
       //VistaAdminCrear a=new VistaAdminCrear();
       //VistaAdminActualizar a=new VistaAdminActualizar();
           // VistaAdminCrearProducto a=new VistaAdminCrearProducto();
      // VistaAdminActualizarProducto a=new VistaAdminActualizarProducto();
                 // VistaAdminCrearVendedor a=new VistaAdminCrearVendedor();
                 // VistaAdminActualizarVendedor a=new VistaAdminActualizarVendedor();

        

    // Se asocia el documento al OutputStream y se indica que el espaciado entre
    // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
    

    // Se abre el documento.
                 
        
    }
    
}
