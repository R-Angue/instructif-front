/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metier.modele.Etablissement;
import metier.service.Service;

/**
 *
 * @author ranguenot
 */
public class GetGlobalStatsAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        List<Etablissement> etablissements = service.listeEtablissement();
        int nb_soutien_tot = service.getNbSoutien();
        double avg_duree_tot = service.getAvgDureeTotal();
        List<Map<String, Object>> result = new ArrayList<>();
        
        for(Etablissement etablissement : etablissements) {
             Map<String, Object> obj = new HashMap<>();
            
            int nb_soutien = service.getNbSoutiensParEtablissement(etablissement);
            double avg_duree = service.getAvgDureeParEtablissement(etablissement);
            
            obj.put("etablissement", etablissement);
            obj.put("nb_soutien", nb_soutien);
            obj.put("avg_duree", avg_duree);
            
            result.add(obj);
        }
        request.setAttribute("etablissements", result);
        request.setAttribute("nb_soutien_tot", nb_soutien_tot);
        request.setAttribute("avg_duree_tot", avg_duree_tot);

    }
;
}
