package com.project.auth.model;

public class EmailBody {
    public String createBody(String code){
        String body = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<h2>Olá,</h2>"
                + "<p>Aqui está o seu código de autenticação:</p>"
                + "<h1 style=\"font-size: 48px; color: #336699;\">" + code + "</h1>"
                + "<p>Atenciosamente,</p>"
                + "<p>Seu aplicativo</p>"
                + "</body>"
                + "</html>";

        return body;
    }
}
