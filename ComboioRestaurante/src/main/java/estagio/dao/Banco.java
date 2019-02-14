/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

/**
 *
 * @author Pereira
 */
public class Banco {
    private static Conexao con;
    private Banco(){} // desabilita a instância de Banco
//    public static Conexao open ()
//    {
////        con = new Conexao("jdbc:postgresql://127.0.0.1.:5432/",
////                          "academia","postgresql","postgresql");
////        return con;
//    }
    public static Conexao getCon()
    {
        return con;
    }    
}