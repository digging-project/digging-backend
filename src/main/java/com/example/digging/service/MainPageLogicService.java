package com.example.digging.service;

import com.example.digging.domain.entity.*;
import com.example.digging.domain.network.Header;
import com.example.digging.domain.network.response.ImgsApiResponse;
import com.example.digging.domain.network.response.PostLinkReadResponse;
import com.example.digging.domain.network.response.PostTextReadResponse;
import com.example.digging.domain.network.response.RecentDiggingResponse;
import com.example.digging.domain.repository.*;
import com.example.digging.util.SecurityUtil;
import com.example.digging.util.UrlTypeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MainPageLogicService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserHasPostsRepository userHasPostsRepository;

    @Autowired
    private PostLinkRepository postLinkRepository;

    @Autowired
    private PostTextRepository postTextRepository;

    @Autowired
    private PostImgRepository postImgRepository;

    @Autowired
    private ImgsRepository imgsRepository;

    @Autowired
    private PostTagRepository postTagRepository;

    /*public ArrayList<RecentDiggingResponse> recentPostsRead() {
        User userInfo = SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUid)
                .orElseThrow(() -> new RuntimeException("token 오류 입니다. 사용자를 찾을 수 없습니다."));

        Optional<User> optional = userRepository.findById(userInfo.getUserId());
        List<UserHasPosts> userHasPostsList = userHasPostsRepository.findAllByUser_UserId(userInfo.getUserId());
        int userHasPostsNum = userHasPostsList.size();
        ArrayList<Integer> postIdList = new ArrayList<Integer>();
        for(int i =0; i<userHasPostsNum; i++){
            postIdList.add(userHasPostsList.get(i).getPosts().getPostId());
        }

        ArrayList<Optional<Posts>> postsList = new ArrayList<>();
        Map<Integer, LocalDateTime> map = new LinkedHashMap<>();
        ArrayList<Optional<Posts>> orderPostsList = new ArrayList<>();
        for(int i =0; i<userHasPostsNum; i++){
            postsList.add(postsRepository.findById(postIdList.get(i)));
            map.put(postsRepository.findById(postIdList.get(i)).get().getPostId(),postsRepository.findById(postIdList.get(i)).get().getUpdatedAt());
        }
        Map<Integer, LocalDateTime> result = sortMapByValue(map);

        for (Integer key : result.keySet()) {
            orderPostsList.add(postsRepository.findById(key));
        }


        ArrayList<ArrayList> tags = new ArrayList();
        ArrayList<RecentDiggingResponse> recentDiggingList = new ArrayList<RecentDiggingResponse>();
        for(int i =0; i<userHasPostsNum; i++){
            if(orderPostsList.get(i).get().getIsLink() == Boolean.TRUE) {
                PostLink newlink = postLinkRepository.findByPostsPostId(orderPostsList.get(i).get().getPostId());
                List<PostTag> nowTags = postTagRepository.findAllByPostsPostId(orderPostsList.get(i).get().getPostId());
                int nowTagsSize = nowTags.size();
                ArrayList<String> tagStr = new ArrayList<String>();
                for(int j=0;j<nowTagsSize;j++){
                    tagStr.add(nowTags.get(j).getTags().getTags());
                }
                RecentDiggingResponse makingResponse = RecentDiggingResponse.builder()
                        .resultCode("Success")
                        .type("link")
                        .postId(newlink.getPosts().getPostId())
                        .title(newlink.getTitle())
                        .url(newlink.getUrl())
                        .updatedAt(newlink.getPosts().getUpdatedAt())
                        .isLike(newlink.getPosts().getIsLike())
                        .tags(tagStr)
                        .build();
                recentDiggingList.add(makingResponse);
            }

            if(orderPostsList.get(i).get().getIsText() == Boolean.TRUE) {
                PostText newtext = postTextRepository.findByPostsPostId(orderPostsList.get(i).get().getPostId());
                List<PostTag> nowTags = postTagRepository.findAllByPostsPostId(orderPostsList.get(i).get().getPostId());
                int nowTagsSize = nowTags.size();
                ArrayList<String> tagStr = new ArrayList<String>();
                for(int j=0;j<nowTagsSize;j++){
                    tagStr.add(nowTags.get(j).getTags().getTags());
                }
                RecentDiggingResponse makingResponse = RecentDiggingResponse.builder()
                        .resultCode("Success")
                        .type("text")
                        .postId(newtext.getPosts().getPostId())
                        .title(newtext.getTitle())
                        .content(newtext.getContent())
                        .updatedAt(newtext.getPosts().getUpdatedAt())
                        .isLike(newtext.getPosts().getIsLike())
                        .tags(tagStr)
                        .build();
                recentDiggingList.add(makingResponse);
            }

            if(orderPostsList.get(i).get().getIsImg() == Boolean.TRUE) {
                PostImg newimg = postImgRepository.findByPostsPostId(orderPostsList.get(i).get().getPostId());
                List<PostTag> nowTags = postTagRepository.findAllByPostsPostId(orderPostsList.get(i).get().getPostId());
                int nowTagsSize = nowTags.size();
                ArrayList<String> tagStr = new ArrayList<String>();
                for(int j=0;j<nowTagsSize;j++){
                    tagStr.add(nowTags.get(j).getTags().getTags());
                }

                ArrayList<ImgsApiResponse> imgsResponse = new ArrayList<>();
                List<Imgs> images = imgsRepository.findAllByPostImg_PostsPostId(newimg.getPosts().getPostId());
                int imgsNum = images.size();
                for(int j=0; j<imgsNum; j++) {
                    ImgsApiResponse imgsApiResponse = ImgsApiResponse.builder()
                            .id(images.get(j).getId())
                            .imgUrl(images.get(j).getImgUrl())
                            .build();
                    imgsResponse.add(imgsApiResponse);
                }

                RecentDiggingResponse makingResponse = RecentDiggingResponse.builder()
                        .resultCode("Success")
                        .type("img")
                        .postId(newimg.getPosts().getPostId())
                        .title(newimg.getTitle())
                        .updatedAt(newimg.getPosts().getUpdatedAt())
                        .isLike(newimg.getPosts().getIsLike())
                        .tags(tagStr)
                        .totalImgNum(imgsResponse.size())
                        .imgs(imgsResponse)
                        .build();
                recentDiggingList.add(makingResponse);
            }
        }

        ArrayList<RecentDiggingResponse> orderrecentDiggingList = new ArrayList<RecentDiggingResponse>();
        int number = recentDiggingList.size();
        if(number<=10){
            for(int i=0;i<number;i++) {
                orderrecentDiggingList.add(recentDiggingList.get(number-i-1));
            }
        } else {
            for(int i=0;i<10;i++){
                orderrecentDiggingList.add(recentDiggingList.get(number-i-1));
            }
        }

        return optional.map(user -> orderrecentDiggingList)
                .orElseGet(()->{
                    ArrayList<RecentDiggingResponse> errorList = new ArrayList<RecentDiggingResponse>();
                    RecentDiggingResponse error = RecentDiggingResponse.builder().resultCode("Error : User 없음").build();
                    errorList.add(error);
                    return errorList;
        });
    }
*/
    public ArrayList<RecentDiggingResponse> recentPostsRead() {
        User userInfo = SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUid)
                .orElseThrow(() -> new RuntimeException("token 오류 입니다. 사용자를 찾을 수 없습니다."));

        Optional<User> optional = userRepository.findById(userInfo.getUserId());
        List<UserHasPosts> userHasPostsList = userHasPostsRepository.findAllByUser_UserId(userInfo.getUserId());
        List<Posts> postsList = postsRepository.findTop10ByUpdatedAt();

    }

    public static LinkedHashMap<Integer, LocalDateTime> sortMapByValue(Map<Integer, LocalDateTime> map) {
        List<Map.Entry<Integer, LocalDateTime>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        LinkedHashMap<Integer, LocalDateTime> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, LocalDateTime> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}
