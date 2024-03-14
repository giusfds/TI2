package app;

public class Principal {

    public static void main(String[] args) {

        DAO dao = new DAO();

        dao.conectar();

        // Inserir uma série na tabela
        Series serie = new Series(89, "terror", "Midsommar", "45mins/episódio");
        if (dao.inserirSerie(serie)) {
            System.out.println("Inserção com sucesso -> " + serie.toString());
        }

        // Mostrar séries
        System.out.println("==== Mostrar séries === ");
        Series[] series = dao.getSeries();
        for (int i = 0; i < series.length; i++) {
            System.out.println(series[i].toString());
        }

        // Atualizar a série
        serie.setTitulo("Inception");
        dao.atualizarSerie(serie);

        // Mostrar as séries
        System.out.println("==== Mostrar séries === ");
        series = dao.getSeries();
        for (int i = 0; i < series.length; i++) {
            System.out.println(series[i].toString());
        }

        // Excluir a série
        dao.excluirSerie(serie.getCodigo());

        // Mostrar as séries
        series = dao.getSeries();
        System.out.println("==== Mostrar séries === ");
        for (int i = 0; i < series.length; i++) {
            System.out.println(series[i].toString());
        }

        dao.close();
    }
}
