package com.webservices.restfulwebservices.producer;

import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.producer.TextProducer;

public class MyTextProducer implements TextProducer {
    private static final int DEFAULT_LENGTH = 5;
    private static final char[] NUMBERS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a','b','c','d','e'};
    private final TextProducer _txtProd;

    public MyTextProducer() {
        this(5);
    }
    public MyTextProducer(int length) {
        this._txtProd = new DefaultTextProducer(length, NUMBERS);
    }
    @Override
    public String getText() {
        return this._txtProd.getText();
    }
}
