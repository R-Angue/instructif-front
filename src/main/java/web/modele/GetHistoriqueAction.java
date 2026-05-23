/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Soutien;
import metier.service.Service;

/**
 *
 * @author ranguenot
 */
public class GetHistoriqueAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("utilisateur_id");
        String type = (String) session.getAttribute("type");

        Service service = new Service();

        if (type == "eleve") {
            Eleve eleve = service.findByIdEleve(id);

            if (eleve != null) {
                request.setAttribute("succes", true);
                request.setAttribute("type", "eleve");
                request.setAttribute("historique", service.historiqueEleve(eleve));
            } else {
                request.setAttribute("succes", false);
            }
        } else if (type == "intervenant") {
            Intervenant intervenant = service.findByIdIntervenant(id);

            if (intervenant != null) {
                request.setAttribute("succes", true);
                request.setAttribute("type", "intervenant");
                request.setAttribute("historique", service.historiqueIntervenant(intervenant));
            } else {
                request.setAttribute("succes", false);
            }
        }

    }
}
