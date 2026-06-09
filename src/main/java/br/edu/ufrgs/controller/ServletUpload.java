package br.edu.ufrgs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import br.edu.ufrgs.model.Freight;
import br.edu.ufrgs.model.FreightCompany;
import br.edu.ufrgs.model.Order;
import br.edu.ufrgs.provider.CompanyProvider;
import br.edu.ufrgs.provider.CsvCompanyProvider;
import br.edu.ufrgs.provider.OrderProvider;
import br.edu.ufrgs.provider.CsvOrderProvider;

import br.edu.ufrgs.services.FreightProcessor;

@WebServlet("/upload")
@MultipartConfig
public class ServletUpload extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Part companyFile = request.getPart("companyConfig");
        Part orderFile = request.getPart("orderList");

        List<String> companyLines = readLines(companyFile);
        List<String> orderLines = readLines(orderFile);

        try {

                CompanyProvider companyProvider =
                        new CsvCompanyProvider(companyLines);

                OrderProvider orderProvider =
                        new CsvOrderProvider(orderLines);

                FreightCompany company =
                        companyProvider.getCompany();

                List<Order> orders =
                        orderProvider.getOrders();

                FreightProcessor processor = 
                        new FreightProcessor(orderProvider, companyProvider);

                List<Freight> freights =
                        processor.freightProcess();

                HttpSession session = request.getSession();

                session.setAttribute("company", company);
                session.setAttribute("orders", orders);
                session.setAttribute("freights", freights);

                response.sendRedirect(
                        request.getContextPath() + "/results"
                );

        } catch (IllegalArgumentException e) {

                request.setAttribute(
                        "errorMessage",
                        e.getMessage()
                );

                request.getRequestDispatcher(
                        "/index.jsp"
                ).forward(request, response);

                return;
        } catch (Exception e) {

                e.printStackTrace();

                request.setAttribute(
                        "errorMessage",
                        "Erro interno do sistema."
                );

                request.getRequestDispatcher(
                        "/index.jsp"
                ).forward(request, response);

                return;
        }
    }

    private List<String> readLines(Part file)
            throws IOException {

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     file.getInputStream()))) {

            return reader.lines().toList();
        }
    }
}