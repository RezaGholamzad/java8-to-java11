# Introducing java.net.http : 

beginning with JDK 11, a new networking package called java.net.http, in the module java.net.http, 
has been added. It provides enhanced, updated networking support for HTTP clients. This new APIis 
generally referred to as the HTTP Client API.For many types of HTTP networking, the capabilities 
defined by the API in java.net.http can provide superior solutions. In addition to offering a streamlined, 
easy-to-use API, other advantages include support for asynchronous communication, HTTP/2, 
and flow control. In general, the HTTP Client API is designed as a superior alternative to the 
functionality provided by HttpURLConnection. It also supports the WebSocket protocol for 
bidirectional communication.

## Three Key Elements : 

### HttpClient : 

HttpClient encapsulates the HTTP request/response mechanism. 
It supports both synchronous and asynchronous communication.

HttpClient is an abstract class, and instances are not created via a 
public constructor. Rather, you will use a factory method to build one. 
HttpClient supports builders with the HttpClient.Builder interface, which provides several methods 
that let you configure the HttpClient. To obtain an HttpClient builder,use the newBuilder() static method. 
It returns a builder that lets you configure the HttpClient that it will create. Next, 
call build() on the builder. It creates and returns the HttpClient instance.

HttpClient.Builder defines a number of methods that let you configure the builder. 
By default, redirects are not followed. You can change this by calling followRedirects(), 
passing in the new redirect setting, which must be a value in the HttpClient.Redirect enumeration. 
It defines the following values: ALWAYS, NEVER, and NORMAL. 
The first two are self explanatory. The NORMAL setting causes redirects to be followed 
unless a redirect is from an HTTPS site to an HTTP site. 
For example, this creates a builder in which the redirect policy is NORMAL. It then uses that builder 
to construct an HttpClient.

`HttpClient.Builder myBuilder =  HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL);`

`HttpClient.myHC = myBuilder.build();`

Among others, builder configuration settings include authentication, proxy,HTTP version, and priority. 
Therefore, you can build an HTTP client to fit virtually any need.In cases in which the default 
configuration is sufficient, you can obtain a default HttpClient directly by calling 
the newHttpClient() method.

### HttpRequest :

The HTTP Client API encapsulates requests in the HttpRequest abstract class.
To create an HttpRequest object, you will use a builder. 
To obtain a builder,call newBuilder().

HttpRequest.Builder lets you specify various aspects of the request, 
such as what request method to use. (The default is GET.) You can also set header information, 
the URI, and the HTTP version, among others. Aside from the URI,often the default settings are sufficient. 
You can obtain a string representation of the request method by calling method() on the HttpRequest object.
To actually construct a request, call build() on the builder instance.

### HttpResponse :

The HTTP Client API encapsulates a response in an implementation of theHttpResponse interface. 
It is a generic interface declared like this:

`HttpResponse<T>`

When a request is sent, an HttpResponse instance is returned that contains the response. 
HttpResponse defines several methods that give you access to the information in the response.
Arguably, the most important is body(), A reference to the body is returned. 
The specific type of reference is determined by the type of T, which is specified by the body 
handler specified by the send() method.You can obtain the status code associated with 
the response by calling statusCode().
Another method in HttpResponse is headers(), which obtains the response headers. It is shown here:

`HttpHeaders headers()`

The headers associated with the response are encapsulated in an instance of the HttpHeaders class. 
It contains various methods that give you access to the headers. 
The one used is map(), shown here:

`Map<String, List<String>> map()`

It returns a map that contains all of the header fields and values.


