/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ranguenot
 */
public class DeconnexionAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        request.setAttribute("succes", true);
        request.setAttribute("type", null);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("utilisateur_id", null);
            session.setAttribute("type", null);
        }
    }
}
