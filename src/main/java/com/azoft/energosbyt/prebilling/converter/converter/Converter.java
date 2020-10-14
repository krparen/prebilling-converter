package com.azoft.energosbyt.prebilling.converter.converter;

public interface Converter<I, O> {
  public O convert (I input);
}
