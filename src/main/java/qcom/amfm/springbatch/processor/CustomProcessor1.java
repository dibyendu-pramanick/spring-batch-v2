package qcom.amfm.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;

public class CustomProcessor1 implements ItemProcessor<String,String> {
	
	@Override
	public String process(String item) throws Exception {
		
		System.out.println("Processing------" + item);
		
		return item;
	}

}
