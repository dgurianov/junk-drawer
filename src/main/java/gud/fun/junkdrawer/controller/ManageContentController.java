package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.ManageContentRequestDto;
import gud.fun.junkdrawer.dto.ManageContentResponseDto;
import gud.fun.junkdrawer.dto.ManageContentStatusRequestDto;
import gud.fun.junkdrawer.dto.ManageContentStatusResponseDto;
import gud.fun.junkdrawer.service.ManageContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value=Endpoints.CREATE_CONTENT)
public class ManageContentController {

    @Autowired
    private ManageContentService contentService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ManageContentResponseDto> createContent(@RequestBody ManageContentRequestDto manageContentRequestDto) {
        ManageContentResponseDto createdContent = null;
        try {
          createdContent = contentService.createContent(manageContentRequestDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Content is not created!");
        }
        return ResponseEntity.ok(createdContent);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ManageContentStatusResponseDto> getCreateContentStatus(@PathVariable Long id) {
        ManageContentStatusRequestDto request = new ManageContentStatusRequestDto();
        request.setJobId(id);
        ManageContentStatusResponseDto response = contentService.getCreateContentStatus(request);
        return ResponseEntity.ok(response);
    }

}