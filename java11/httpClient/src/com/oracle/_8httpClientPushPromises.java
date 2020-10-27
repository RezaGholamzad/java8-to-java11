package com.oracle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class _8httpClientPushPromises {

    private static HttpResponse.PushPromiseHandler<String> pushPromiseHandler(){

        /*
            PushPromiseHandler is a functional interface.
            applyPushPromise method is abstract method of PushPromiseHandler.
            more details in java doc -> PushPromiseHandler + ctrl and enter
         */
        return (HttpRequest initiatingRequest, HttpRequest pushPromiseRequest,
                Function<HttpResponse.BodyHandler<String>, CompletableFuture<HttpResponse<String>>> acceptor) -> {
            acceptor.apply(HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response ->
                            System.out.println(" Pushed response: " + response.uri()
                                    + ", headers: " + response.headers())
                    );

            System.out.println("Promise request uri: " + pushPromiseRequest.uri());
            System.out.println("Promise request headers: " + pushPromiseRequest.headers());
        };
    }

    public static void main(String[] args) {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest pageRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://http2.golang.org/serverpush"))
                .build();

        httpClient.sendAsync(pageRequest, HttpResponse.BodyHandlers.ofString(), pushPromiseHandler())
                .thenAccept(pageResponse -> {
                    System.out.println("Page response status code: " + pageResponse.statusCode());
                    System.out.println("Page response headers: " + pageResponse.headers());
//                    System.out.println("Page response body: " + pageResponse.body());
                }).join();

    }
}
