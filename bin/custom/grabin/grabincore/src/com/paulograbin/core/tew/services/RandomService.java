package com.paulograbin.core.tew.services;

public interface RandomService {

    boolean randomBoolean();

    Integer getRandomNumber();

    Integer getRandomNumber(int upperLimit);

    String makeRandomString();
}
