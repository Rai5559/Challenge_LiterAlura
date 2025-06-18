package com.rai69.literalura.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rai69.literalura.dto.BookDTO;
import com.rai69.literalura.dto.AuthorDTO;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiReq {
    public static final String GUTENDEX_SEARCH_URL = "https://gutendex.com/books?search=";
    private static final ObjectMapper mapper = new ObjectMapper();

    public BookDTO getSingleBook(String query, int index) {
        try {
            String jsonResponse = getBook(query);
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode resultsNode = rootNode.path("results");
            validateResults(resultsNode, query, index);
            JsonNode bookNode = resultsNode.get(index);
            return parseBookNode(bookNode);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener libro: " + e.getMessage(), e);
        }
    }

    private String getBook(String query) throws IOException, InterruptedException {
        String url = GUTENDEX_SEARCH_URL + URLEncoder.encode(query, StandardCharsets.UTF_8);
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private void validateResults(JsonNode resultsNode, String query, int index) {
        if (!resultsNode.isArray() || resultsNode.isEmpty()) {
            throw new RuntimeException("No se encontraron libros para: " + query);
        }
        if (index < 0 || index >= resultsNode.size()) {
            throw new RuntimeException("Índice " + index + " fuera de rango. Solo hay " + resultsNode.size() + " resultados disponibles");
        }
    }

    private BookDTO parseBookNode(JsonNode bookNode) {
        if (bookNode == null) {
            throw new RuntimeException("El libro en la posición es nulo");
        }
        AuthorDTO author = parseAuthor(bookNode.path("authors"));
        String language = parseLanguage(bookNode.path("languages"));
        String title = bookNode.path("title").asText();
        int downloadCount = bookNode.path("download_count").asInt();
        return new BookDTO(title, author, language, downloadCount);
    }

    private AuthorDTO parseAuthor(JsonNode authorsNode) {
        if (authorsNode.isArray() && authorsNode.size() > 0) {
            JsonNode authorNode = authorsNode.get(0);
            String name = authorNode.path("name").asText();
            Integer birthYear = authorNode.has("birth_year") && !authorNode.get("birth_year").isNull() ? authorNode.get("birth_year").asInt() : null;
            Integer deathYear = authorNode.has("death_year") && !authorNode.get("death_year").isNull() ? authorNode.get("death_year").asInt() : null;
            return new AuthorDTO(name, birthYear, deathYear);
        }
        return null;
    }

    private String parseLanguage(JsonNode languagesNode) {
        if (languagesNode.isArray() && languagesNode.size() > 0) {
            return languagesNode.get(0).asText();
        }
        return "N/I";
    }
}
