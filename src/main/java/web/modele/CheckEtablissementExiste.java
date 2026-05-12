/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.service.Service;
import util.JsonUtil;
import web.test.DemandeTest;
import web.test.ServiceTest;

/**
 *
 * @author ranguenot
 */
public class CheckEtablissementExiste extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        

        JsonUtil jsonutil = new JsonUtil();
        String uai = request.getParameter("uai");
                
        Etablissement etablissement = jsonutil.getInfoEtablissement(uai);

        if (etablissement == null) {
            request.setAttribute("etablissement", null);
        } else {
            request.setAttribute("etablissement", etablissement);
        }
    }
;
}
