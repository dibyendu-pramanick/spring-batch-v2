package qcom.amfm.springbatch.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import qcom.amfm.springbatch.entity.FileEntity;
import qcom.amfm.springbatch.service.FileService;

public class CustomReader implements ItemReader<FileEntity>{

	@Autowired
	private FileService fileService;
	
	private List<FileEntity> files;
	private boolean loadFileData = false;
	
	
	private void initialize() {
		this.files = fileService.getAllFiles();
	}
	
	@Override
	public FileEntity read() throws Exception {
		if(!loadFileData) {
			loadFileData = true;
			initialize();
		}
		if(!files.isEmpty()) {
			return files.remove(0);
		}
		loadFileData = false;
		return null;
	}

}
