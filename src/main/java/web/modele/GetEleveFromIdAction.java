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
public class GetEleveFromIdAction extends Action{
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("utilisateur_id");
        
        Service service = new Service();
        Eleve eleve = service.findByIdEleve(id);
        
        if(eleve == null){
            request.setAttribute("succes", false);
            request.setAttribute("eleve", null);
        }
        else{
            request.setAttribute("succes", true);
            request.setAttribute("eleve", eleve);
//            request.setAttribute("nom", eleve.getNom());
//            request.setAttribute("prenom", eleve.getPrenom());
//            request.setAttribute("DateNaissance", eleve.getDateDeNaissance());
//            request.setAttribute("adresseMail", eleve.getMail());
//            request.setAttribute("classe", eleve.getClasse());
//            request.setAttribute("nom", eleve.getNom());
        }
        
    }
}
