package qcom.amfm.springbatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import qcom.amfm.springbatch.entity.FileEntity;
import qcom.amfm.springbatch.service.FileService;

public class CustomWriter implements ItemWriter<FileEntity>{
	
	@Autowired
	private FileService fileService;

	@Override
	public void write(List<? extends FileEntity> items) throws Exception {
		for(FileEntity file: items) {
			fileService.saveFile(file);
		}
		
	}

}
