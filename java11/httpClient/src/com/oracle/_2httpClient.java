package com.oracle;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
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

            The default settings HttpClient include:

                prefer HTTP/2
                no connection timeout
                redirection policy of NEVER
                no cookie handler
                no authenticator
                default thread pool executor
                default proxy selector
                default SSL context
         */
        HttpClient httpClient = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
//                .proxy(ProxySelector.of(new InetSocketAddress("proxyHost", 8080)))
//                .sslContext(SSLContext.getDefault())
//                .sslParameters(new SSLParameters())
                .connectTimeout(Duration.ofSeconds(20))
                .followRedirects(HttpClient.Redirect.NEVER) // disable redirect
                .build();
        /*
            connectTimeout() determines how long the client waits until a connection can be established.
            If the connection can't be established, the client throws a HttpConnectTimeoutException exception.
         */

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
