package com.fzn.pesystem.concase.service;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    public String addAttachments(@RequestParam String type, @RequestParam Integer typeId, @RequestPart("file") MultipartFile file);

    public String addVideo(@RequestParam String type,@RequestParam Integer completionId,@RequestPart("video") MultipartFile video);

    public String downloadFile(@RequestParam String downLoadUrl,@RequestParam String saveUrl);
}
