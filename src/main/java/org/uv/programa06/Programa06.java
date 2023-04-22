/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package org.uv.programa06;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.uv.Entyties.*;
import org.uv.DAO.*;
/**
 *
 * @author juand
 */
public class Programa06 {

    public static void main(String[] args) {
        
        DAOProducto daoPro=new DAOProducto();
        
        Producto p1=new Producto(1,"Sabritas saladas 250g", 7.50f, 10);
        //daoPro.create(p1);
        Producto p2=new Producto(2,"Jugo Boing fresa", 10f, 10);
        //daoPro.create(p2);
        Producto p3=new Producto(3,"Maiz", 5.5f, 100);
        //daoPro.create(p3);
        Producto p4=new Producto(4,"Frijol", 22.5f, 20);
        //daoPro.create(p4);
        
        DAOVenta daoVen=new DAOVenta();
        Venta v1=new Venta(1);
        
        List<Detalle_Venta> LVD=new ArrayList<Detalle_Venta>();
        DAOVenta_Detalle daoVD=new DAOVenta_Detalle(); 
        Detalle_Venta DV1=new Detalle_Venta(1, v1, p1, 2f);
        /*Cuando se realiza un guardado de un detalle de venta lo mejor seria que el stock del producto disminuyera*/
        Detalle_Venta DV2=new Detalle_Venta(2, v1, p2, 2f);
        LVD.add(DV1);
        LVD.add(DV2);
        
        v1.setDetalle_venta(LVD);
       v1.calculartotal();
        
        
        daoVen.create(v1);
        //daoVD.create(DV1);
        //daoVD.create(DV2);
        
        //Ahora veremos lo datos guardados en una venta
        v1=daoVen.findbyID(1);
        if (v1!=null){
            System.out.println("Venta no: "+v1.getId_venta()+" Fecha: "+v1.getFecha());
            List<Detalle_Venta> LDV=v1.getDetalle_venta();
            if(LDV!=null){
                System.out.println("ID_Detalle:         Descripcion:        Catidad:        Precio_Unitario:        Subtotal:");
                for(Detalle_Venta DV:LDV){
                    /*Esta parte se puede hacer mediante un DTO que nos traiga solo los valores requeridos de la BD, evitando asi
                    * la necesidad de llamar a los demas objetos
                    */
                    System.out.println(DV.getId_detalle_venta()+"       "+DV.getProducto().getDescripcion()+
                            "       "+DV.getCantidad()+"        "+DV.getPrecio_venta()+"        "+DV.getSubtotal());
                }
                System.out.println("Total: "+v1.getTotal());
            }else{
                Logger.getLogger(DAOVenta.class.getName()).log(Level.INFO, "La venta no cuenta con ningun dealle");
            }
        }else{
            Logger.getLogger(DAOVenta.class.getName()).log(Level.INFO, "El id de venta ingresado no existe.");      
        }
        
        /*Ahora borraremos un Producto
        * Tomemos en cuenta que al haber restricciones de llaves foraneas podria provocar una excepcion
        * por lo que la configuracion Delee on cascade estaria mejor.
        */
        /*boolean bandera=daoPro.delete(3);
        if (bandera==true){
            Logger.getLogger(DAOVenta.class.getName()).log(Level.INFO, "El producto se ha borrado con exito.");
        }else{
            Logger.getLogger(DAOVenta.class.getName()).log(Level.INFO, "El id del producto ingresado no existe.");
        }*/
        
        /*boolean bandera= daoVen.delete(1);
        if(bandera==true){
            Logger.getLogger(DAOVenta.class.getName()).log(Level.INFO, "El producto se ha borrado con exito.");
        }else{
            Logger.getLogger(DAOVenta.class.getName()).log(Level.INFO, "El id del producto ingresado no existe.");
        }*/
        
        /*Detalle_Venta DV3=new Detalle_Venta(3, v1, p4, 2f);
        v1.setDetalle_venta(DV3);
        v1.calculartotal();
        daoVen.update(v1, 1);*/
        
    }
}
