package com.oracle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _1httpClient {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        /*
            There are two ways to create an HttpClient :
            HttpClient.newHttpClient();
            HttpClient.newBuilder().build();
         */

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest
                .newBuilder(new URI("https://httpbin.org/get?name=mkyong1"))
                .version(HttpClient.Version.HTTP_2) // this is default
                .GET() // this is default
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();
        /*
            When HTTP/2 is specified, the first request to an origin server will try to use it.
            If the server supports the new protocol version, then the response will be sent
            using that version. All subsequent requests/responses to that server will use HTTP/2.
            If the server does not supports HTTP/2, then HTTP/1.1 will be used.
        */

        HttpResponse<InputStream> httpResponse = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());

        /*
            Responses are handled by implementations of the HttpResponse.BodyHandler interface.
            A number of predefined body handler factory methods are provided in
            the HttpResponse.BodyHandlers class.
            stream returned by ofInputStream() should be read in its entirety.

            HttpResponse.BodyHandler a functional interface that returns a BodySubscriber,
            which itself handles consuming the response body.

            HttpResponse.BodySubscriber subscribes for the response body and consumes its
            bytes into some other form (a string, a file, or some other storage type).
         */

        System.out.println("request method : " + httpRequest.method());
        System.out.println("response status code : " + httpResponse.statusCode());
        System.out.println("response body : " + httpResponse.body());

        InputStream inputStream = httpResponse.body();
        int c = 0;
        while ( (c = inputStream.read()) != -1 ){
            System.out.print( (char) c );
        }

        HttpHeaders httpHeaders = httpResponse.headers();
        Map<String, List<String>> httpHeadersMap = httpHeaders.map();
        Set<String> httpHeadersField = httpHeaders.map().keySet();

        System.out.println();
        System.out.println("headers map : " + httpHeadersMap);
        System.out.println("headers map field : " + httpHeadersField);

    }
}
