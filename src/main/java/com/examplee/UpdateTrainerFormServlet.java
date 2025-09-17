package com.examplee;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class UpdateTrainerFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM trainers WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<!DOCTYPE html><html><head><title>Edit Trainer</title></head><body>");
                out.println("<h2>Edit Trainer</h2>");
                out.println("<form action='updateTrainer' method='post'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("Name: <input type='text' name='name' value='" + rs.getString("name") + "'><br><br>");
                out.println("Email: <input type='email' name='email' value='" + rs.getString("email") + "'><br><br>");
                out.println("Skill: <input type='text' name='skill' value='" + rs.getString("skill") + "'><br><br>");
                out.println("<input type='submit' value='Update'>");
                out.println("</form>");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
