/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaChoferes.model.src.objetoAccesoDatos;

import com.sistemaChoferes.model.src.conexionBaseDatos.Conexion;
import java.sql.Connection;

/**
 *
 * @author icruz
 */
public class ModificacionesDAO {
    private Connection connection;
    
    public ModificacionesDAO(){
        connection = Conexion.conectar();
    }
    
}