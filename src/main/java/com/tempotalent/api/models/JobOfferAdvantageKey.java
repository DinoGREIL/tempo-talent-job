package com.tempotalent.api.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;

public class JobOfferAdvantageKey implements Serializable {
  @Column(name="idjob_offer", nullable = false)
  private UUID jobOfferId;

  @Column(name="idadvantage", nullable = false)
  private UUID advantageId;

  public JobOfferAdvantageKey() {
  }

  public JobOfferAdvantageKey(UUID jobOfferId, UUID advantageId) {
    this.jobOfferId = jobOfferId;
    this.advantageId = advantageId;
  }

  public UUID getJobOffer() {
    return jobOfferId;
  }

  public UUID getAdvantage() {
    return advantageId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof JobOfferAdvantageKey))
      return false;
    JobOfferAdvantageKey that = (JobOfferAdvantageKey) o;
    return this.advantageId.equals(that.advantageId) && this.jobOfferId.equals(that.jobOfferId);
  }

  @Override
  public int hashCode() {
    return 31 * advantageId.hashCode() + jobOfferId.hashCode();
  }
}

