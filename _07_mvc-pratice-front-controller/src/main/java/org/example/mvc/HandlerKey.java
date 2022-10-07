package org.example.mvc;

import org.example.mvc.controller.RequestMathod;

import java.util.Objects;

public class HandlerKey {

    private final RequestMathod requestMathod;
    private final String uriPath;

    public HandlerKey(RequestMathod requestMathod, String uriPath) {
        this.requestMathod = requestMathod;
        this.uriPath = uriPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerKey that = (HandlerKey) o;
        return requestMathod == that.requestMathod && Objects.equals(uriPath, that.uriPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestMathod, uriPath);
    }

}
