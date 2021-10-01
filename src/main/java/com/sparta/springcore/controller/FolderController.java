package com.sparta.springcore.controller;

import com.sparta.springcore.dto.FolderRequestDto;
import com.sparta.springcore.model.Folder;
import com.sparta.springcore.model.User;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FolderController {
    private final FolderService folderService;

    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping("/api/folders")
    public List<Folder> addFolders(@RequestBody FolderRequestDto requestDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<String> folderNames = requestDto.getFolderNames();
        User user = userDetails.getUser();

        return folderService.addFolders(folderNames, user);
    }
}
