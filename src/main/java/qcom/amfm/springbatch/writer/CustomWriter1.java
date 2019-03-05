package qcom.amfm.springbatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import qcom.amfm.springbatch.entity.AuditEntity;
import qcom.amfm.springbatch.entity.AuditHistoryEntity;
import qcom.amfm.springbatch.service.FileService;

public class CustomWriter1 implements ItemWriter<String> {
	
	@Autowired
	private FileService fileService;
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		items.forEach(data -> {
			AuditEntity audit = new AuditEntity();
			audit.setCommand(data);
			
			AuditHistoryEntity auditHistory = new AuditHistoryEntity();
			auditHistory.setCommand(data);
			
			fileService.updateAuditAndAuditHistory(audit, auditHistory);
		});
	}

}
