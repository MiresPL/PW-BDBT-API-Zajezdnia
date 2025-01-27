package com.mires.bdbt.zajezdnia.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    // Handle 400 - Bad Request
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(BadRequestException ex, Model model, HttpServletRequest request) {
        model.addAttribute("errorMessage", "Nieprawidłowe żądanie");
        model.addAttribute("request", request);
        return "error/Error"; // Return the error page view
    }

    // Handle 401 - Unauthorized
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorized(HttpClientErrorException.Unauthorized ex, Model model, HttpServletRequest request) {
        model.addAttribute("errorMessage", "Brak autoryzacji");
        model.addAttribute("request", request);
        return "error/Error"; // Return the error page view
    }

    // Handle 403 - Forbidden
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleForbidden(HttpClientErrorException.Forbidden ex, Model model, HttpServletRequest request) {
        model.addAttribute("errorMessage", "Brak dostępu");
        model.addAttribute("request", request);
        return "error/Error"; // Return the error page view
    }

    // Handle 404 - Not Found
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(NoResourceFoundException ex, Model model, HttpServletRequest request) {
        model.addAttribute("errorMessage", "Nie znaleziono strony");
        model.addAttribute("request", request);
        return "error/Error"; // Return the error page view
    }

    // Handle 500 - Internal Server Error
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleInternalServerError(HttpServerErrorException.InternalServerError ex, Model model, HttpServletRequest request) {
        model.addAttribute("errorMessage", "Wystąpił wewnętrzny błąd serwera!");
        model.addAttribute("request", request);
        return "error/Error"; // Return the error page view
    }

    // Fallback for generic exceptions (500 by default)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericError(Exception ex, Model model, HttpServletRequest request) {
        model.addAttribute("errorMessage", "Wystąpił błąd");
        model.addAttribute("request", request);
        return "error/Error"; // Return the error page view
    }
}
