/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Soutien;
import metier.modele.Theme;
import metier.service.Service;

/**
 *
 * @author ranguenot
 */
public class FinirSoutienAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Long soutienId = Long.parseLong(request.getParameter("soutien_id"));
        String commentaire = request.getParameter("soutien_commentaire");
        Long id = (Long) session.getAttribute("utilisateur_id");

        Service service = new Service();

        Soutien soutien = service.findByIdSoutien(soutienId);
        Intervenant intervenant = service.findByIdIntervenant(id);

        if (soutien.getIntervenant().getId() != intervenant.getId()) {
            request.setAttribute("succes", false);
        } else {
            Soutien soutien_fini = service.finSoutien(soutien, commentaire);

            if (soutien_fini.getDuree() == 0) {
                request.setAttribute("succes", false);
            } else {
                request.setAttribute("succes", true);
            }
        }

    }
;
}
