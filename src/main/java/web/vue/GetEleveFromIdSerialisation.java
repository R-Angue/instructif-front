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
import java.math.BigDecimal;
import metier.modele.Eleve;
import metier.modele.Etablissement;

/**
 *
 * @author ranguenot
 */
public class GetEleveFromIdSerialisation extends Serialisation{
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            Eleve eleve = (Eleve) request.getAttribute("eleve");
            JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
            JsonObjectBuilder jsonContainerEtablissement = Json.createObjectBuilder();
            jsonContainer.add("succes", (Boolean) request.getAttribute("succes"));
            if (eleve != null) {
                Etablissement etablissement = eleve.getEtablissement();
                
                jsonContainer.add("nom", eleve.getNom());
                jsonContainer.add("prenom", eleve.getPrenom());
                jsonContainer.add("dateNaissance", eleve.getDateDeNaissance().toString());
                jsonContainer.add("adresseMail", eleve.getMail());
                jsonContainer.add("classe", eleve.getClasse());
               
                
                jsonContainerEtablissement.add("codeUai", etablissement.getCodeUai());
                jsonContainerEtablissement.add("nom", etablissement.getNom());
                jsonContainerEtablissement.add("ips", etablissement.getIps());
                jsonContainerEtablissement.add("commune", etablissement.getCommune());
                jsonContainerEtablissement.add("departement", etablissement.getDepartement());
                jsonContainerEtablissement.add("academie", etablissement.getAcademie());
                jsonContainerEtablissement.add("latitude", etablissement.getLatitude());
                jsonContainerEtablissement.add("longiture", etablissement.getLongitude());
                
                jsonContainer.add("etablissement", jsonContainerEtablissement);
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
