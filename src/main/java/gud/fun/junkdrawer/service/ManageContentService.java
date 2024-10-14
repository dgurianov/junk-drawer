package gud.fun.junkdrawer.service;

import gud.fun.junkdrawer.dto.ManageContentRequestDto;
import gud.fun.junkdrawer.dto.ManageContentResponseDto;
import gud.fun.junkdrawer.dto.ManageContentStatusRequestDto;
import gud.fun.junkdrawer.dto.ManageContentStatusResponseDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ManageContentService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private Job createContentJob;

    @Autowired
    private JobExplorer jobExplorer;

    public ManageContentResponseDto createContent(ManageContentRequestDto manageContentRequestDto) throws Exception {
        JobExecution je = jobLauncher.run(createContentJob, createJobParameters(manageContentRequestDto));
        ManageContentResponseDto response = new ManageContentResponseDto();
        response.setJobId(je.getJobId());
        return response;
    }

    public ManageContentStatusResponseDto getCreateContentStatus(ManageContentStatusRequestDto request) {
        ManageContentStatusResponseDto response = new ManageContentStatusResponseDto();
        response.setStatus(jobExplorer.getJobExecution(request.getJobId()).getStatus().toString());
        return response;
    }

    private JobParameters createJobParameters(ManageContentRequestDto request) {
        Map<String, JobParameter<?>> m = new HashMap<>();
        m.put("time", new JobParameter<>((Long) System.currentTimeMillis(), Long.class));
        m.put("contentName", new JobParameter<>(request.getContentName(), String.class));
        m.put("contentAmount", new JobParameter<>(request.getContentAmount(), Long.class));
        JobParameters p = new JobParameters(m);
        return p;
    }

}