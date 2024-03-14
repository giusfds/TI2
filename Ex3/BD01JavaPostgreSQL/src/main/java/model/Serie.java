package model;

public class Serie {
    private int codigo;
    private String genero;
    private String titulo;
    private String duracaoEpisodio;

    public Serie() {
        this.codigo = -1;
        this.genero = "";
        this.titulo = "";
        this.duracaoEpisodio = "";
    }

    public Serie(int codigo, String genero, String titulo, String duracaoEpisodio) {
        this.codigo = codigo;
        this.genero = genero;
        this.titulo = titulo;
        this.duracaoEpisodio = duracaoEpisodio;
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

    public String getDuracaoEpisodio() {
        return duracaoEpisodio;
    }

    public void setDuracaoEpisodio(String duracaoEpisodio) {
        this.duracaoEpisodio = duracaoEpisodio;
    }

    @Override
    public String toString() {
        return "Series [codigo=" + codigo + ", genero=" + genero + ", titulo=" + titulo + ", duracaoEpisodio=" + duracaoEpisodio + "]";
    }
}
