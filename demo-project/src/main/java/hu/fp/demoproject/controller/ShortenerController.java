package hu.fp.demoproject.controller;

import hu.fp.demoproject.service.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ShortenerController {

    @Autowired
    ShortenerService service;

    @GetMapping("shorten/{url}")
    public String shorten(@PathVariable String url) {
        return service.shorten(url);
    }

    @GetMapping("/${app.base_path}/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        System.out.println(shortUrl);
        boolean successful = shortUrl.length() <= 8;
        String url = "";
        try {
            url = service.decode(shortUrl);
        } catch (Exception ex) {
            successful = false;
        }

        if (successful) {
            response.sendRedirect(url);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid short URL in path.");
        }
    }
}
