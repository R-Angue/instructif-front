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
public class GetIntervenantFromIdAction extends Action{
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("utilisateur_id");
        
        Service service = new Service();
        Intervenant intervenant = service.findByIdIntervenant(id);
        
        if(intervenant == null){
            request.setAttribute("succes", false);
            request.setAttribute("intervenant", null);
            request.setAttribute("soutiens_en_cours", null);
        }
        else{
            List<Soutien> soutiens = service.historiqueIntervenant(intervenant);
            List<Soutien> soutiens_en_cours = new ArrayList<>();;
            
            for (Soutien soutien : soutiens) {
                if (soutien.getBilan() == null && soutien.getDuree() == 0) {
                    soutiens_en_cours.add(soutien);
                }
            }
            
            request.setAttribute("succes", true);
            request.setAttribute("intervenant", intervenant);
            request.setAttribute("soutiens_en_cours", soutiens_en_cours);
        }
        
    }
}
