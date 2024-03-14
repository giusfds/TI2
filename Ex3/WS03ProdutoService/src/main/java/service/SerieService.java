package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.SerieDAO;
import model.Serie;
import spark.Request;
import spark.Response;


public class SerieService {

	private SerieDAO serieDAO = new SerieDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_CODIGO = 1;
	private final int FORM_ORDERBY_TITULO = 2;
	private final int FORM_ORDERBY_GENERO = 3;
	
	
	public SerieService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Serie(), FORM_ORDERBY_TITULO);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Serie(), orderBy);
	}

	
	public void makeForm(int tipo, Serie serie, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umSerie = "";
		if(tipo != FORM_INSERT) {
			umSerie += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umSerie += "\t\t<tr>";
			umSerie += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/serie/list/1\">Nova Serie</a></b></font></td>";
			umSerie += "\t\t</tr>";
			umSerie += "\t</table>";
			umSerie += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/serie/";
			String name, descricao, buttonLabel, titulo, genero;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Serie";
				titulo = "Titulo";
				descricao = "Bla bla bla...";
				genero = "Terror, suspense...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + serie.getCodigo();
				name = "Atualizar Serie (ID " + serie.getCodigo() + ")";
				titulo = serie.getTitulo();
				descricao = serie.getDescricao();
				genero =  serie.getGenero();
				buttonLabel = "Atualizar";
			}
			umSerie += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umSerie += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umSerie += "\t\t<tr>";
			umSerie += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umSerie += "\t\t</tr>";
			umSerie += "\t\t<tr>";
			umSerie += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umSerie += "\t\t</tr>";
			umSerie += "\t\t<tr>";
			umSerie += "\t\t\t<td>&nbsp;Titulo: <input class=\"input--register\" type=\"text\" name=\"titulo\" value=\""+ titulo +"\"></td>";
			umSerie += "\t\t\t<td>Descricao: <input class=\"input--register\" type=\"text\" name=\"descricao\" value=\""+ descricao +"\"></td>";
			umSerie += "\t\t\t<td>Genero: <input class=\"input--register\" type=\"text\" name=\"genero\" value=\""+ genero +"\"></td>";
			umSerie += "\t\t</tr>";
			umSerie += "\t\t<tr>";
			//umSerie += "\t\t\t<td>&nbsp;Data de fabricação: <input class=\"input--register\" type=\"text\" name=\"dataFabricacao\" value=\""+ serie.getDataFabricacao().toString() + "\"></td>";
			//umSerie += "\t\t\t<td>Data de validade: <input class=\"input--register\" type=\"text\" name=\"dataValidade\" value=\""+ serie.getDataValidade().toString() + "\"></td>";
			umSerie += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umSerie += "\t\t</tr>";
			umSerie += "\t</table>";
			umSerie += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umSerie += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umSerie += "\t\t<tr>";
			umSerie += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Serie (Codigo " + serie.getCodigo() + ")</b></font></td>";
			umSerie += "\t\t</tr>";
			umSerie += "\t\t<tr>";
			umSerie += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umSerie += "\t\t</tr>";
			umSerie += "\t\t<tr>";
			umSerie += "\t\t\t<td>&nbsp;Titulo: "+ serie.getTitulo() +"</td>";
			umSerie += "\t\t\t<td>Descricao: "+ serie.getDescricao() +"</td>";
			umSerie += "\t\t\t<td>Genero: "+ serie.getGenero() +"</td>";
			umSerie += "\t\t</tr>";
			umSerie += "\t\t<tr>";
			//umSerie += "\t\t\t<td>&nbsp;Data de fabricação: "+ serie.getDataFabricacao().toString() + "</td>";
			//umSerie += "\t\t\t<td>Data de validade: "+ serie.getDataValidade().toString() + "</td>";
			umSerie += "\t\t\t<td>&nbsp;</td>";
			umSerie += "\t\t</tr>";
			umSerie += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-SERIE>", umSerie);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Series</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/serie/list/" + FORM_ORDERBY_CODIGO + "\"><b>Codigo</b></a></td>\n" +
        		"\t<td><a href=\"/serie/list/" + FORM_ORDERBY_TITULO + "\"><b>Titulo</b></a></td>\n" +
        		"\t<td><a href=\"/serie/list/" + FORM_ORDERBY_GENERO + "\"><b>Genero</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Serie> series;
		if (orderBy == FORM_ORDERBY_CODIGO) {                 	series = serieDAO.getOrderByCodigo();
		} else if (orderBy == FORM_ORDERBY_TITULO) {		series = serieDAO.getOrderByTitulo();
		} else if (orderBy == FORM_ORDERBY_GENERO) {			series = serieDAO.getOrderByGenero();
		} else {											series = serieDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Serie p : series) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getCodigo() + "</td>\n" +
            		  "\t<td>" + p.getTitulo() + "</td>\n" +
            		  "\t<td>" + p.getGenero() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/serie/" + p.getCodigo() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/serie/update/" + p.getCodigo() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteSerie('" + p.getCodigo() + "', '" + p.getTitulo() + "', '" + p.getGenero() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-SERIE>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String titulo = request.queryParams("titulo");
		String descricao = request.queryParams("descricao");
		String genero = request.queryParams("genero");
		String resp = "";
		
		Serie serie = new Serie(-1, titulo, descricao, genero);
		
		if(serieDAO.insert(serie) == true) {
            resp = "Serie (" + titulo + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Serie (" + titulo + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int codigo = Integer.parseInt(request.params(":codigo"));		
		Serie serie = (Serie) serieDAO.get(codigo);
		
		if (serie != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, serie, FORM_ORDERBY_TITULO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Serie " + codigo + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int codigo = Integer.parseInt(request.params(":codigo"));		
		Serie serie = (Serie) serieDAO.get(codigo);
		
		if (serie != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, serie, FORM_ORDERBY_TITULO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Serie " + codigo + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int codigo = Integer.parseInt(request.params(":codigo"));
		Serie serie = serieDAO.get(codigo);
        String resp = "";       

        if (serie != null) {
        	serie.setTitulo(request.queryParams("titulo"));
        	serie.setDescricao(request.queryParams("descricao"));
        	serie.setGenero(request.queryParams("genero"));
        	serieDAO.update(serie);
        	response.status(200); // success
            resp = "Serie (Codigo " + serie.getCodigo() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Serie (Codigo \" + serie.getCodigo() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int codigo = Integer.parseInt(request.params(":codigo"));
        Serie produto = serieDAO.get(codigo);
        String resp = "";       

        if (produto != null) {
            serieDAO.delete(codigo);
            response.status(200); // success
            resp = "Serie (" + codigo + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Serie (" + codigo + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}