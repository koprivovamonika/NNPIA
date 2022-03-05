package com.example.nnpia_cv03;

import org.springframework.stereotype.Service;

@Service
public class CounterService implements ICounterService {
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
