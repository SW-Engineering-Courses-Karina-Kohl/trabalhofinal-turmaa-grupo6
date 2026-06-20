package br.edu.ufrgs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import br.edu.ufrgs.exporter.CsvFreightExporter;
import br.edu.ufrgs.exporter.FreightExporter;
import br.edu.ufrgs.model.Freight;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session =
                    request.getSession(false);

            if (session == null) {
                response.sendRedirect(
                        request.getContextPath()
                        + "/results"
                );
                return;
            }

            List<Freight> freights =
                    (List<Freight>) session.getAttribute("freights");

            if (freights == null || freights.isEmpty()) {
                response.sendRedirect(
                        request.getContextPath()
                        + "/results"
                );
                return;
            }

            exportFreights(response, freights);

        } catch (Exception e) {

            System.err.println("Erro no DownloadServlet:");
            e.printStackTrace();

            throw new ServletException(e);
        }
    }

    private void exportFreights(
            HttpServletResponse response,
            List<Freight> freights)
            throws IOException {

        String path =
                System.getProperty("java.io.tmpdir")
                + "/logistica_finalizada.csv";

        FreightExporter exporter =
                new CsvFreightExporter(path);

        exporter.exportFreights(freights);

        Path file = Paths.get(path);

        response.setContentType("text/csv");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=\"logistica_finalizada.csv\""
        );

        Files.copy(
                file,
                response.getOutputStream()
        );

        response.getOutputStream().flush();
    }
}