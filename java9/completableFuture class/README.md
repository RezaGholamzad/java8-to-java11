# CompletableFuture

CompletableFuture is used for asynchronous programming in Java. 
Asynchronous programming is a means of writing non-blocking code by running a task on a separate thread 
than the main application thread and notifying the main thread about its progress, completion or failure.

This way, your main thread does not block/wait for the completion of the task and 
it can execute other tasks in parallel.

## Future vs CompletableFuture : 

CompletableFuture is an extension to Java’s Future API which was introduced in Java 5.
A Future is used as a reference to the result of an asynchronous computation. 
It provides an isDone() method to check whether the computation is done or not, 
and a get() method to retrieve the result of the computation when it is done.

### Limitations of Future :

1) It cannot be manually completed :

    Let’s say that you’ve written a function to fetch the latest price of an e-commerce product from a remote API.
    Since this API call is time-consuming, you’re running it in a separate thread and 
    returning a Future from your function.
    Now, let’s say that If the remote API service is down, 
    then you want to complete the Future manually by the last cached price of the product.
    Can you do this with Future? No!

2) You cannot perform further action on a Future’s result without blocking :

    Future does not notify you of its completion. 
    It provides a get() method which blocks until the result is available.
    You don’t have the ability to attach a callback function to the Future and have 
    it get called automatically when the Future’s result is available.

3) Multiple Futures cannot be chained together :

    Sometimes you need to execute a long-running computation and when the computation is done, 
    you need to send its result to another long-running computation, and so on.
    You can not create such asynchronous workflow with Futures.

4) You can not combine multiple Futures together :

    Let’s say that you have 10 different Futures that you want to run in parallel and then 
    run some function after all of them completes. You can’t do this as well with Future.

5)  No Exception Handling : 

    Future API does not have any exception handling construct.

CompletableFuture implements Future and CompletionStage interfaces and provides a huge set of 
convenience methods for creating, chaining and combining multiple Futures. 
It also has a very comprehensive exception handling support.

## CompletableFuture Exception Handling : 

Let’s first understand how errors are propagated in a callback chain, 
Consider the following CompletableFuture callback chain : 

`CompletableFuture.supplyAsync(() -> {`

	`// Code which might throw an exception`
	
	`return "Some result";`
	
`}).thenApply(result -> {`

	`return "processed result";`
	
`}).thenApply(result -> {`

	`return "result after further processing";`
	
`}).thenAccept(result -> {`

	`// do something with the final result`
	
`});`

If an error occurs in the original supplyAsync() task, then none of the thenApply() callbacks 
will be called and future will be resolved with the exception occurred. 
If an error occurs in first thenApply() callback then 2nd and 3rd callbacks won’t be 
called and the future will be resolved with the exception occurred, and so on.

# ForkJoinPool : 

## commonPool Introduction :

ForkJoinPool#commonPool() is a static thread-pool, which is lazily initialized when is actually needed. 
A static commonPool() is available and appropriate for most applications. 
The common pool is used by any ForkJoinTask that is not explicitly submitted to a specified pool. 
Using the common pool normally reduces resource usage (its threads are slowly reclaimed during periods 
of non-use, and reinstated upon subsequent use). 
commonPool is a default thread pool for every ForkJoinTask.

Two major concepts use the commonPool inside JDK: 
CompletableFuture and  Parallel Streams.