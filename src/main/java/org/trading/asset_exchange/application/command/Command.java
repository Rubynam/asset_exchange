package org.trading.asset_exchange.application.command;

public interface Command<I,O>{

  O execute(I input);
}
