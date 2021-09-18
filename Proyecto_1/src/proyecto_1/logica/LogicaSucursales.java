package proyecto_1.logica;

import proyecto_1.Proyecto_1;
import proyecto_1.datos.Sucursales;

public class LogicaSucursales {

    public void exportarListadoPdf() {

    }

    public void listadoOficial() {

    }

    public void cargaMasiva() {

    }
    
    public boolean unico(int codigo) {
        for (int i = 0; i < Proyecto_1.sucursal.length; i++) {

            if (Proyecto_1.sucursal[i] != null) {
                if (Proyecto_1.sucursal[i].getCodigo() == codigo) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean crear(int codigo, String nombre, String direccion, String correo, int telefono) {
        Sucursales nuevaSucursal = new Sucursales(codigo, nombre, direccion, correo, telefono);

        if (this.unico(codigo) == false) {
            return false;
        }

        for (int i = 0; i < Proyecto_1.sucursal.length; i++) {

            if (Proyecto_1.sucursal[i] == null) {
                Proyecto_1.sucursal[i] = nuevaSucursal;
                return true;
            }
        }
        return false;
    }

    

    public boolean actualizar(int codigo, String nombre, String direccion, String correo, int telefono) {

        for (int i = 0; i < Proyecto_1.sucursal.length; i++) {

            if (Proyecto_1.sucursal[i] != null) {
                if (Proyecto_1.sucursal[i].getCodigo() == codigo) {

                    Proyecto_1.sucursal[i].setNombre(nombre);
                    Proyecto_1.sucursal[i].setDireccion(direccion);
                    Proyecto_1.sucursal[i].setCorreo(correo);
                    Proyecto_1.sucursal[i].setTelefono(telefono);

                    return true;

                }

            }

        }

        return false;

    }

    public boolean eliminar(int codigo) {

        for (int i = 0; i < Proyecto_1.sucursal.length; i++) {

            if (Proyecto_1.sucursal[i] != null) {

                if (codigo == Proyecto_1.sucursal[i].getCodigo()) {

                    Proyecto_1.sucursal[i] = null;
                    return true;

                }
            }
        }
        return false;
    }

}
