package com.flipkart.poseidon.rotation;

import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shrey.garg on 2019-06-16.
 */
@Component
public class OutOfRotationServlet extends HttpServlet {
    @Language("JSON")
    private static final String message = "{\"message\": \"Taken out of Rotation\"}";

    private final RotationManager manager;

    @Autowired
    public OutOfRotationServlet(RotationManager manager) {
        this.manager = manager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "must-revalidate,no-cache,no-store");

        manager.oor();
        resp.setStatus(200);
        try (ServletOutputStream output = resp.getOutputStream()) {
            output.print(message);
        }
    }
}
