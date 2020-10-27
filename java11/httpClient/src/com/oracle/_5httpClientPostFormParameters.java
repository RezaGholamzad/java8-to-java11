package com.oracle;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class _5httpClientPostFormParameters {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    /*
        HttpRequest.BodyPublisher Subtype of Flow.Publisher.
        A BodyPublisher is used when you send a request with a request body.
        The BodyPublisher converts objects into a flow of byte buffers suitable for sending as a body.
     */
    public static HttpRequest.BodyPublisher ofFromData(Map<Object, Object> data){
        var builder = new StringBuilder();
        /*
            Map.Entry is a key and its value combined into one class.
            This allows you to iterate over Map.entrySet() instead of having to
            iterate over Map.keySet(), then getting the value for each key.
         */
        for (Map.Entry<Object, Object> entry : data.entrySet()){
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }

        /*
            HttpRequest.BodyPublishers Implementations of BodyPublisher that implement
            various useful publishers, such as publishing the request body from a String, or from a file.
         */
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        Map<Object, Object> data = new HashMap<>();
        data.put("username", "abc");
        data.put("password", "123");
        data.put("custom", "secret");
        data.put("ts", System.currentTimeMillis());

        HttpRequest request = HttpRequest.newBuilder(new URI("https://httpbin.org/post"))
                .POST(ofFromData(data)) // BodyPublishers.noBody() can be used where no request body is required.
//                .uri(URI.create("https://httpbin.org/post"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

    }
}
