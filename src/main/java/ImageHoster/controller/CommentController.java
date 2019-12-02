package ImageHoster.controller;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "image/{imageId}/{title}", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment") String text, Comment newComment, HttpSession session) {

        User user = (User) session.getAttribute("loggeduser");

        newComment.setUser(user);
        newComment.setDate(new Date());

        Image image = imageService.getImage(imageId);
        newComment.setImages(image);
        newComment.setText(text);
        commentService.createComment(newComment);
        return "redirect:/images/{imageId}/{title}";
    }
}
