package model;

public class Serie {
    private int codigo;
    private String titulo;
    private String descricao;
    private String genero;

    public Serie() {
        this.codigo = -1;
        this.titulo = "";
        this.descricao = "";
        this.genero = "";
    }

    public Serie(int codigo, String titulo, String descricao, String genero) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.genero = genero;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Series [codigo=" + codigo + " , titulo=" + titulo + ", descricao=" + descricao + "genero=" + genero +"]";
    }
}
