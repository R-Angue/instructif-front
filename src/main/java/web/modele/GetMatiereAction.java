/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import metier.modele.Matiere;
import metier.modele.Theme;
import metier.service.Service;

/**
 *
 * @author ranguenot
 */
public class GetMatiereAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("utilisateur_id");
        
        Service service = new Service();
        List<Matiere> matieres = service.getListeMatiere();
        
        request.setAttribute("matieres", matieres);
        for(Matiere m : matieres){
           List<Theme> themes = service.getListeTheme(m);
           request.setAttribute(m.getNom(), themes);
        }
        
    }
}
