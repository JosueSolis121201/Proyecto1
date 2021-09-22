package proyecto_1;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import proyecto_1.vistas.VistaVendedor;

public class Proyecto_1 {

    public static Sucursales sucursal[] = new Sucursales[50];
    public static Productos[] producto = new Productos[200];
    public static Clientes[] cliente = new Clientes[100];
    public static Vendedores[] vendedor = new Vendedores[400];
    public static Ventas[] venta = new Ventas[1000];

    public static Object cargarDatos(String nombre) {
        try {
            //ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(""));
            //serial.
            ObjectInputStream serialEntrada = new ObjectInputStream(new FileInputStream(nombre));
            return serialEntrada.readObject();
        } catch (Exception ex) {
            System.out.println("No hay datos que cargar");
            return null;
        }
    }
    
    public static void descargarDatos(String nombre,Object guardar) {
        try {
            ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(nombre));
            serial.writeObject(guardar);
        } catch (Exception ex) {
            System.out.println("Error al serializar");
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        //cargarDatos("m1");
        
        //sucursal[0] = new Sucursales(1,"","","",1);
        //descargarDatos("sucursal",sucursal);
        
        Sucursales[] lecturaSucursal = (Sucursales[])cargarDatos("sucursal");
        if(lecturaSucursal!=null)
            sucursal = lecturaSucursal;
        
        
        Productos[] lecturaProductos = (Productos[])cargarDatos("producto");
        if(lecturaProductos!=null)
            producto = lecturaProductos;
        
        
        Clientes[] lecturaClientes = (Clientes[]) cargarDatos("cliente");
        if (lecturaClientes != null) {
            cliente = lecturaClientes;
        }
        
        Vendedores[] lecturaVendedor = (Vendedores[]) cargarDatos("vendedor");
        if (lecturaVendedor != null) {
            vendedor = lecturaVendedor;
        }
        
        
             Ventas[] lecturaVentas = (Ventas[]) cargarDatos("venta");
        if (lecturaVentas != null) {
            venta = lecturaVentas;
        }
                



        /*producto = new Productos[200];
        cliente = new Clientes[100];
        vendedor = new Vendedores[400];
        venta = new Ventas[1000];*/
        
        
        VistaVendedor adminPrueba = new VistaVendedor();
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
