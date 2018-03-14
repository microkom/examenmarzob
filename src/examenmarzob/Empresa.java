/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenmarzob;

import static examenmarzob.Util.writeFile;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author german
 */
public class Empresa {

    private String nombre;
    private ArrayList<Producto> prodList = new ArrayList<Producto>();

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Producto> getProducto() {
        return prodList;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProducto(ArrayList<Producto> producto) {
        this.prodList = producto;
    }

    public void consultaDB() {
        Conexion login = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;

        try {
            //conexión a la base de datos
            con = login.conectar();

            stmt = con.prepareStatement("select * from productos");
            ResultSet rs = stmt.executeQuery();

            Producto product = null;

            while (rs.next()) {
                product = new Producto(rs.getInt("IdProducto"),
                        rs.getString("NomProducto"),
                        rs.getInt("Proveedor"),
                        rs.getInt("Categoria"),
                        rs.getInt("Precio"),
                        rs.getInt("Existencias"));
                prodList.add(product);

            }
            String text = "EMPRESA: " + this.nombre ;
            
            for (Producto obj : prodList){
                obj.getSetNombreCategoria();
                obj.getSetNombreProveedor();
                obj.getSetCantidadVendida();
                text += obj.toString()+"\n";
            }
            
            File fichero1 = new File("Productos.txt");
            writeFile(fichero1, text);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            login.desconectar(con);
        }
    }
}
