package br.pro.applistacompras;

public class Produto {

    private String nome;
    private double quantidade;

    public Produto() {

    }

    public Produto(String nome, double quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return  nome + " | " + quantidade;
    }
}
