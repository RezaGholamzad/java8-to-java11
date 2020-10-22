package com.oracle.methods;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class _9allOfMethod {

    static CompletableFuture<String> downloadWebPage(String pageLink){
        return CompletableFuture.supplyAsync(() -> {
            // Code to download and return the web page's content
            return "CompletableFuture";
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
            We used thenCompose() and thenCombine() to combine two CompletableFutures together.
            Now, what if you want to combine an arbitrary number of CompletableFutures? Well,
            you can use the following methods to combine any number of CompletableFutures.

            CompletableFuture.allOf is used in scenarios when you have a List of independent
            futures that you want to run in parallel and do something after all of them are complete.

            Let’s say that you want to download the contents of 100 different web pages of a website.
            You can do this operation sequentially but this will take a lot of time.
            So, you have written a function which takes a web page link, and returns
            a CompletableFuture, i.e. It downloads the web page’s content asynchronously.

            Now, when all the web pages are downloaded, you want to count the number
            of web pages that contain a keyword - ‘CompletableFuture’.
            Let’s use CompletableFuture.allOf() to achieve this
         */

        // A list of 100 web page links
        List<String> webPageLinks = Arrays.asList("oracle.com", "java.com");

        // Download contents of all the web pages asynchronously and
        List<CompletableFuture<String>> pageContentFutures = webPageLinks
                .stream()
                .map(_9allOfMethod::downloadWebPage)
                .collect(Collectors.toList());

        // Create a combined Future using allOf()
        CompletableFuture<Void> allFutures = CompletableFuture
                .allOf(pageContentFutures
                        .toArray(CompletableFuture[]::new));

        /*
            The problem with CompletableFuture.allOf() is that it returns CompletableFuture<Void>.
            But we can get the results of all the wrapped CompletableFutures by writing
            few additional lines of code

            When all the Futures are completed, call `future.join()` to get their results and
            collect the results in a list
         */
        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> {
            return pageContentFutures
                    .stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());

        });

        /*
            Take a moment to understand the above code snippet.
            Since we’re calling future.join() when all the futures are complete,
             we’re not blocking anywhere

            The join() method is similar to get().
            The only difference is that it throws an unchecked exception
            if the underlying CompletableFuture completes exceptionally.

            Let’s now count the number of web pages that contain our keyword
         */

        // Count the number of web pages having the "CompletableFuture" keyword.
        CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> {
           return pageContents
                   .stream()
                   .filter(pageContent -> pageContent.contains("CompletableFuture"))
                   .count();
        });

        System.out.println("Number of Web Pages having CompletableFuture keyword - " +
                countFuture.get());

    }
}
