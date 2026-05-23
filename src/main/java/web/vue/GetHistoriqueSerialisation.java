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
import metier.modele.Soutien;
import metier.modele.Theme;

/**
 *
 * @author ranguenot
 */
public class GetHistoriqueSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            List<Soutien> historique = (List<Soutien>) request.getAttribute("historique");
            String type = (String) request.getAttribute("type");
            JsonObjectBuilder jsonContainer = Json.createObjectBuilder();

            JsonArrayBuilder soutiensJSONArray = Json.createArrayBuilder();

            for (Soutien soutien : historique) {

                JsonObjectBuilder soutienContainer = Json.createObjectBuilder();

                    // Ajout des attributs simples (id et duree sont des primitifs/non-nuls)
                    soutienContainer.add("id", soutien.getId());
                    soutienContainer.add("duree", soutien.getDuree());

                    soutienContainer.add("lienVisio", soutien.getLienVisio() != null ? soutien.getLienVisio() : "");
                    soutienContainer.add("description",
                            soutien.getDescription() != null ? soutien.getDescription() : "");
                    soutienContainer.add("bilan", soutien.getBilan() != null ? soutien.getBilan() : "En attente");

                    soutienContainer.add("date", soutien.getDate() != null ? soutien.getDate().toString() : "");

                    soutienContainer.add("theme", soutien.getTheme() != null ? soutien.getTheme().getNom() : "");
                    soutienContainer.add("elevePrenom",
                            soutien.getEleve() != null ? soutien.getEleve().getPrenom() : "");
                    soutienContainer.add("eleveNom", soutien.getEleve() != null ? soutien.getEleve().getNom() : "");

                    soutiensJSONArray.add(soutienContainer);
            }
            
            jsonContainer.add("soutiens", soutiensJSONArray);
            jsonContainer.add("type", type);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            out.print(jsonContainer.build().toString());
            out.close();
        }
    }
;
}
