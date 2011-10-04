package cloudtrace.instrument.thrift;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cloudtrace.instrument.Span;
import cloudtrace.instrument.Trace;
import cloudtrace.instrument.Tracer;
import cloudtrace.thrift.TInfo;

/**
 * To move trace data from client to server, the RPC call must be annotated to take a TInfo object as its first argument.
 * The user can simply pass null, so long as they wrap their Client and Service objects with these functions.
 * 
 * <pre>
 * Trace.on("remoteMethod");
 * Iface c = new Client();
 * c = TraceWrap.client(c);
 * c.remoteMethod(null, arg2, arg3);
 * Trace.off();
 * </pre>
 * 
 * The wrapper will see the annotated method and send or re-establish the trace information.
 * 
 * Note that the result of these calls is a Proxy object that conforms to the basic interfaces, but is not your concrete instance.
 * 
 */
public class TraceWrap {
    
    @SuppressWarnings("unchecked")
    public static <T> T service(final T instance) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object obj, Method method, Object[] args)
                    throws Throwable {
                if (args == null || args.length < 1 || args[0] == null || !(args[0] instanceof TInfo)) {
                    return method.invoke(instance, args);
                }
                Span span = Trace.trace((TInfo)args[0], method.getName());
                try {
                    return method.invoke(instance, args);
                } catch (InvocationTargetException ex) {
                    throw ex.getCause();
                } finally {
                    span.stop();
                }
            }
        };
        return (T) Proxy.newProxyInstance(
                instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(), 
                handler);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T client(final T instance) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object obj, Method method, Object[] args)
                    throws Throwable {
                if (args == null || args.length < 1 || args[0] != null) {
                    return method.invoke(instance, args);
                }
                Class<?> klass = method.getParameterTypes()[0];
                if (TInfo.class.isAssignableFrom(klass)) {
                    args[0] = Tracer.traceInfo();
                }
                Span span = Trace.start("client:" + method.getName());
                try {
                    return method.invoke(instance, args);
                } catch (InvocationTargetException ex) {
                    throw ex.getCause();
                } finally {
                    span.stop();
                }
            }
        };
        return (T) Proxy.newProxyInstance(
                instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(), 
                handler);
    }

}
