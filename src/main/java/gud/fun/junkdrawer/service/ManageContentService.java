package gud.fun.junkdrawer.service;

import gud.fun.junkdrawer.dto.ManageContentRequestDto;
import gud.fun.junkdrawer.dto.ManageContentResponseDto;
import gud.fun.junkdrawer.dto.ManageContentStatusRequestDto;
import gud.fun.junkdrawer.dto.ManageContentStatusResponseDto;
import gud.fun.junkdrawer.persistance.model.deleteorder.EntityLevelOne;
import gud.fun.junkdrawer.persistance.model.deleteorder.EntityLevelParent;
import gud.fun.junkdrawer.persistance.model.deleteorder.EntityLevelTwo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ManageContentService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    private Job createContentJob;

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    private List<JpaRepository<? extends EntityLevelParent, UUID>> jpaRepositoriesForParent;

    @Autowired
    private List<JpaRepository<? extends EntityLevelOne, UUID>> jpaRepositoriesForLevelOne;

    @Autowired
    private List<JpaRepository<? extends EntityLevelTwo, UUID>> jpaRepositoriesForLevelTwo;


    public ManageContentResponseDto createContent(ManageContentRequestDto manageContentRequestDto) throws Exception {
        JobExecution je = jobLauncher.run(createContentJob, createJobParameters(manageContentRequestDto));
        ManageContentResponseDto response = new ManageContentResponseDto();
        response.setJobId(je.getJobId());
        response.setMessage(je.getStatus().toString());
        return response;
    }

    public ManageContentStatusResponseDto getCreateContentStatus(ManageContentStatusRequestDto request) {
        ManageContentStatusResponseDto response = new ManageContentStatusResponseDto();
        response.setStatus(jobExplorer.getJobExecution(request.getJobId()).getStatus().toString());
        return response;
    }

    private JobParameters createJobParameters(ManageContentRequestDto request) {
        Map<String, JobParameter<?>> jobParameterMap = new HashMap<>();
        //You need time here for randomness ,  otherwise, you cannot create job with the same parameters more than once
        jobParameterMap.put("time", new JobParameter<>((Long) System.currentTimeMillis(), Long.class));
        jobParameterMap.put("contentName", new JobParameter<>(request.getContentName(), String.class));
        jobParameterMap.put("contentAmount", new JobParameter<>(request.getContentAmount(), Long.class));
        return new JobParameters(jobParameterMap);
    }

    public ManageContentResponseDto deleteAllContent() {
        ManageContentResponseDto responseDto = new ManageContentResponseDto();
        try {
            //Can be executed first
            //Independent entities , that are  parent of other entity  or has no child
            jpaRepositoriesForParent.forEach(JpaRepository::deleteAll);

            //Can be executed second
            //Entities that are child of some parent on 1st layer
            jpaRepositoriesForLevelOne.forEach(JpaRepository::deleteAll);

            //Can be executed third
            //Entities that are child of some parent on 2nd layer
            jpaRepositoriesForLevelTwo.forEach(JpaRepository::deleteAll);

            responseDto.setMessage("All data was deleted successfully.");

        }catch (DataIntegrityViolationException e){
           responseDto.setMessage("A DataIntegrityViolationException was caught, while deleting all data." +
                   " Possible reason could be, that some Entity from the model extends wrong abstract class from deleteorder sub-package , " +
                   "which leads to deleting child entities before parents. Exception message : " +
                   e.getMessage());
        }
        responseDto.setJobId(0L);
        return responseDto;
    }

}