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
public class CheckConnexionAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Long id = (Long) session.getAttribute("utilisateur_id");

        if (id == null) {
            request.setAttribute("succes", false);
        } else {
            request.setAttribute("succes", true);
            request.setAttribute("type", (String) session.getAttribute("type"));
        }
    }
;
}
