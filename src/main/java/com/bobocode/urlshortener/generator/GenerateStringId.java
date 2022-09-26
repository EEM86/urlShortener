package com.bobocode.urlshortener.generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class GenerateStringId implements IdentifierGenerator {

  private static int RANDOM_LENGTH = 5;

  @Override
  public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
      throws HibernateException {
    return RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH);
  }
}
