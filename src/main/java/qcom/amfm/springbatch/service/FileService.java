package qcom.amfm.springbatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import qcom.amfm.springbatch.entity.AuditEntity;
import qcom.amfm.springbatch.entity.AuditHistoryEntity;
import qcom.amfm.springbatch.entity.FileEntity;
import qcom.amfm.springbatch.repository.AuditHistoryRepository;
import qcom.amfm.springbatch.repository.AuditRepository;
import qcom.amfm.springbatch.repository.FileRepository;

@Service
public class FileService {
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private AuditRepository auditRepository;
	
	@Autowired
	private AuditHistoryRepository auditHistoryRepository;
	
	public FileEntity saveFile(MultipartFile file) throws Exception {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileEntity dbFile = new FileEntity();
		dbFile.setData(file.getBytes());
		dbFile.setFileName(fileName);
		dbFile.setFileType(file.getContentType());
		dbFile.setStatus("initial");
		return fileRepository.save(dbFile);
	}
	
	public FileEntity saveFile(FileEntity file) {
		return fileRepository.save(file);
	}
	
	public FileEntity getFile(int id) {
		return fileRepository.findById(id).orElse(null);
	}
	
	public List<FileEntity> getAllFiles() {
		return fileRepository.findAll();
	}
	
	public void updateAuditAndAuditHistory(AuditEntity audit, AuditHistoryEntity auditHistory) {
		auditRepository.save(audit);
		auditHistoryRepository.save(auditHistory);
	}

}
