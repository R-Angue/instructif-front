/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.time.LocalDate;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.service.Service;

/**
 *
 * @author ranguenot
 */
public class InscriptionAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String nom = request.getParameter("ins_nom");
        String prenom = request.getParameter("ins_prenom");
        String date = request.getParameter("ins_date");
        String email = request.getParameter("ins_email");
        String classe = request.getParameter("ins_classe");
        String id = request.getParameter("ins_id");
        String uai = request.getParameter("ins_uai");
        String mdp = request.getParameter("ins_mdp");
        
        
        Service service = new Service();
        
        Eleve eleve = new Eleve(nom, prenom, LocalDate.parse(date), email, parseInt(classe), null, id, mdp);
        
        Boolean succes = service.inscrireEleve(eleve, uai);

        if (!succes) {
            request.setAttribute("succes", false);
        } else {
            request.setAttribute("succes", true);
        }

    }
;
}
