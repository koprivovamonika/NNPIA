package com.example.nnpia_cv03;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCounterService implements ICounterService{
    private Integer counter = 0;

    @Override
    public void add(){
        this.counter++;
    }

    @Override
    public Object get() {
        return counter;
    }
}
