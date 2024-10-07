package gud.fun.junkdrawer.service.batch;

import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import gud.fun.junkdrawer.util.generator.PhoneNumberGenerator;
import gud.fun.junkdrawer.util.generator.withentity.CityEntityGenerator;
import gud.fun.junkdrawer.util.generator.withentity.CountryEntityGenerator;
import gud.fun.junkdrawer.util.generator.StreetGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@Component
public class CreateContentTasklet implements Tasklet {

    @Autowired
    private WebApplicationContext appContext;

    @Autowired
    private CityEntityGenerator cityGenerator;

    @Autowired
    private StreetGenerator streetGenerator;

    @Autowired
    private CountryEntityGenerator countryEntityGenerator;

    @Autowired
    private PhoneNumberGenerator phoneNumberGenerator;

    private JpaRepository repository = null;




    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        String contentName = (String) chunkContext.getStepContext().getJobParameters().get("contentName");
        Long contentAmount = (Long) chunkContext.getStepContext().getJobParameters().get("contentAmount");

        try {
            Class<?> cls = Class.forName("gud.fun.junkdrawer.persistance.model." + contentName);
            Repositories repositories = new Repositories(appContext);
            repository = (JpaRepository) repositories.getRepositoryFor(cls).orElseThrow(ClassNotFoundException::new);
        } catch (ClassNotFoundException e) {
            log.error("While searching for repository, requested Class was not found: {}", contentName);
            return RepeatStatus.FINISHED;
        }

        switch(contentName) {
            case "City":
                log.info("Request to create City times: {}",contentAmount);
                while(contentAmount-- > 0) {
                    City city = cityGenerator.generateRandom();
                    repository.save(city);
                }
                break;
            case "Country":
                log.debug("Request to save Country times: {}", contentAmount);
                break;
            case "PhoneNumber":
                log.debug("Request to save PhoneNumber times: {}", contentAmount);
                while (contentAmount-- > 0) {
                    repository.save(phoneNumberGenerator.generateRandom());
                }
                break;
            default:
                log.error("Request to save unknown entity. Nothing will be done");
        }

        return RepeatStatus.FINISHED;
    }
}
