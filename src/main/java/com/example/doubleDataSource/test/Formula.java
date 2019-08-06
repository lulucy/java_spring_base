package com.example.doubleDataSource.test;

public interface Formula {
    double caluate(int a);
    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
