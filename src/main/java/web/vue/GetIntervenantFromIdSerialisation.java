/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import metier.modele.Intervenant;

/**
 *
 * @author ranguenot
 */
public class GetIntervenantFromIdSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            Intervenant intervenant = (Intervenant) request.getAttribute("intervenant");
            JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
            jsonContainer.add("succes", (Boolean) request.getAttribute("succes"));

            if (intervenant != null) {
                jsonContainer.add("nom", intervenant.getNom());
                jsonContainer.add("prenom", intervenant.getPrenom());
                jsonContainer.add("login", intervenant.getLogin());
                jsonContainer.add("adresseMail", intervenant.getEmail());
                jsonContainer.add("nbInterventions", intervenant.getNbInterventions());

            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            out.print(jsonContainer.build().toString());
            out.close();
        }
    }
;
}
