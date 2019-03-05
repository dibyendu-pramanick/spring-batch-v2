package qcom.amfm.springbatch.processor;

import java.util.Base64;

import org.springframework.batch.item.ItemProcessor;

import qcom.amfm.springbatch.entity.FileEntity;

public class CustomProcessor implements ItemProcessor<FileEntity,FileEntity>{

	@Override
	public FileEntity process(FileEntity item) throws Exception {
		
		byte[] buffer = new byte[item.getData().length];
		buffer = item.getData();
		System.out.println(item.getFileName());
		System.out.println(new String(buffer));
		
		item.setStatus("processed");
		
		return item;
	}

}
