package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.dto.ManageContentDtoRequest;
import gud.fun.junkdrawer.dto.ManageContentDtoResponse;
import gud.fun.junkdrawer.dto.ManageContentStatusRequestDto;
import gud.fun.junkdrawer.dto.ManageContentStatusResponseDto;
import gud.fun.junkdrawer.service.ManageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value=Endpoints.CREATE_CONTENT)
public class ManageContentController {

    @Autowired
    private ManageContentService contentService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ManageContentDtoResponse> createContent(@RequestBody ManageContentDtoRequest manageContentDtoRequest) {
        ManageContentDtoResponse createdContent = null;
        try {
          createdContent = contentService.createContent(manageContentDtoRequest);
        } catch (Exception e) {
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