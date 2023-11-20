package com.tempotalent.api.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;

public class JobOfferAdvantageKey implements Serializable {
  @Column(name="idjob_offer", nullable = false)
  private UUID idjob_offer;

  @Column(name="idadvantage", nullable = false)
  private UUID idadvantage;

  public JobOfferAdvantageKey() {
  }

  public JobOfferAdvantageKey(UUID idjob_offer, UUID idadvantage) {
    this.idjob_offer = idjob_offer;
    this.idadvantage = idadvantage;
  }

  public UUID getJobOffer() {
    return idjob_offer;
  }

  public UUID getAdvantage() {
    return idadvantage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof JobOfferAdvantageKey))
      return false;
    JobOfferAdvantageKey that = (JobOfferAdvantageKey) o;
    return this.idadvantage.equals(that.idadvantage) && this.idjob_offer.equals(that.idjob_offer);
  }

  @Override
  public int hashCode() {
    return 31 * idadvantage.hashCode() + idjob_offer.hashCode();
  }
}

