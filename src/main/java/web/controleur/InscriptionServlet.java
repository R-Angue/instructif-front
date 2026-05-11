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
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.time.LocalDate;
import java.util.Date;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.service.Service;

/**
 *
 * @author ranguenot
 */
@WebServlet(name = "Inscription", urlPatterns = {"/InscriptionServlet"})
public class InscriptionServlet extends HttpServlet {
    
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
//        Eleve alice = new Eleve(
//            "Pascal",
//            "Alice",
//            LocalDate.of(2012, 5, 14),
//            "alice.pascal@free.fr",
//            5,
//            null,
//            "alice",
//            "alice123"
//        );
        String nom = request.getParameter("ins_nom");
        String prenom = request.getParameter("ins_prenom");
        String date = request.getParameter("ins_date");
        String email = request.getParameter("ins_email");
        String classe = request.getParameter("ins_class");
        String id = request.getParameter("ins_class");
        String etablissementid = request.getParameter("ins_etablissement");
        String mdp = request.getParameter("ins_mdp");
        
        
        Service service = new Service();
        
        Etablissement etablissement = service.findByIdEtablissement(parseLong(etablissementid));
        Eleve eleves = new Eleve(nom, prenom, LocalDate.parse(date), email, parseInt(classe), etablissement, id, mdp);
        
        
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
