package gud.fun.junkdrawer.controller;

import gud.fun.junkdrawer.configuration.Endpoints;
import gud.fun.junkdrawer.controller.data.ManageContentController;
import gud.fun.junkdrawer.dto.ManageContentRequestDto;
import gud.fun.junkdrawer.dto.ManageContentResponseDto;
import gud.fun.junkdrawer.dto.ManageContentStatusRequestDto;
import gud.fun.junkdrawer.dto.ManageContentStatusResponseDto;
import gud.fun.junkdrawer.service.ManageContentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ManageContentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ManageContentService contentService;

    @InjectMocks
    private ManageContentController manageContentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(manageContentController).build();
    }

    @Test
    void testCreateContent() throws Exception {
        ManageContentResponseDto responseDto = new ManageContentResponseDto();
        responseDto.setJobId(1L);

        when(contentService.createContent(any(ManageContentRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post(Endpoints.CREATE_CONTENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"contentName\":\"City\",\"contentAmount\":10 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobId").exists());
    }

    @Test
    void testGetContentById() throws Exception {
        ManageContentStatusResponseDto responseDto = new ManageContentStatusResponseDto();
        responseDto.setStatus("READY");

        when(contentService.getCreateContentStatus(any(ManageContentStatusRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(get(Endpoints.CREATE_CONTENT +"/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").exists());
    }
}
