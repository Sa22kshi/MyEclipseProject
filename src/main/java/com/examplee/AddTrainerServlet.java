package com.examplee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddTrainerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String skill = request.getParameter("skill");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO trainers(name,email,skill) VALUES (?,?,?)"
            );
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, skill);

            ps.executeUpdate();

            // Redirect back to view trainers
            response.sendRedirect("viewTrainers");

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}



