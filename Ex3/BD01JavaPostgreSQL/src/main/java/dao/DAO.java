
import java.sql.*;

public class DAO {
    private Connection conexao;

    public DAO() {
        conexao = null;
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "teste";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "ti2cc";
        String password = "ti@cc";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao != null);
            System.out.println("Conexão efetuada com o PostgreSQL!");
        } catch (ClassNotFoundException e) {
            System.err.println("Conexão NÃO efetuada com o PostgreSQL -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o PostgreSQL -- " + e.getMessage());
        }

        return status;
    }

    public boolean close() {
        boolean status = false;

        try {
            if (conexao != null) {
                conexao.close();
                status = true;
                System.out.println("Conexão fechada com o PostgreSQL!");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public boolean inserirSerie(Series serie) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO series (codigo, genero, titulo, duracao_episodio) "
                    + "VALUES (" + serie.getCodigo() + ", '" + serie.getGenero() + "', '"
                    + serie.getTitulo() + "', '" + serie.getDuracaoEpisodio() + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean atualizarSerie(Series serie) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE series SET genero = '" + serie.getGenero() + "', titulo = '"
                    + serie.getTitulo() + "', duracao_episodio = '" + serie.getDuracaoEpisodio() + "'"
                    + " WHERE codigo = " + serie.getCodigo();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean excluirSerie(int codigo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM series WHERE codigo = " + codigo);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Series[] getSeries() {
        Series[] series = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM series");
            if (rs.next()) {
                rs.last();
                series = new Series[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    series[i] = new Series(rs.getInt("codigo"), rs.getString("genero"),
                            rs.getString("titulo"), rs.getString("duracao_episodio"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return series;
    }
}
