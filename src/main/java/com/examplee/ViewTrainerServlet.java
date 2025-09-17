package com.examplee;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class ViewTrainerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM trainers");

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Trainer List</title>");
            out.println("<style>");
            out.println("body {font-family: Arial, sans-serif; background: linear-gradient(to right, #00c6ff, #0072ff); padding: 20px;}");
            out.println(".container {background: #fff; padding: 20px; border-radius: 10px; box-shadow: 0px 4px 10px rgba(0,0,0,0.3);}");
            out.println("h2 {text-align: center; color: #333;}");
            out.println("table {width: 100%; border-collapse: collapse; margin-top: 20px;}");
            out.println("th, td {padding: 12px; text-align: center; border-bottom: 1px solid #ddd;}");
            out.println("th {background-color: #0072ff; color: white;}");
            out.println("tr:hover {background-color: #f1f1f1;}");
            out.println("a.button {padding: 6px 12px; background: #0072ff; color: white; text-decoration: none; border-radius: 5px;}");
            out.println("a.button:hover {background: #00c6ff;}");
            out.println("</style></head><body>");
            out.println("<div class='container'>");
            out.println("<h2>📋 Trainer List</h2>");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Skill</th><th>Actions</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("id");
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("skill") + "</td>");
                out.println("<td>");
                out.println("<a class='button' href='deleteTrainer?id=" + id + "'>🗑 Delete</a> ");
                out.println("<a class='button' href='updateTrainerForm?id=" + id + "'>✏ Edit</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<a href='addTrainer.html' class='button'>➕ Add More Trainers</a>");
            out.println("</div></body></html>");

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}




