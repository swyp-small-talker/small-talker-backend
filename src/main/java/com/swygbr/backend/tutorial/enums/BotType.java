package com.swygbr.backend.tutorial.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BotType {
  GENIE(10),
  JASMINE(20);

  private final int level;

  public boolean isHigherThan(BotType other) {
    return this.level > other.level;
  }
}
