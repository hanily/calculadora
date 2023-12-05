/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rhani
 */
public class Despesa_Categoria {
    private int idDespesa;
    private int idCategoria;

    public Despesa_Categoria(int idDespesa, int idCategoria) {
        this.idDespesa = idDespesa;
        this.idCategoria = idCategoria;
    }

    public Despesa_Categoria() {
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
}
