package br.edu.ufrgs.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@ExtendWith(MockitoExtension.class)
class UploadServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private UploadServlet servlet;

    // SUCCESS SCENARIO: VALID FILES
    @Test
    void shouldProcessFilesAndRedirectToResultsWhenDataIsValid() throws Exception {

        String validCompanyCsv = "parametro,valor\nfator_distancia_km,0.05\nfator_peso_kg,2.10\nmultiplicador_expresso,1.5\nprazo_base_dias,2\ndesconto_expresso_dias,1";
        String validOrderCsv = "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido\nORD-001,Loja Tech,450.0,2.5,NORMAL,2026-03-23";
        Part mockCompanyPart = createMockPart(validCompanyCsv);
        Part mockOrderPart = createMockPart(validOrderCsv);

        when(request.getPart("companyConfig")).thenReturn(mockCompanyPart);
        when(request.getPart("orderList")).thenReturn(mockOrderPart);
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/app");

        org.mockito.Mockito.lenient().when(request.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute(eq("company"), any());
        verify(session).setAttribute(eq("orders"), any());
        verify(session).setAttribute(eq("freights"), any());
        verify(response).sendRedirect("/app/results");
    }

    //ERROR SCENARIO: BUSINESS RULE EXCEPTION (Invalid CSV)
    @Test
    void shouldForwardToIndexWithErrorMessageWhenCsvIsInvalid() throws Exception{
    
        String invalidCompanyCsv = "parametro,valor\nlinha_errada,texto";
        String validOrderCsv = "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido\nORD-001,Loja Tech,450.0,2.5,NORMAL,2026-03-23";
        Part mockCompanyPart = createMockPart(invalidCompanyCsv);
        Part mockOrderPart = createMockPart(validOrderCsv);

        when(request.getPart("companyConfig")).thenReturn(mockCompanyPart);
        when(request.getPart("orderList")).thenReturn(mockOrderPart);
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("errorMessage"), anyString());
        verify(dispatcher).forward(request, response);
    }

    //ERROR SCENARIO: SYSTEM/INTERNAL FAILURE
    @Test
    void shouldForwardToIndexWithGenericErrorWhenSystemFails() throws Exception {

        String validCompanyCsv = "parametro,valor\nfator_distancia_km,0.05\nfator_peso_kg,2.10\nmultiplicador_expresso,1.5\nprazo_base_dias,2\ndesconto_expresso_dias,1";
        String validOrderCsv = "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido\nORD-001,Loja Tech,450.0,2.5,NORMAL,2026-03-23";
        
        Part mockCompanyPart = createMockPart(validCompanyCsv);
        Part mockOrderPart = createMockPart(validOrderCsv);

        when(request.getPart("companyConfig")).thenReturn(mockCompanyPart);
        when(request.getPart("orderList")).thenReturn(mockOrderPart);
        when(request.getSession()).thenThrow(new RuntimeException("Simulando queda do servidor"));
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("errorMessage", "Erro interno do sistema.");
        verify(dispatcher).forward(request, response);
    }

    //HELPER METHOD
    private Part createMockPart(String fileContent) throws IOException{
        Part part = mock(Part.class);
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        when(part.getInputStream()).thenReturn(inputStream);
        return part;
    }
}