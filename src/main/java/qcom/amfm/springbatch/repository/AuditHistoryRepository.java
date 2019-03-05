package qcom.amfm.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qcom.amfm.springbatch.entity.AuditHistoryEntity;

public interface AuditHistoryRepository extends JpaRepository<AuditHistoryEntity, Integer>{

}
