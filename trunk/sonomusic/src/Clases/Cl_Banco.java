/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Pitufina
 */
public class Cl_Banco {
    private int id;
    private String nom_ban;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_ban() {
        return nom_ban;
    }

    public void setNom_ban(String nom_ban) {
        this.nom_ban = nom_ban.toUpperCase();
    }
    
    
}
