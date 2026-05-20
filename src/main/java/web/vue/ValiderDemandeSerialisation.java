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

/**
 *
 * @author ranguenot
 */
public class ValiderDemandeSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            Boolean succes = (Boolean) request.getAttribute("succes");
            String url_visio = (String) request.getAttribute("url_visio");
            String nom_intervenant = (String) request.getAttribute("nom_intervenant");
            String prenom_intervenant = (String) request.getAttribute("prenom_intervenant");
            
            JsonObjectBuilder jsonContainer = Json.createObjectBuilder();

            jsonContainer.add("succes", succes);
            if (succes == true) {
                jsonContainer.add("url_visio", url_visio);
                jsonContainer.add("nom_intervenant", nom_intervenant);
                jsonContainer.add("prenom_intervenant", prenom_intervenant);
            } else {
                jsonContainer.addNull("url_visio");
                jsonContainer.addNull("nom_intervenant");
                jsonContainer.addNull("prenom_intervenant");
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            out.print(jsonContainer.build().toString());
            out.close();
        }
    }
;

}
