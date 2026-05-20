/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.modele.Soutien;
import metier.modele.Theme;
import metier.service.Service;

/**
 *
 * @author ranguenot
 */
public class ValiderDemandeAction extends Action{
    @Override
    public void execute(HttpServletRequest request)  {
        HttpSession session = request.getSession();

        String themeId = request.getParameter("demande_theme");
        String commentaire = request.getParameter("demande_commentaire");
        Long id = (Long) session.getAttribute("utilisateur_id");
        


        Service service = new Service();
        Eleve eleve = service.findByIdEleve(id);
        Theme theme = service.findByIdTheme(Long.parseLong(themeId));
        Soutien soutien = service.demandeSoutien(eleve, theme, commentaire);

        if (soutien == null) {
            request.setAttribute("succes", false);
        } else {
            request.setAttribute("succes", true);
            request.setAttribute("url_visio", soutien.getLienVisio());
            request.setAttribute("nom_intervenant", soutien.getIntervenant().getNom());
            request.setAttribute("prenom_intervenant", soutien.getIntervenant().getPrenom());
        }

    }
;
}
