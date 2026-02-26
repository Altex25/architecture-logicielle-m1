package org.example.loan.client;

import org.example.loan.exception.RemoteServiceException;
import org.example.loan.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    public UserClient(RestTemplate restTemplate, @Value("${user.service.url}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
    }

    public UserPayload findById(Long userId) {
        String url = userServiceUrl + "/api/users/{id}";
        try {
            UserPayload payload = restTemplate.getForObject(url, UserPayload.class, userId);
            if (payload == null) {
                throw new RemoteServiceException("User service returned an empty response", null);
            }
            return payload;
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        } catch (RestClientException ex) {
            throw new RemoteServiceException("Cannot reach user-service", ex);
        }
    }

    public static class UserPayload {

        private Long id;
        private String name;
        private String email;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
