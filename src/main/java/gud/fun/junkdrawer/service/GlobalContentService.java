package gud.fun.junkdrawer.service;

import gud.fun.junkdrawer.dto.ContentDtoRequest;
import gud.fun.junkdrawer.dto.ContentDtoResponse;
import gud.fun.junkdrawer.dto.CreateContentStatusRequestDto;
import gud.fun.junkdrawer.dto.CreateContentStatusResponseDto;
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
public class GlobalContentService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private Job createContentJob;

    @Autowired
    private JobExplorer jobExplorer;

    public ContentDtoResponse createContent(ContentDtoRequest contentDtoRequest) throws Exception {
        //Process DTO and start to  either do manually or run the batch job
        JobExecution je = jobLauncher.run(createContentJob, createJobParameters(contentDtoRequest));
        ContentDtoResponse response = new ContentDtoResponse();
        response.setJobId(je.getJobId());
        return response;
    }

    public CreateContentStatusResponseDto getCreateContentStatus(CreateContentStatusRequestDto request) {
        //Process DTO and start to  either do manually or run the batch job
        CreateContentStatusResponseDto response = new CreateContentStatusResponseDto();
        response.setStatus(jobExplorer.getJobExecution(request.getJobId()).getStatus().toString());
        return response;
    }

    private JobParameters createJobParameters(ContentDtoRequest request) {
        Map<String, JobParameter<?>> m = new HashMap<>();
        m.put("time", new JobParameter<>((Long) System.currentTimeMillis(), Long.class));
        m.put("contentName", new JobParameter<>(request.getContentName(), String.class));
        m.put("contentAmount", new JobParameter<>(request.getContentAmount(), Long.class));
        JobParameters p = new JobParameters(m);
        return p;
    }

}