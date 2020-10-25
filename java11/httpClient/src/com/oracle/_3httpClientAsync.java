package com.oracle;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class _3httpClientAsync {
    public static void main(String[] args) throws URISyntaxException, ExecutionException, InterruptedException {

        /*
            New API also support Async HTTP requests using httpClient.sendAsync() method.
            It returns CompletableFuture object which can be used to determine whether the request
            has been completed or not. It also provide you access to the HttpResponse once request
            is completed. Best part is that if you desire you can even cancel the request before it completes.
         */

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder(new URI("https://httpbin.org/get?name=mkyong1"))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> httpResponseAsync =
                httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                        .thenApply((response) -> {

                            final Logger logger = Logger.getLogger(_3httpClientAsync.class.getName());
                            logger.setLevel(Level.INFO);
                            logger.info(String.valueOf(response.statusCode()));
                            logger.info(String.valueOf(response.headers()));
                            logger.info(response.body());

                            return response;
                        });

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        if (httpResponseAsync.isDone()){
            System.out.println(httpResponseAsync.get().statusCode());
            System.out.println(httpResponseAsync.get().body());
        }else {
            System.out.println("request is failed");
            httpResponseAsync.cancel(true);
        }
    }
}
