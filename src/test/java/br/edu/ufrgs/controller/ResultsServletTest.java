package br.edu.ufrgs.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.ufrgs.model.Freight;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class ResultsServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private ResultsServlet servlet;

    //SUCCESS SCENARIO: SESSION EXISTS
    @Test
    void shouldTransferFreightsToRequestAndForwardWhenSessionIsValid() throws Exception {
        
        List<Freight> mockFreights = new ArrayList<>();
        
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("freights")).thenReturn(mockFreights);
        when(request.getRequestDispatcher("/WEB-INF/results.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("freights", mockFreights);
        verify(dispatcher).forward(request, response);
    }

    //ERROR/EDGE SCENARIO: SESSION IS NULL
    @Test
    void shouldForwardWithoutTransferringDataWhenSessionIsNull() throws Exception {
        
        when(request.getSession(false)).thenReturn(null);
        when(request.getRequestDispatcher("/WEB-INF/results.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, never()).setAttribute(any(String.class), any());
        verify(dispatcher).forward(request, response);
    }
}