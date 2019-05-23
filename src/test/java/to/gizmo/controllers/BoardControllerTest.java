package to.gizmo.controllers;

import static org.hamcrest.Matchers.hasValue;
import static org.springframework.data.repository.util.ClassUtils.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import to.gizmo.entities.Board;
import to.gizmo.entities.Workspace;
import to.gizmo.repositories.BoardRepository;
import to.gizmo.repositories.WorkspaceRepository;
import java.util.Optional;

@RunWith(SpringRunner.class)
@RequestMapping("/board")
@SpringBootTest
public class BoardControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Test
    @WithMockUser(username="admin")
    public void testBoardCreate() throws Exception {
        Board board = new Board();
        Optional<Workspace> optional = workspaceRepository.findById(1);

        mockMvc.perform(get("/board/create/{id}", 1)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .sessionAttr("board", optional.get())
        )
            .andExpect(view().name("board/create"));
    }

    @Test
    @WithMockUser(username="admin")
    public void testBoardCreateProcess() throws Exception {
        Optional<Workspace> optional = workspaceRepository.findById(1);

        mockMvc.perform(get("/board/create/{id}", 4)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .sessionAttr("board", optional.get())
            .param("title", "title")
        )
            .andExpect(redirectedUrl("/workspace/read/4"));

    }

    @Test
    @WithMockUser(username="admin")
    public void testBoardUpdate() throws Exception {
        Optional<Board> optional = boardRepository.findById(1);

        mockMvc.perform(get("/board/update/{id}",1)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .sessionAttr("board", optional.get()))
            .andExpect(status().isOk())
            .andExpect(view().name("board/update"));
    }

    @Test
    @WithMockUser(username="admin")
    public void testBoardUpdateProcess() throws Exception {
        mockMvc.perform(
            post("/board/update/{id}",1)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "title")
                .sessionAttr("board", "1"))
            .andExpect(view().name("/workspace/read/1"));
    }



    @Test
    @WithMockUser(username="admin")
    public void testBoardDelete() throws Exception {
        mockMvc.perform(post("/board/delete/{id}", 1))
            .andExpect(redirectedUrl("/workspace/read/1"));
    }
}
