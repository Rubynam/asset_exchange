package org.trading.asset_exchange.application.port;

public interface Transformer<I,O> {

  O transform(I input);

  I reverseTransform(O output);
}
