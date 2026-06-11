package br.edu.ufrgs.util;

public class ClientValidator {
    
    public static String validate(String client){

        if (client == null) {
            throw new IllegalArgumentException(
                "Cliente não informado."
            );
        }

        client = client.trim();

        if (client.isEmpty()) {
            throw new IllegalArgumentException(
                "Nome do cliente vazio."
            );
        }

        return client;
    }
}
