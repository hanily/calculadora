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
public class Despesa {
    
    private int id;
    private String descricao;
    private double valor;
    private Usuario usuario;

    public Despesa(int id, String descricao, double valor, Usuario usuarioSessao) {
        this.descricao = descricao;
        this.valor = valor;
        this.id = id;
        this.usuario = usuarioSessao;
    }

    public Despesa(String descricao, double valor, Usuario usuarioSessao) {
        this.descricao = descricao;
        this.valor = valor;
        this.usuario = usuarioSessao;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
