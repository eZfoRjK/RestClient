import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.Arrays;

public class Main {
    static final String BASE_URL = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // get
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<User[]> responseEntity = restTemplate.exchange(BASE_URL, HttpMethod.GET, httpEntity, User[].class);
        Arrays.stream(responseEntity.getBody()).forEach(System.out::println);

        String sessionId = responseEntity.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        headers.add("Cookie", sessionId);

        // add new
        User newUser = new User(3L, "James", "Brown", (byte) 36);
        HttpEntity<User> requestAddUser = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> responseAddUser = restTemplate.exchange(BASE_URL, HttpMethod.POST, requestAddUser, String.class);
        sb.append(responseAddUser.getBody());
        System.out.println("Add user response: " + responseAddUser.getBody());

        // change update
        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        HttpEntity<User> requestUpdateUser = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> responseUpdateUser = restTemplate.exchange(BASE_URL, HttpMethod.PUT, requestUpdateUser, String.class);
        sb.append(responseUpdateUser.getBody());
        System.out.println("Update user response: " + responseUpdateUser.getBody());

        // delete
        ResponseEntity<String> responseDeleteUser = restTemplate
                .exchange(BASE_URL + "/{id}", HttpMethod.DELETE, new HttpEntity<>(headers), String.class, newUser.getId());
        sb.append(responseDeleteUser.getBody());
        System.out.println("Delete user response: " + responseDeleteUser.getBody());

        System.out.println("=========================================");

        // Вроде ОНО
        System.out.println("ОНО?????: " + sb.toString());
    }
}










