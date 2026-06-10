package br.edu.ufrgs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.edu.ufrgs.model.Freight;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {

            List<Freight> freights =
                    (List<Freight>) session.getAttribute("freights");

            request.setAttribute(
                    "freights",
                    freights
            );
        }

        request.getRequestDispatcher(
                "/WEB-INF/results.jsp"
        ).forward(request, response);
    }
}
