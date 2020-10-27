package com.oracle;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.http.HttpClient;

public class _9httpClientCookie {
    public static void main(String[] args) {
        /*
            cookies are a way to add state to HTTP, which by design, is a stateless protocol.
            However, with Cookies, the server can associate multiple requests to the same session.

            Cookies are, from a technical standpoint, just HTTP headers:
            Cookie (request) and Set-Cookie (response). However, they are treated specially by the browsers.
            When a server wants to set a cookie, he adds the Set-Cookie header to the response.
            The client reads the value of this header and sends it in the Cookie header
            with each consecutive request back to the server.

            The Java 11 HttpClient has built-in cookie support, but it's disabled by default.
         */

        CookieHandler.setDefault(new CookieManager());
        HttpClient httpClient = HttpClient.newBuilder()
                .cookieHandler(CookieHandler.getDefault())
//                .cookieManager(new CookieManager(null, CookiePolicy.ACCEPT_NONE)) // doesn't allow to accept cookies at all
                .build();

        /*
            The default constructor creates a cookie manager that stores all cookies in RAM.
            You can change this behavior by instantiating the manager with the other constructor
            (CookieManager(CookieStore store, CookiePolicy cookiePolicy)) and specify an
            implementation of the CookieStore interface.
         */

    }
}
