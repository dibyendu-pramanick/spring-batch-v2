package qcom.amfm.springbatch.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import qcom.amfm.springbatch.entity.FileEntity;
import qcom.amfm.springbatch.service.FileService;

public class CustomerReader1 implements ItemReader<String> {
	
	@Autowired
	private FileService fileService;
	
	private List<FileEntity> files;
	private List<String> fileData;
	
	private boolean loadFileData = false;
	
	
	private void initialize() {
		this.files = fileService.getAllFiles();
		String data = new String(this.files.get(0).getData());
		List<String> unmodifiableData = Arrays.asList(data.split("\n"));
		fileData = new ArrayList<>(unmodifiableData);
	}
	
	@Override
	public String read() throws Exception {
		if(!loadFileData) {
			loadFileData = true;
			initialize();
		}
		if(!fileData.isEmpty()) {
			return fileData.remove(0);
		}
		loadFileData = false;
		return null;
	}

}
