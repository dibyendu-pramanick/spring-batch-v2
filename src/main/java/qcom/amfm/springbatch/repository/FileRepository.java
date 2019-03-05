package qcom.amfm.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qcom.amfm.springbatch.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer>{

}
