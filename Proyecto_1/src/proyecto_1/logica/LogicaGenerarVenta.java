
package proyecto_1.logica;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import proyecto_1.Proyecto_1;
import proyecto_1.datos.Clientes;
import proyecto_1.datos.ProductoVenta;
import proyecto_1.datos.Productos;
import proyecto_1.datos.Vendedores;
import proyecto_1.datos.Ventas;

public class LogicaGenerarVenta {
    
    public void agregarAVenta(ProductoVenta[] listado,ProductoVenta producto){
        for(int i=0;i<listado.length;i++){
            if(listado[i]==null){
                listado[i] =producto;
                break;
            }
        }
        
        
    }
    public void descargarDatos(String nombre, Object guardar) {
        try {
            ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(nombre));
            serial.writeObject(guardar);
        } catch (Exception ex) {
            System.out.println("Error al serializar");
            System.out.println(ex);
        }
    }
    public DefaultTableModel listadoOficial(Vendedores Actual) {
        String[] header = {"No. Factura",  "nit" , "nombre", "fecha", "total", "Accion"};
        String[][] data = {};
        DefaultTableModel modelo = new DefaultTableModel(data, header);

        
        for (int i = 0; i < Proyecto_1.venta.length; i++) {
            Ventas v = Proyecto_1.venta[i];
            if (Proyecto_1.venta[i] != null) {
                if(v.getVendedor()==null ||(Actual.getCodigo() == v.getVendedor().getCodigo())){

                    String[] fila = {v.getId()+"",v.getClienteFactura().getNit(),
                            v.getClienteFactura().getNombre(),v.getFecha(),v.getTotal()+"", "Visualizar"};
                    modelo.addRow(fila);
                }

            }

        }
        this.descargarDatos("venta", Proyecto_1.venta);

        return modelo;
    }
     
    public int numFactura(){
        int correlativo=1;
        for (int i = 0; i < Proyecto_1.venta.length; i++) {

            if (Proyecto_1.venta[i] != null) {
                if(Proyecto_1.venta[i].getId()>=correlativo){
                    correlativo=Proyecto_1.venta[i].getId()+1;
                }
            }

        }
        return correlativo;
    }
    
    public double total(ProductoVenta[] listado){
        double total=0; 
        for (int i = 0; i <listado.length; i++) {

            if (listado[i] != null) {
                ProductoVenta act = listado[i];
                total= total +act.getProducto().getPrecio()* act.getCantidadVenta();
            }

        }
        return total;
    }
    
    public void crearVenta(Clientes clienteFactura, Vendedores vendedor, ProductoVenta[] listadoProductos,int id, String fecha, double total){
        for(int i=0;i<Proyecto_1.venta.length;i++){
            if(Proyecto_1.venta[i]==null){
               Proyecto_1.venta[i]= new Ventas( clienteFactura,  vendedor,  listadoProductos,  id,  fecha,  total);
               break;
            }
        }
        
    }
    public DefaultTableModel listadoProductos(ProductoVenta[] listado) {
        String[] header = {"codigo", "nombre",  "cantidad", "precio", "subtotal"};
        String[][] data = {};
        DefaultTableModel modelo = new DefaultTableModel(data, header);

        for (int i = 0; i <listado.length; i++) {

            if (listado[i] != null) {
                ProductoVenta act = listado[i];
                String[] fila = {act.getProducto().getCodigo()+"",act.getProducto().getNombre(),act.getCantidadVenta()+"",
                    act.getProducto().getPrecio()+"", act.getProducto().getPrecio()* act.getCantidadVenta() +""  };
                modelo.addRow(fila);

            }

        }

      

        return modelo;
    }
    
    public void filtrarClientes(String nombre, String correo, String nit, String genero, JComboBox combo) {
        combo.removeAllItems();

        for (int i = 0; i < Proyecto_1.cliente.length; i++) {
            Clientes actual = Proyecto_1.cliente[i];
            if (actual != null) {
                if (actual.getNit().contains(nit) || nit.equals("")) {
                    if (actual.getNombre().contains(nombre) || nombre.equals("")) {
                        if (actual.getCorreo().contains(correo) || correo.equals("")) {
                            if (actual.getGenero().toLowerCase().contains(genero.toLowerCase()) || genero.equals("")) {
                                combo.addItem(actual);
                            }
                        }
                    }

                }

            }
        }
    }
    
    public DefaultTableModel filtrarVentas(String factura, String nombre, String nit, String fecha) {
        String[] header = {"No. Factura",  "nit" , "nombre", "fecha", "total", "Accion"};
        String[][] data = {};
        DefaultTableModel modelo = new DefaultTableModel(data, header);
        for (int i = 0; i < Proyecto_1.venta.length; i++) {
            Ventas actual = Proyecto_1.venta[i];
            if (actual != null) {
                if (actual.getClienteFactura().getNit().contains(nit) || nit.equals("")) {
                    if ((actual.getId()+"").contains(factura) || factura.equals("")) {
                        if (actual.getClienteFactura().getNombre().contains(nombre) || nombre.equals("")) {
                            if (actual.getFecha().contains(fecha) || fecha.equals("")) {
                                
                                String[] fila = {actual.getId()+"",actual.getClienteFactura().getNit()+"",
                                actual.getClienteFactura().getNombre(),actual.getFecha(),actual.getTotal()+"",
                                "Visualizar"};
                                modelo.addRow(fila);
                            }
                        }
                    }

                }

            }
        }
        
        return modelo;
    }
    
    public void exportarListadoPdf(int noVenta) {
        
        Ventas ven=null;
        
        for(int i=0;i<Proyecto_1.venta.length;i++){
            if(Proyecto_1.venta[i]!=null){
                if(Proyecto_1.venta[i].getId()== noVenta){
                    ven=Proyecto_1.venta[i];
                    break;
                }
            }
        }
        
        if(ven==null){
            return;
        }
        Document documento = new Document();
        FileOutputStream ficheroPdf = null;
        try {
            ficheroPdf = new FileOutputStream("ReporteFactura"+noVenta+".pdf");
            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
            documento.open();
            documento.add(new Paragraph("Factura",
                    FontFactory.getFont("arial",
                            22,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            documento.add(new Paragraph("Blue Mall",
                    FontFactory.getFont("arial",
                            18,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            
            
            Vendedores emp = ven.getVendedor();
            int caja = 0;
            if(emp!=null)
                caja =emp.getCaja();
            documento.add(new Paragraph("Caja: "+caja ,
                    FontFactory.getFont("arial",
                            18,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            documento.add(new Paragraph("Numero Factura: " + ven.getId(),
                    FontFactory.getFont("arial",
                            18,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            documento.add(new Paragraph("Nit Cliente: " + ven.getClienteFactura().getNit(),
                    FontFactory.getFont("arial",
                            18,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            documento.add(new Paragraph("Nombre Cliente: "+ven.getClienteFactura().getNombre(),
                    FontFactory.getFont("arial",
                            18,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            documento.add(new Paragraph("Fecha: "+ven.getFecha(),
                    FontFactory.getFont("arial",
                            18,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            
            
            documento.add(new Paragraph("\n"));
            PdfPTable tabla = new PdfPTable(6);
            String[] header = {"codigo", "nombre", "descripcion", "cantidad", "precio","subtotal"};
            for (String cada : header) {
                System.out.println(cada);
                tabla.addCell(cada);
            }
            
            ProductoVenta[] listadoProduc =ven.getListadoProductos();
            for (int i = 0; i <listadoProduc .length; i++) {

                if (listadoProduc[i] != null) {
                    Productos pro = listadoProduc[i].getProducto();
                    
                    String[] fila = {pro.getCodigo() + "", pro.getNombre(),
                        pro.getDescripcion(), listadoProduc[i].getCantidadVenta() + "",
                        pro.getPrecio() + "",listadoProduc[i].getCantidadVenta()*pro.getPrecio()+""};

                    for (String cada : fila) {
                        tabla.addCell(cada);

                    }

                }

            }

            documento.add(tabla);
            
            documento.add(new Paragraph("Total: "+ven.getTotal(),
                    FontFactory.getFont("arial",
                            18,
                            Font.ITALIC,
                            BaseColor.GREEN)));
            documento.close();
        } catch (Exception ex) {
            Logger.getLogger(Proyecto_1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
