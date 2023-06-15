package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Repositories.*;
import com.capstone.fitnessrx.Models.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import java.util.Calender;
import java.util.Collections;
import java.util.List;


@Controller
public class FeedController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final CommentsRepository commentsDao;
    private final FriendsRepository friendsDao;

    public FeedController(UserRepository userDao, PostRepository postDao, CommentsRepository commentsDao, FriendsRepository friendsDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.commentsDao = commentsDao;
        this.friendsDao = friendsDao;
    }

    //                          1.GETMAPPING
    //                          2.POST LIKE POST
    //GET MAPPING FOR FEEDS     3.POST COMMENTING ON POSTS
    //                          4.POST CREATING POSTS
    //
    @GetMapping("/feed/{id}")
    public String getFeed(@PathVariable Long id, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User authenticatedUserId = user;
        model.addAttribute("authenticatedUserId", authenticatedUserId);


        Post post = postDao.getReferenceById(id);


//        this gives navbar the profile pic
        String navbarProfilePic = user.getProfilePic();
        model.addAttribute("profilePicUrl", navbarProfilePic);

        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calenderUrl = "/calender/" + user.getId();
        model.addAttribute("calenderUrl", calenderUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);


        List<Comments> comment = commentsDao.findAll();
        List<Post> posts = postDao.findAll();
        Collections.reverse(posts);
        List<Comments> comments = commentsDao.findAll();
        User userProfile = userDao.findById(id).orElse(null);


        if (userProfile != null) {
//
//            List<Post> post = postDao.findAll();
            User userfeed = userDao.findById(id).orElse(null);
            List<Friends> userFriends = friendsDao.findAllByUserMain(userfeed);

            model.addAttribute("post", new Post());
            model.addAttribute("comments", new Comments());


            String username = userProfile.getUsername();
            String location = userProfile.getLocation();
            String workoutPreference = userProfile.getWorkoutPreference();
            String bio = userProfile.getBio();
            String goal = userProfile.getGoal();


            model.addAttribute("userFriends", userFriends);
            model.addAttribute("userProfile", userProfile);
            model.addAttribute("userProfileId", id);
            model.addAttribute("username", username);
            model.addAttribute("location", location);
            model.addAttribute("workoutPreference", workoutPreference);
            model.addAttribute("bio", bio);
            model.addAttribute("goal", goal);
            model.addAttribute("comments", comment);
            model.addAttribute("posts", posts);


            return "index/feed";
        } else {

            return "index/error";
        }
    }

//
//Post Liking Posts
//
    @PostMapping("/feed/like")
    public String likePost(@RequestParam("like") Long like, @RequestParam(name = "postIdent") String postIdentNum) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getReferenceById((long) user.getId());

        // Retrieve the post from the database using the postId
        Long postId = Long.parseLong(postIdentNum);
        Post post = postDao.getReferenceById(postId);


        // Increment the like count for the post
        Long currentLikes = post.getLikes();
        Long newLikes = currentLikes + 1;
        post.setLikes(newLikes);

        // Update the post in the database or perform any desired actions
        postDao.save(post); // Assuming 'postDao' has a save method to persist the post changes

        // Redirect to a different page or return the same page
        return "redirect:/feed/" + user.getId();
    }
    //
    //Post Commenting on Posts
    //
    @PostMapping("/feed/comment/create")
    public String commentvIICreate(@RequestParam(name = "comment") String content, @RequestParam(name = "postIdent") String postIdentNum) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getReferenceById((long) user.getId());

        Long postId = Long.parseLong(postIdentNum);
        Post post = postDao.getReferenceById(postId);

        //Create a new comment object
        Comments newComment = new Comments();

        newComment.setContent(content);
        newComment.setUser(user);
        newComment.setPosts(post);

        commentsDao.save(newComment);

//            System.out.println("~~~~~~~~~~~~~~");
//            System.out.println("commentToDb.getContent() = " + commentToDb.getContent());

        return "redirect:/feed/" + user.getId();
    }
    //
    //Post Creation of Posts
    //
    @PostMapping("/feed/{id}")
    public String postCreate(@ModelAttribute Post newPost) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getReferenceById((long) user.getId());
//            User userIdUrl = userDao.findById(id).orElse(null);

        // Create a new Post object
        Post post = new Post();

//
        post.setContent(newPost.getContent());
        post.setUser(user);
        postDao.save(post);


        return "redirect:/feed/" + user.getId();
    }

    @PostMapping("/feed/post/delete/{id}")
    public String postDelete(@PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Post post = postDao.getReferenceById(id);

//        Comments comment = commentsDao.getReferenceById(id);


//        commentsDao.deleteCommentsByPosts_Id(post.getId());
//        postDao.deleteAllById(post.getComments());
        postDao.deleteById(post.getId());

        return "redirect:/feed/" + user.getId();
    }

    @PostMapping("/feed/comment/delete/{id}")
    public String commentDelete(@PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Comments comments = commentsDao.getReferenceById(id);

        commentsDao.deleteById(comments.getId());

        return "redirect:/feed/" + user.getId();
    }

    @GetMapping("/feed/post/edit/{id}")
    public String postEditPage(@PathVariable long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User authenticatedUserId = user;
        model.addAttribute("authenticatedUserId", authenticatedUserId);


        Post post = postDao.getReferenceById(id);




        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calenderUrl = "/calender/" + user.getId();
        model.addAttribute("calenderUrl", calenderUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);


        List<Comments> comment = commentsDao.findAll();
        List<Post> posts = postDao.findAll();
        Collections.reverse(posts);
        List<Comments> comments = commentsDao.findAll();
        User userProfile = userDao.findById(id).orElse(null);

        model.addAttribute("userProfile", userProfile);
        model.addAttribute("userProfileId", id);
        model.addAttribute("comments", comment);
        model.addAttribute("posts", post);

        return "index/feedPostEdit";
    }

    @PostMapping("/feed/post/edit/{id}")
    public String postEdit(@PathVariable long id, @RequestParam(name = "contentBody") String content) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Post post = postDao.getReferenceById(id);
        post.setContent(content);
        postDao.save(post);

        return "redirect:/feed/" + user.getId();
    }






}
