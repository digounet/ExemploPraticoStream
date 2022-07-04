package com.example.exemplopraticostream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BikeController {
    @GetMapping(value = "api/bike")
    public List<String> getFile() throws IOException {
        var response = new ArrayList<String>();
        var path = Paths.get("nomes.txt");
        var reader = Files.newBufferedReader(path);
        String line;
        while ((line = reader.readLine()) != null) {
            response.add(line);
        }

        return response;

    }

    @GetMapping(value = "api/bike-streams")
    public Flux<String> getFileAsStream() throws IOException {
        var path = Paths.get("nomes.txt");
        var fileStream = Files.lines(path);
        var fluxStream = Flux.fromStream(fileStream)
                .delaySequence(Duration.ofSeconds(1));

        return fluxStream;

    }

}
