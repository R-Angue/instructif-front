/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ranguenot
 */
public class SuccesSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            Boolean succes = (Boolean) request.getAttribute("succes");
            String type = (String) request.getAttribute("type");
            JsonObjectBuilder jsonContainer = Json.createObjectBuilder();

            jsonContainer.add("succes", succes);
            if (type == null) {
                jsonContainer.addNull("type");
            } else {
                jsonContainer.add("type", type);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            out.print(jsonContainer.build().toString());
            out.close();
        }
    }
;

}
