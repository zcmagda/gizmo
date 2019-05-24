package to.gizmo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@RequestMapping("/board")
@SpringBootTest
public class BoardControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    @WithMockUser(username = "admin")
    public void testBoardCreate() throws Exception {
        mockMvc.perform(
            get("/board/create/{workspaceId}", 1)
        )
            .andExpect(status().isOk())
            .andExpect(view().name("board/create"));
    }


    @Test
    @WithMockUser(username = "admin")
    public void testBoardCreateProcess() throws Exception {
        mockMvc.perform(
            post("/board/create/{workspaceId}", 1)
                .param("title", "title")
        )
            .andExpect(redirectedUrl("/workspace/read/1"));

    }

    @Test
    @WithMockUser(username = "admin")
    public void testBoardUpdate() throws Exception {
        mockMvc.perform(
            get("/board/update/{id}", 1)
        )
            .andExpect(status().isOk())
            .andExpect(view().name("board/update"));
    }

    @Test
    @WithMockUser(username = "admin")
    public void testBoardUpdateProcess() throws Exception {
        mockMvc.perform(
            post("/board/update/{id}", 1)
                .param("title", "title")
        )
            .andExpect(redirectedUrl("/workspace/read/1"));
    }


    @Test
    @WithMockUser(username = "admin")
    public void testBoardDelete() throws Exception {
        mockMvc.perform(post("/board/delete/{id}", 1))
            .andExpect(redirectedUrl("/workspace/read/1"));
    }
}
