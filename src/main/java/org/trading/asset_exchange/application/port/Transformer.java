package org.trading.asset_exchange.application.port;

public interface Transformer<I,O> {

  public O transform(I input);

  public I reverseTransform(O output);
}
