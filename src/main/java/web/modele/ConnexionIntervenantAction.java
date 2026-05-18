/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.modele.Intervenant;
import metier.service.Service;

/**
 *
 * @author ranguenot
 */
public class ConnexionIntervenantAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String email = request.getParameter("login_email");
        String mdp = request.getParameter("login_mdp");

        System.out.println("connection, email : " + email);
        System.out.println("connection, mdp : " + mdp);

        Service service = new Service();

        Intervenant intervenant = service.intervenantAuthentification(email, mdp);
        System.out.println(intervenant);
        if (intervenant == null) {
            request.setAttribute("succes", false);
            session.setAttribute("type", null);
            session.setAttribute("utilisateur_id", null);
        } else {
            session.setAttribute("utilisateur_id", intervenant.getId());
            session.setAttribute("type", "intervenant");
            request.setAttribute("succes", true);
        }

    }
;
}
