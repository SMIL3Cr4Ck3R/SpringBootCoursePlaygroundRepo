package io.sc0.REST.API.Demo.repository;

import io.sc0.REST.API.Demo.entity.MdmApiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ApiLogRepository extends JpaRepository<MdmApiLog, Long> {

}