package com.codeup.blog.blog;

import com.codeup.blog.blog.models.Post;
import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.PostRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =BlogApplication.class)
@AutoConfigureMockMvc
public class PostIntegrationTests {

    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userDao;

    @Autowired
    PostRepository postsDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp()throws Exception{
        testUser = userDao.findByUsername("testUser");

        // Creates the test user if not exists
        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = userDao.save(newUser);
        }

        // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
        httpSession = this.mvc.perform(post("/login").with(csrf())
                .param("username", "testUser")
                .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/posts"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void contextLoads() {
        // Sanity Test, just to make sure the MVC bean is working
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        // It makes sure the returned session is not null
        assertNotNull(httpSession);
    }

    @Test
    public void testCreatePost() throws Exception {
        // Makes a Post request to /ads/create and expect a redirection to the Ad
        this.mvc.perform(
                post("/posts/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        // Add all the required parameters to your request like this
                        .param("title", "test")
                        .param("body", "for sale"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testShowAd() throws Exception {

        Post existingPost = postsDao.findAll().get(0);

        // Makes a Get request to /ads/{id} and expect a redirection to the Ad show page
        this.mvc.perform(get("/posts/" + existingPost.getId()))
                .andExpect(status().isOk())
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingPost.getBody())));
    }

    @Test
    public void testAdsIndex() throws Exception {
        Post existingPost = postsDao.findAll().get(0);

        // Makes a Get request to /ads and verifies that we get some of the static text of the ads/index.html template and at least the title from the first Ad is present in the template.
        this.mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                // Test the static content of the page
                .andExpect(content().string(containsString("Featured Posts")))
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingPost.getTitle())));
    }

    @Test
    public void testEditAd() throws Exception {

        Post existingPost = postsDao.findAll().get(0);

        this.mvc.perform(
                post("/posts/" + existingPost.getId() + "/edit").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "edited title")
                        .param("body", "edited description"))
                .andExpect(status().is3xxRedirection());

        this.mvc.perform(get("/posts/" + existingPost.getId()))
                .andExpect(status().isOk())

                .andExpect(content().string(containsString("edited title")))
                .andExpect(content().string(containsString("edited description")));
    }

    @Test
    public void testDeletePost() throws Exception{
        this.mvc.perform(post("/posts/create").with(csrf())
                .session((MockHttpSession) httpSession)
                .param("title", "post to delete")
                .param("body", "wont last long"))
                .andExpect(status().is3xxRedirection());

            Post existingPost = postsDao.findByTitle("post to delete");

            this.mvc.perform(post("/posts/delete").with(csrf())
                    .session((MockHttpSession) httpSession)
                    .param("id", String.valueOf(existingPost.getId())))
                    .andExpect(status().is3xxRedirection());
    }

}
