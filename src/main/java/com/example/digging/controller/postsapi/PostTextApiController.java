package com.example.digging.controller.postsapi;

import com.example.digging.domain.network.Header;
import com.example.digging.domain.network.UserDto;
import com.example.digging.domain.network.request.PostTextApiRequest;
import com.example.digging.domain.network.request.UpdateUserRequest;
import com.example.digging.domain.network.response.PostLinkReadResponse;
import com.example.digging.domain.network.response.PostTextApiResponse;
import com.example.digging.domain.network.response.PostTextReadResponse;
import com.example.digging.ifs.CrudInterface;
import com.example.digging.service.PostLinkApiLogicService;
import com.example.digging.service.PostTextApiLogicService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/posttext")
@Api
public class PostTextApiController implements CrudInterface<PostTextApiRequest, PostTextApiResponse> {

    @Autowired
    private PostTextApiLogicService postTextApiLogicService;

    @Override
    @PostMapping("")
    public PostTextApiResponse create(@RequestBody PostTextApiRequest request) {
        log.info("{}", request);
        return postTextApiLogicService.create(request);
    }

    @Override
    public PostTextApiResponse read(Integer id) {
        return null;
    }

    @Override
    public PostTextApiResponse update(Integer id, PostTextApiRequest request) { return null; }

    @GetMapping("")
    public PostTextReadResponse textread( @RequestParam(name = "postid") Integer postid) {
        return postTextApiLogicService.textread( postid);
    }

    @PutMapping("")
    public PostTextReadResponse textupdate(@RequestParam(name = "postid") Integer postid, @RequestBody PostTextApiRequest request){
        return postTextApiLogicService.textupdate(postid, request);
    }

    @GetMapping("/all_text_read")
    public ArrayList<PostTextReadResponse> alltextread() {
        log.info("[READ All Text]");
        return postTextApiLogicService.alltextread();
    }

    @GetMapping("/likes_text_read")
    public ArrayList<PostTextReadResponse> likestextread()
    {
        log.info("[READ Likes Text]");
        return postTextApiLogicService.likestextread();
    }

    @Override
    public PostTextApiResponse delete(Integer id) {
        return null;
    }
}
