package com.egem.ram.dao;

import com.egem.ram.domain.HttpRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HttpRequestRepository extends JpaRepository<HttpRequest, Long> {
  Integer countAllByUrlContains(String name);
}
