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
import java.util.List;
import web.test.DemandeTest;

/**
 *
 * @author ranguenot
 */
public class ListeDemandesSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            List<DemandeTest> listeDemande = (List<DemandeTest>) request.getAttribute("liste_demandes");
            JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
            JsonArrayBuilder jsonListeDemande = Json.createArrayBuilder();
            for (DemandeTest demandeTest : listeDemande) {
                JsonObjectBuilder jsonDemande = Json.createObjectBuilder();
                jsonDemande.add("id", demandeTest.getId());
                jsonDemande.add("dateCreation", demandeTest.getDateCreation().toString());
                jsonDemande.add("description", demandeTest.getDescription());
                
                jsonListeDemande.add(jsonDemande);

            }
            jsonContainer.add("listedemandes", jsonListeDemande);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            out.print(jsonContainer.build().toString());
            out.close();
        }
    }
;

}
