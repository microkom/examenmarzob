/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenmarzob;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author german
 */
public class Producto {

    private int idProd;
    private String nombreProd;
    private int idProv;
    private int idCateg;
    private String nombreProveedor;
    private String nombreCategoria;
    private int precio;
    private int existencias;
    private int cantidadVendida;

    public Producto(int idProd, String nombreProd, int idProv, int idCateg, int precio, int existencias) {
        this.idProd = idProd;
        this.nombreProd = nombreProd;
        this.idProv = idProv;
        this.idCateg = idCateg;
        this.nombreProveedor = nombreProveedor;
        this.nombreCategoria = nombreCategoria;
        this.precio = precio;
        this.existencias = existencias;
    }

    public int getIdProd() {
        return idProd;
    }

    public void getSetNombreProveedor() {

        Conexion login = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            //conexión a la base de datos
            con = login.conectar();

            stmt = con.prepareStatement("select Nombre from Proveedores WHERE IdProveedor=?");
            stmt.setInt(1, this.idProv);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.nombreProveedor = rs.getString(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            login.desconectar(con);
        }
    }

    public void getSetNombreCategoria() {

        Conexion login = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            //conexión a la base de datos
            con = login.conectar();

            stmt = con.prepareStatement("select NomCategoria from Categorias WHERE IdCategoria=?");
            stmt.setInt(1, this.idCateg);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.nombreCategoria = rs.getString(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            login.desconectar(con);
        }

    }

    public String getNombreProd() {
        return nombreProd;
    }

    public int getPrecio() {
        return precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public int getIdProv() {
        return idProv;
    }

    public int getIdCateg() {
        return idCateg;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public void setIdCateg(int idCateg) {
        this.idCateg = idCateg;
    }

    @Override
    public String toString() {
        return "\n\tIdProducto: " + idProd
                + "\n\tNombre Producto: " + nombreProd
                + "\n\tNombre Proveedor: " + nombreProveedor
                + "\n\tNombre Categoria: " + nombreCategoria
                + "\n\tPrecio: " + precio
                + "\n\tExistencias: " + existencias 
                + "\n\tCantidad Vendida: " + cantidadVendida +"\n";
    }

    public void getSetCantidadVendida() {

        Conexion login = new Conexion();
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            //conexión a la base de datos
            con = login.conectar();

            stmt = con.prepareStatement("SELECT sum(l.Cantidad) FROM "
                    + "lineaspedido l INNER JOIN productos p ON l.Producto = p.IdProducto WHERE p.IdProducto=?"
                    + " GROUP BY p.NomProducto");
            stmt.setInt(1, this.idProd);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.cantidadVendida = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            login.desconectar(con);
        }
    }
}
