package com.example.a2345meia78;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Contato implements Serializable{
    String nome;
    String email;

    @Override
    public String toString() {
        return nome + " - " + telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    String telefone;
    String foto;

    public Contato(String nome, String email, String telefone, String foto) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.foto = foto;
    }

    public Contato(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Contato(String nome) {
        this.nome = nome;
    }

    public Contato(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Contato(Contato contato) {
        this.nome = contato.getNome();
        this.email = contato.getEmail();
        this.telefone = contato.getTelefone();
        this.foto= contato.getFoto();

    }

    public Contato() {
    }


}
