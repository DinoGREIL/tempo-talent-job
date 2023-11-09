package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempotalent.api.models.Subscription;
import com.tempotalent.api.models.SubscriptionKey;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionKey> {
}
