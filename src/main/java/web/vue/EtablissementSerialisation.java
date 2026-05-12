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
import metier.modele.Etablissement;

/**
 *
 * @author ranguenot
 */
public class EtablissementSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            Etablissement etablissement = (Etablissement) request.getAttribute("etablissement");
            JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
            
            if (etablissement != null) {
                jsonContainer.add("codeUai", etablissement.getCodeUai());
                jsonContainer.add("nom", etablissement.getNom());
                jsonContainer.add("ips", etablissement.getIps());
                jsonContainer.add("commune", etablissement.getCommune());
                jsonContainer.add("departement", etablissement.getDepartement());
                jsonContainer.add("academie", etablissement.getAcademie());
                jsonContainer.add("latitude", etablissement.getLatitude());
                jsonContainer.add("longiture", etablissement.getLongitude());
            } else {
                jsonContainer.add("nom", "null");
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            out.print(jsonContainer.build().toString());
            out.close();
        }
    }
;

}
