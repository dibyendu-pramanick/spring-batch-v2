package qcom.amfm.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qcom.amfm.springbatch.entity.AuditEntity;

public interface AuditRepository extends JpaRepository<AuditEntity, Integer> {

}
