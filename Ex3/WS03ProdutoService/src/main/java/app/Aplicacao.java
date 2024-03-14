package app;

import static spark.Spark.*;
import service.SerieService;


public class Aplicacao {
	
	private static SerieService serieService = new SerieService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/serie/insert", (request, response) -> serieService.insert(request, response));

        get("/serie/:codigo", (request, response) -> serieService.get(request, response));
        
        get("/serie/list/:orderby", (request, response) -> serieService.getAll(request, response));

        get("/serie/update/:codigo", (request, response) -> serieService.getToUpdate(request, response));
        
        post("/serie/update/:codigo", (request, response) -> serieService.update(request, response));
           
        get("/serie/delete/:codigo", (request, response) -> serieService.delete(request, response));

             
    }
}