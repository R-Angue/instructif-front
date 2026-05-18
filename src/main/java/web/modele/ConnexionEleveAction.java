/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.service.Service;

/**
 *
 * @author ranguenot
 */
public class ConnexionEleveAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String email = request.getParameter("login_email");
        String mdp = request.getParameter("login_mdp");

        System.out.println("connection, email : " + email);
        System.out.println("connection, mdp : " + mdp);

        Service service = new Service();

        Eleve eleve = service.eleveAuthentification(email, mdp);

        if (eleve == null) {
            request.setAttribute("succes", false);
            session.setAttribute("type", null);
            session.setAttribute("utilisateur_id", null);
        } else {
            session.setAttribute("utilisateur_id", eleve.getId());
            request.setAttribute("succes", true);
            session.setAttribute("type", "eleve");
        }

    }
;
}
