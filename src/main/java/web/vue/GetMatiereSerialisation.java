/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import metier.modele.Matiere;
import metier.modele.Theme;

/**
 *
 * @author ranguenot
 */
public class GetMatiereSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            List<Matiere> matieres = (List<Matiere>) request.getAttribute("matieres");
            JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
            for (Matiere matiere : matieres) {
                JsonArrayBuilder listeThemes = Json.createArrayBuilder();

                for (Theme theme : (List<Theme>) request.getAttribute(matiere.getNom())) {
                    JsonObjectBuilder themeContainer = Json.createObjectBuilder();
                    themeContainer.add("nom", theme.getNom());
                    themeContainer.add("id", theme.getId());
                    
                    listeThemes.add(themeContainer);
                    
                }

                jsonContainer.add(matiere.getNom(), listeThemes);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            out.print(jsonContainer.build().toString());
            out.close();
        }
    }
;
}
