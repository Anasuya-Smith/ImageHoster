package ImageHoster.controller;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    /*
        //@RequestMapping(value = "/images/{imageId}/{title}", method = RequestMethod.POST)
        @RequestMapping(value = "/comments/upload", method = RequestMethod.POST)
        public String createComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment") String text, Comment newComment, HttpSession session) {

            User user = (User) session.getAttribute("loggeduser");

            newComment.setUser(user);
            newComment.setCreatedDate(new Date());

            Image image = imageService.getImage(imageId);
            newComment.setImages(image);
            newComment.setText(text);
            commentService.createComment(newComment);
            return "redirect:/images/{imageId}/{title}";
        }

     */
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment") String comments, HttpSession session, Model model) {

        Image image = imageService.getImageById(imageId);

        User user1 = (User) session.getAttribute("loggeduser");

        Comment newComment = new Comment();

        newComment.setUser(user1);
        newComment.setCreatedDate(new Date());
        //List<Comment> commentId =

        newComment.setImages(image);
        newComment.setText(comments);
        commentService.createComment(newComment);

        Image image1 = imageService.getImageById(imageId);
        model.addAttribute("comments", newComment);
        model.addAttribute("image", image1);
        model.addAttribute("tags", image1.getTags());

        return "/images/image";

    }
}

