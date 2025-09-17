package com.examplee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteTrainerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM trainers WHERE id=?");
            ps.setInt(1, Integer.parseInt(id));

            int result = ps.executeUpdate();

            if (result > 0) {
                response.sendRedirect("viewTrainers");
            } else {
                out.println("<h3>⚠️ Trainer not found!</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}

