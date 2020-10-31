package com.oracle;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StackWalkerDemo {

    public void methodOne() {
        this.methodTwo();
    }

    public void methodTwo() {
        this.methodThree();
    }

    public void methodThree() {

        //Capture the Entire Stack Trace :
        entireStack();

        //Filtering the StackFrames :
//        filterFrames();

        //Capturing the Reflection Frames :
//        reflectionFrames();


        //Capturing Hidden Frames :
//        hiddenFrames();

        //Identifying the Calling Class :
//        identifyingCallingClass();


        System.out.println("StackTraceElement : ");
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()){
            System.out.println(stackTraceElement);
        }
    }

    //Capture the Entire Stack Trace
    public void entireStack(){

        List<StackWalker.StackFrame> stackTrace = StackWalker.getInstance()
                .walk(this::walkExample);
        System.out.println("StackWalker : ");
        for (StackWalker.StackFrame stackFrame: stackTrace){
            System.out.println(stackFrame);
        }
    }

    //Filtering the StackFrames
    public void filterFrames(){

        List<StackWalker.StackFrame> stackTrace = StackWalker.getInstance()
                .walk(this::walkExample2);
        System.out.println("StackWalker : ");
        for (StackWalker.StackFrame stackFrame: stackTrace){
            System.out.println(stackFrame);
        }
    }

    //Capturing the Reflection Frames :
    public void reflectionFrames(){
        /*
            In order to capture the reflection frames, which are hidden by default,
            the StackWalker needs to be configured with an additional option SHOW_REFLECT_FRAMES.
            Using this option, all the reflections frames including Method.invoke()
            and Constructor.newInstance() will be captured.
         */
        List<StackWalker.StackFrame> stackTrace = StackWalker
                .getInstance(StackWalker.Option.SHOW_REFLECT_FRAMES)
                .walk(this::walkExample);
        System.out.println("StackWalker : ");
        for (StackWalker.StackFrame stackFrame: stackTrace){
            System.out.println(stackFrame);
        }
    }

    //Capturing Hidden Frames :
    public void hiddenFrames (){
        /*
            Note that we are assigning a lambda reference to a Runnable in this example.
            The only reason is that JVM will create some hidden frames for the lambda expression.

            SHOW_HIDDEN_FRAMES is a superset of SHOW_REFLECT_FRAMES.
         */
        Runnable r = () -> {
            List<StackWalker.StackFrame> stackTrace = StackWalker
                    .getInstance(StackWalker.Option.SHOW_HIDDEN_FRAMES)
                    .walk(this::walkExample);
            System.out.println("StackWalker : ");
            for (StackWalker.StackFrame stackFrame: stackTrace){
                System.out.println(stackFrame);
            }
        };
        r.run();
    }

    //Identifying the Calling Class
    public void identifyingCallingClass(){
        Class<?> caller = StackWalker
                .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass();
        System.out.println("StackWalker : ");
        System.out.println(caller.getCanonicalName());
        /*
            Please note that the StackWalker::getCallerClass should not be called from
            the method at the bottom of the stack. as it will result in IllegalCallerException being thrown.
         */
    }

    public List<StackWalker.StackFrame> walkExample(Stream<StackWalker.StackFrame> stackFrameStream) {
        return stackFrameStream.collect(Collectors.toList());
    }

    // Filtering the StackFrames :
    public List<StackWalker.StackFrame> walkExample2(Stream<StackWalker.StackFrame> stackFrameStream) {
        return stackFrameStream.filter(f -> f.getClassName().contains("com.oracle"))
                .collect(Collectors.toList());
    }


}

public class Main {

    public static void main(String[] args) {
        new StackWalkerDemo().methodOne();
    }
}
