package com.oracle;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class _2httpClient {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        /*
            There are two ways to create an HttpClient :
            HttpClient.newHttpClient();
            HttpClient.newBuilder().build();
         */

        HttpClient httpClient = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
//                .proxy(ProxySelector.of(new InetSocketAddress("proxyHost", 8080)))
                .connectTimeout(Duration.ofSeconds(20))
                .followRedirects(HttpClient.Redirect.NEVER) // disable redirect
                .build();

        HttpRequest httpRequest = HttpRequest
                .newBuilder(new URI("https://httpbin.org/get?name=mkyong1"))
                .version(HttpClient.Version.HTTP_2) // this is default
                .GET() // this is default
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();

        HttpResponse<String> httpResponse = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("request method : " + httpRequest.method());
        System.out.println("response status code : " + httpResponse.statusCode());
        System.out.println("response body : " + httpResponse.body());
    }
}
