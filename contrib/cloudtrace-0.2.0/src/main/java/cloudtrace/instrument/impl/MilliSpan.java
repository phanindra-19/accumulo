package cloudtrace.instrument.impl;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cloudtrace.instrument.Span;
import cloudtrace.instrument.Tracer;

/**
 * A Span implementation that stores its information in milliseconds since the epoch.
 */
public class MilliSpan implements Span {

    private static final Random next = new SecureRandom();
    private long start;
    private long stop;
    final private Span parent;
    final private String description;
    final private long spanId;
    private Map<String, String> traceInfo = null;
    
    public Span child(String description) {
        return new MilliSpan(description, next.nextLong(), this);
    }
    
    public MilliSpan(String description, long id, Span parent) {
        this.description = description;
        this.spanId = id;
        this.parent = parent;
        this.start = 0;
        this.stop = 0;
    }
    
    public synchronized void start() {
        if (start > 0)
            throw new IllegalStateException("Span for " + description + " has already been started");
        start = System.currentTimeMillis();
    }

    public synchronized void stop() {
        if (start == 0)
            throw new IllegalStateException("Span for " + description + " has not been started");
        stop = System.currentTimeMillis();
        Tracer.getInstance().pop(this);
    }
    
    protected long currentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    public synchronized boolean running() {
        return start != 0 && stop == 0;
    }

    public synchronized long accumulatedMillis() {
        if (start == 0)
            return 0;
        if (stop > 0)
            return stop - start;
        return currentTimeMillis() - start;
    }

    public String toString() {
        long parentId = parentId();
        return ("\"" + description() + "\" trace:" +
                Long.toHexString(traceId()) + 
                " span:" + spanId + 
                (parentId > 0 ? " parent:" + parentId : "") + 
                " start:" + start + 
                " ms: " + Long.toString(accumulatedMillis()) + 
                (running() ? "..." : ""));
        
    }
    
    public String description() {
        return description;
    }

    @Override
    public long spanId() {
        return spanId;
    }

    @Override
    public Span parent() {
        return parent;
    }

    @Override
    public long parentId() {
        if (parent == null)
            return -1;
        return parent.spanId();
    }

    @Override
    public long traceId() {
        return parent.traceId();
    }

    @Override
    public long getStartTimeMillis() {
        return start;
    }

    @Override
    public long getStopTimeMillis() {
        return stop;
    }

    @Override
    public void data(String key, String value) {
        if (traceInfo == null)
            traceInfo = new HashMap<String, String>();
        traceInfo.put(key, value);
    }
    
    @Override
    public Map<String, String> getData() {
        if (traceInfo == null)
            return Collections.emptyMap();
        return Collections.unmodifiableMap(traceInfo);
    }
}