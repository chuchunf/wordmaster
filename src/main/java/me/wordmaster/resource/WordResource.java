package me.wordmaster.resource;

import me.wordmaster.model.UserWord;
import me.wordmaster.security.AllowedRoles;
import me.wordmaster.service.WordService;
import me.wordmaster.vo.AnswerVO;
import me.wordmaster.vo.BookWordVO;
import me.wordmaster.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("word")
public class WordResource {
    @Autowired
    private WordService service;

    @GET
    @Path("next10words")
    @AllowedRoles
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopUser(@Context HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("user");
        return Response.ok(service.getNext10Words(username)).build();
    }

    @GET
    @Path("details/{word}")
    @AllowedRoles
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWord(@PathParam("word") String word) {
        return Response.ok(service.getWorDDetails(word)).build();
    }

    @POST
    @Path("ask")
    @AllowedRoles
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response askQuestions(List<String> words) {
        List<QuestionVO> questions = service.getQuestion(words);
        return Response.ok(questions).build();
    }

    @POST
    @Path("answer")
    @AllowedRoles
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response answerQuestions(@Context HttpServletRequest request, List<AnswerVO> answer) {
        String username = (String) request.getSession().getAttribute("user");
        service.updateRecord(username, answer);
        return Response.ok().build();
    }

    @POST
    @Path("bookword")
    @AllowedRoles
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBookWord(List<BookWordVO> lists) {
        service.updateBookWord(lists);
        return Response.ok().build();
    }

    @POST
    @Path("userword")
    @AllowedRoles
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserWord(@Context HttpServletRequest request, UserWord word) {
        String username = (String) request.getSession().getAttribute("user");
        service.updateUserWord(username, word);
        return Response.ok().build();
    }
}
