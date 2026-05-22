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
import metier.modele.Autre;
import metier.modele.Enseignant;
import metier.modele.Etudiant;
import metier.modele.Intervenant;
import metier.modele.Soutien;

/**
 *
 * @author ranguenot
 */
public class GetIntervenantFromIdSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            Intervenant intervenant = (Intervenant) request.getAttribute("intervenant");
            List<Soutien> soutiens_en_cours = (List<Soutien>) request.getAttribute("soutiens_en_cours");
            Integer nbSoutiens = (Integer) request.getAttribute("nb_soutiens");
            Double avgDuree = (Double) request.getAttribute("avg_duree");
            Integer nbEleves = (Integer) request.getAttribute("nb_eleves");
            Integer nbEtablissements = (Integer) request.getAttribute("nb_etablissements");

            JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
            jsonContainer.add("succes", (Boolean) request.getAttribute("succes"));

            JsonArrayBuilder soutiensJSONArray = Json.createArrayBuilder();

            if (intervenant != null) {
                jsonContainer.add("nom", intervenant.getNom());
                jsonContainer.add("prenom", intervenant.getPrenom());
                jsonContainer.add("login", intervenant.getLogin());
                jsonContainer.add("adresseMail", intervenant.getEmail());
                jsonContainer.add("nbInterventions", intervenant.getNbInterventions());

                jsonContainer.add("nbSoutiens", nbSoutiens != null ? nbSoutiens : 0);
                jsonContainer.add("avgDuree", avgDuree != null ? avgDuree : 0.0);
                jsonContainer.add("nbEleves", nbEleves != null ? nbEleves : 0);
                jsonContainer.add("nbEtablissements", nbEtablissements != null ? nbEtablissements : 0);

                if (intervenant instanceof Etudiant) {
                    Etudiant etudiant = (Etudiant) intervenant;
                    jsonContainer.add("typeIntervenant", "etudiant");
                    jsonContainer.add("universite", etudiant.getUniversite() != null ? etudiant.getUniversite() : "");
                    jsonContainer.add("specialite", etudiant.getSpecialite() != null ? etudiant.getSpecialite() : "");
                } else if (intervenant instanceof Enseignant) {
                    Enseignant enseignant = (Enseignant) intervenant;
                    jsonContainer.add("typeIntervenant", "enseignant");
                    jsonContainer.add("typeEtablissement",
                            enseignant.getTypeEtablissement() != null ? enseignant.getTypeEtablissement() : "");
                } else if (intervenant instanceof Autre) {
                    Autre autre = (Autre) intervenant;
                    jsonContainer.add("typeIntervenant", "autre");
                    jsonContainer.add("activite", autre.getActivite() != null ? autre.getActivite() : "");
                } else {
                    jsonContainer.add("typeIntervenant", "intervenant");
                }

            }

            if (soutiens_en_cours != null) {
                for (Soutien soutien : soutiens_en_cours) {
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
            }

            jsonContainer.add("soutiens", soutiensJSONArray);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            out.print(jsonContainer.build().toString());
            out.close();
        }
    };
}
