/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import web.test.DemandeTest;
import web.test.ServiceTest;

/**
 *
 * @author ranguenot
 */
public class ConsulterListeDemandesAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        ServiceTest sT = new ServiceTest();
        List<DemandeTest> liste_demandes = sT.listerDemandes();
        
        request.setAttribute("liste_demandes", liste_demandes);
        
    };
}
