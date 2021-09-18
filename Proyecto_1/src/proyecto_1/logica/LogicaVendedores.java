/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1.logica;

import proyecto_1.Proyecto_1;
import proyecto_1.datos.Vendedores;

/**
 *
 * @author Norki
 */
public class LogicaVendedores {
    
public boolean loginAdmin(String codigo,String contraseña){
    boolean resultado=false;

    if(codigo.equals("admin") && contraseña.equals("admin")){

        resultado=true;
    }else{
        resultado=false;
    }
        return resultado;
}



public boolean loginUsuario(String codigo,String contraseña){
    boolean resultado=false;
    for(int i=0;i<Proyecto_1.vendedor.length;i++){
        Vendedores vendedorActual=Proyecto_1.vendedor[i];
        if(vendedorActual!=null){
           if(codigo.equals(vendedorActual.getCodigo()) && contraseña.equals(vendedorActual.getPassword())){
               
               resultado=true;
           }else{
               
               resultado=false;
           }
           }
        }
    return resultado;
}   
}
