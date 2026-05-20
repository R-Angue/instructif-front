/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web.controleur;

import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.modele.Action;
import web.modele.CheckConnexionAction;
import web.modele.CheckEtablissementExiste;
import web.modele.ConnexionEleveAction;
import web.modele.ConnexionIntervenantAction;
import web.modele.ConsulterListeDemandesAction;
import web.modele.GetEleveFromIdAction;
import web.modele.GetIntervenantFromIdAction;
import web.modele.GetMatiereAction;
import web.modele.InscriptionAction;
import web.modele.ValiderDemandeAction;
import web.vue.EtablissementSerialisation;
import web.vue.GetEleveFromIdSerialisation;
import web.vue.GetIntervenantFromIdSerialisation;
import web.vue.GetMatiereSerialisation;
import web.vue.SuccesSerialisation;
import web.vue.ListeDemandesSerialisation;
import web.vue.Serialisation;
import web.vue.ValiderDemandeSerialisation;

/**
 *
 * @author ranguenot
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.creerFabriquePersistance();
    }

    // Cette méthode s'exécute UNE SEULE FOIS quand le serveur s'arrête
    @Override
    public void destroy() {
        JpaUtil.fermerFabriquePersistance();
        super.destroy();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Action action;
            Serialisation serialisation;

            System.out.println("resultat du todo : " + request.getParameter("todo"));

            switch (request.getParameter("todo")) {
                case "consulter-liste-demandes":
                    action = new ConsulterListeDemandesAction();
                    serialisation = new ListeDemandesSerialisation();

                    action.execute(request);
                    serialisation.appliquer(request, response);
                    break;

                case "connexion_eleve":
                    action = new ConnexionEleveAction();
                    serialisation = new SuccesSerialisation();

                    action.execute(request);
                    serialisation.appliquer(request, response);
                    break;

                case "connexion_intervenant":
                    action = new ConnexionIntervenantAction();
                    serialisation = new SuccesSerialisation();

                    action.execute(request);
                    serialisation.appliquer(request, response);
                    break;

                case "inscription":
                    action = new InscriptionAction();
                    serialisation = new SuccesSerialisation();

                    action.execute(request);
                    serialisation.appliquer(request, response);
                    break;

                case "check_connexion":
                    action = new CheckConnexionAction();
                    serialisation = new SuccesSerialisation();

                    action.execute(request);
                    serialisation.appliquer(request, response);
                    break;
                case "check_etablissement":
                    action = new CheckEtablissementExiste();
                    serialisation = new EtablissementSerialisation();

                    action.execute(request);
                    serialisation.appliquer(request, response);
                    break;
                case "get_eleve_from_id":
                    action = new GetEleveFromIdAction();
                    serialisation = new GetEleveFromIdSerialisation();

                    action.execute(request);
                    serialisation.appliquer(request, response);
                    break;
                case "get_intervenant_from_id":
                    action = new GetIntervenantFromIdAction();
                    serialisation = new GetIntervenantFromIdSerialisation();

                    action.execute(request);
                    serialisation.appliquer(request, response);
                    break;
                case "get_matiere" : 
                    action = new GetMatiereAction();
                    serialisation = new GetMatiereSerialisation();
                    
                    action.execute(request);
                    serialisation.appliquer(request, response);
                    break;
                    
                case "valider_demande" : 
                    action = new ValiderDemandeAction();
                    serialisation = new ValiderDemandeSerialisation();

                    action.execute(request);
                    serialisation.appliquer(request, response);
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
