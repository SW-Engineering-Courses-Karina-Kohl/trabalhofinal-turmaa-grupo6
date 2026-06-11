package br.edu.ufrgs.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.ufrgs.model.Freight;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class DownloadServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private DownloadServlet servlet;

    //SUCCESS SCENARIO: VALID SESSION AND FREIGHTS PRESENT
@Test
    void shouldSetHttpHeadersAndWriteCsvToOutputStreamWhenDataIsValid() throws Exception {
        List<Freight> mockFreights = new ArrayList<Freight>() {
            @Override
            public boolean isEmpty() { return false; } 
            
            @Override
            public java.util.Iterator<Freight> iterator() { 
                return java.util.Collections.emptyIterator(); 
            } 
        };

        class DummyOutputStream extends ServletOutputStream {
            boolean isFlushed = false;
            
            @Override
            public void write(int b) {} 
            
            @Override
            public boolean isReady() { return true; }
            
            @Override
            public void setWriteListener(jakarta.servlet.WriteListener listener) {}
            
            @Override
            public void flush() { this.isFlushed = true; }
        }
        
        DummyOutputStream dummyOutputStream = new DummyOutputStream();

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("freights")).thenReturn(mockFreights);
        when(response.getOutputStream()).thenReturn(dummyOutputStream);

        servlet.doGet(request, response);

        verify(response).setContentType("text/csv");
        verify(response).setHeader("Content-Disposition", "attachment; filename=\"logistica_finalizada.csv\"");
        
        org.junit.jupiter.api.Assertions.assertTrue(dummyOutputStream.isFlushed);
    }

    //EDGE SCENARIO: REDIRECT WHEN SESSION IS NULL
    @Test
    void shouldRedirectToResultsWhenSessionIsNull() throws Exception{

        when(request.getSession(false)).thenReturn(null);
        when(request.getContextPath()).thenReturn("/app");

        servlet.doGet(request, response);

        verify(response).sendRedirect("/app/results");
        verify(response, never()).setContentType(anyString());
    }

    //EDGE SCENARIO: REDIRECT WHEN FREIGHT LIST IS NULL
    @Test
    void shouldRedirectToResultsWhenFreightListIsNull() throws Exception{

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("freights")).thenReturn(null);
        when(request.getContextPath()).thenReturn("/app");

        servlet.doGet(request, response);

        verify(response).sendRedirect("/app/results");
        verify(response, never()).setContentType(anyString());
    }

    //EDGE SCENARIO: REDIRECT WHEN FREIGHT LIST IS EMPTY
    @Test
    void shouldRedirectToResultsWhenFreightListIsEmpty() throws Exception {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("freights")).thenReturn(new ArrayList<Freight>());
        when(request.getContextPath()).thenReturn("/app");

        servlet.doGet(request, response);

        verify(response).sendRedirect("/app/results");
        verify(response, never()).setContentType(anyString());
    }
}