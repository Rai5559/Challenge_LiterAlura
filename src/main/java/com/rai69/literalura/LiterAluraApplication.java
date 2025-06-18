package com.rai69.literalura;

import com.rai69.literalura.controller.AuthorController;
import com.rai69.literalura.controller.BookController;
import com.rai69.literalura.util.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication {
    public static void main(String[] args) {
        // Carga de variables de entorno personalizada
        EnvLoader envLoader = new EnvLoader();
        ApplicationContext context = SpringApplication.run(LiterAluraApplication.class, args);

        Scanner sca = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Menú Principal --- \n");
            System.out.println("Seleccione una opción: /n");
            System.out.println("1. Buscar libro");
            System.out.println("2. Libros por idioma");
            System.out.println("3. Listar libros guardados");
            System.out.println("4. Buscar autor");
            System.out.println("5. Buscar autores por año");
            System.out.println("0. Salir");
            System.out.println("\n--- Menú Principal ---");
            String opcion = sca.nextLine();
            switch (opcion) {
                case "1":
                    BookController bookController = context.getBean(BookController.class);
                    System.out.print("Ingrese el nombre del libro: ");
                    String query = sca.nextLine();
                    String result = bookController.fetchAndSaveBook(query, 0);
                    System.out.println(result);
                    break;
                case "2":
                    BookController bookByLanguageController = context.getBean(BookController.class);
                    System.out.print("Ingrese el idioma del libro (en, es, fr): ");
                    String language = sca.nextLine();
                    if (language.isEmpty()  || (!language.equalsIgnoreCase("en") && !language.equalsIgnoreCase("es") && !language.equalsIgnoreCase("fr"))) {
                        System.out.println("Debe ingresar un idioma válido.");
                        continue;
                    }
                    String booksByLanguage = bookByLanguageController.getBooksByLanguage(language);
                    System.out.println("Libros en el idioma " + language + ":");
                    System.out.println(booksByLanguage);
                    break;
                case "3":
                    BookController bookListController = context.getBean(BookController.class);
                    String allBooks = bookListController.getAllBooks();
                    System.out.println("Libros guardados:");
                    System.out.println(allBooks);
                    break;
                case "4":
                    AuthorController authorController = context.getBean(AuthorController.class);
                    System.out.print("Ingrese el nombre del autor: ");
                    String authorName = sca.nextLine();
                    Object authorResult = authorController.getAuthor(authorName);
                    System.out.println(authorResult);
                    break;
                case "5":
                    AuthorController authorYearController = context.getBean(AuthorController.class);
                    System.out.print("Ingrese el año: ");
                    String year = sca.nextLine();
                    Object authorYearResult = authorYearController.getAuthorByYear(year);
                    System.out.println(authorYearResult);
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    sca.close();
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
