package com.mynotes.grpc.demo.mainapi.service;

import com.mynotes.grpc.demo.mainapi.dto.AuthorDTO;
import com.mynotes.grpc.demo.mainapi.dto.CalculateResponseDTO;
import com.mynotes.grpc.demo.mainapi.dto.PostDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JsonService {

    private RestTemplate restTemplate;
    private URI uri;

    public JsonService() throws URISyntaxException {
        restTemplate = new RestTemplate();
        uri = new URI("http://localhost:" + 8081);
    }

    public CalculateResponseDTO calculate(long input) {
        return restTemplate.getForObject(uri + "/calculate/" + input, CalculateResponseDTO.class);
    }

    public PostDTO[] posts(long limit) {
        PostDTO[] result = restTemplate.getForObject(uri + "/blog/posts/" + limit, PostDTO[].class);
        for (int i = 0; i < result.length; i++) {
            PostDTO temp = result[i];
            AuthorDTO authorDTO = restTemplate.getForObject(uri + "/blog/author/" + temp.getAuthorId(), AuthorDTO.class);
            temp.setAuthor(authorDTO);
        }
        return result;
    }

}
