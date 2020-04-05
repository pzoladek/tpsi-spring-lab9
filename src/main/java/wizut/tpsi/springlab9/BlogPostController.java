package wizut.tpsi.springlab9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class BlogPostController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/")
    public String getPosts(Model model) throws SQLException {

        model.addAttribute("posts", blogRepository.getAllPosts());
        return "index";
    }

    @PostMapping("/newpost")
    public String newPost(BlogPost post) throws SQLException {
        blogRepository.createPost(post);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String newPost(@RequestParam("id") long id) throws SQLException {
        blogRepository.deletePost(id);
        return "redirect:/";
    }


}
