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
import java.util.Map;
import metier.modele.Etablissement;

/**
 *
 * @author ranguenot
 */
public class GetGlobalStatsSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            List<Map<String, Object>> etablissements = (List<Map<String, Object>>) request.getAttribute("etablissements");
            int nb_soutien_tot = (int) request.getAttribute("nb_soutien_tot");
            double avg_duree_tot = (double) request.getAttribute("avg_duree_tot");

            JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
            JsonArrayBuilder jsonListeEtab = Json.createArrayBuilder();
            for (Map<String, Object> obj : etablissements) {
                Etablissement etablissement = (Etablissement) obj.get("etablissement");
                int nb_soutien = ((Number) obj.get("nb_soutien")).intValue();

                double avg_duree = ((Number) obj.get("avg_duree")).doubleValue();

                System.out.println("Verification Etablissement");
                System.out.println(etablissement);
                System.out.println(nb_soutien);
                System.out.println(avg_duree);

                JsonObjectBuilder jsonEtab = Json.createObjectBuilder();
                jsonEtab.add("id", etablissement.getId());
                jsonEtab.add("nom", etablissement.getNom());
                jsonEtab.add("longitude", etablissement.getLongitude());
                jsonEtab.add("latitude", etablissement.getLatitude());
                jsonEtab.add("ips", etablissement.getIps());
                jsonEtab.add("nb_soutien", nb_soutien);
                jsonEtab.add("avg_duree", avg_duree);
                jsonListeEtab.add(jsonEtab);

            }
            jsonContainer.add("listeEtablissement", jsonListeEtab);
            jsonContainer.add("nb_soutien_tot", nb_soutien_tot );
            jsonContainer.add("avg_duree_tot", avg_duree_tot);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            out.print(jsonContainer.build().toString());
            out.close();
        }
    }
;
}
