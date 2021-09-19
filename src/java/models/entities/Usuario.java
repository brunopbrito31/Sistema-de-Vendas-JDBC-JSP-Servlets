package models.entities;

import models.enums.NivelAcesso;

public class Usuario {
    
    private int id;
    
    private String nome;
    
    private String senha;
    
    private String email;
    
    private String perguntaCadas;
    
    private String respostaCadas;
    
    private NivelAcesso nivel;

    public Usuario() {
    }

    public Usuario(int id, String nome, String senha, String email, String perguntaCadas, String respostaCadas, NivelAcesso nivel) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.perguntaCadas = perguntaCadas;
        this.respostaCadas = respostaCadas;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerguntaCadas() {
        return perguntaCadas;
    }

    public void setPerguntaCadas(String perguntaCadas) {
        this.perguntaCadas = perguntaCadas;
    }

    public String getRespostaCadas() {
        return respostaCadas;
    }

    public void setRespostaCadas(String respostaCadas) {
        this.respostaCadas = respostaCadas;
    }

    public NivelAcesso getNivel() {
        return nivel;
    }

    public void setNivel(NivelAcesso nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", senha=" + senha + ", email=" + email + ", perguntaCadas=" + perguntaCadas + ", respostaCadas=" + respostaCadas + ", nivel=" + nivel + '}';
    }
    
}
