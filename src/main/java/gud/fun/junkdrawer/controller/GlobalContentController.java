package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.config.Endpoints;
import gud.fun.junkdrawer.dto.ContentDtoRequest;
import gud.fun.junkdrawer.dto.ContentDtoResponse;
import gud.fun.junkdrawer.dto.CreateContentStatusRequestDto;
import gud.fun.junkdrawer.dto.CreateContentStatusResponseDto;
import gud.fun.junkdrawer.service.GlobalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(Endpoints.CREATE_CONTENT)
public class GlobalContentController {

    @Autowired
    private GlobalContentService contentService;

    @PostMapping
    public ResponseEntity<ContentDtoResponse> createContent(@RequestBody ContentDtoRequest contentDtoRequest) {
        ContentDtoResponse createdContent = null;
        try {
          createdContent = contentService.createContent(contentDtoRequest);
        } catch (Exception e) {

        }
        return ResponseEntity.ok(createdContent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateContentStatusResponseDto> getCreateContentStatus(@PathVariable Long id) {
        CreateContentStatusRequestDto request = new CreateContentStatusRequestDto();
        request.setJobId(id);
             CreateContentStatusResponseDto response = contentService.getCreateContentStatus(request);
        return ResponseEntity.ok(response);
    }

}