package qcom.amfm.springbatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import qcom.amfm.springbatch.entity.FileEntity;
import qcom.amfm.springbatch.service.FileService;

@RestController
@RequestMapping("file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private SimpleJobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	@PostMapping("")
	public FileEntity saveFile(@RequestParam("file") MultipartFile file) {
		try {
			return fileService.saveFile(file);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/job")
	public void executeJob() throws Exception {
		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		JobExecution execution = jobLauncher.run(job, param);
	}

}
