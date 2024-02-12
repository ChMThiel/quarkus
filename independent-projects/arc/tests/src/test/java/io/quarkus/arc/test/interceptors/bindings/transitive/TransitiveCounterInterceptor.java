package io.quarkus.arc.test.interceptors.bindings.transitive;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import io.quarkus.arc.ArcInvocationContext;

@Interceptor
@Priority(2)
@SomeAnnotation // this is transitive binding, it also brings in @CounterBinding
public class TransitiveCounterInterceptor {

    public static int timesInvoked = 0;

    public static Set<Annotation> lastBindings = new HashSet<>();

    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        // it should effectively interrupt all that CounterInterceptor does
        timesInvoked++;
        lastBindings = (Set<Annotation>) context.getContextData().get(ArcInvocationContext.KEY_INTERCEPTOR_BINDINGS);
        return context.proceed();
    }
}
