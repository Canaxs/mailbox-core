package com.mailbox.service.impl;

import com.mailbox.common.CommonConstants;
import com.mailbox.enums.MailType;
import com.mailbox.service.FileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public Map<String, MailType> fileReadConvertList() {
        Map<String, MailType> file = new HashMap<>();
        List<String> word = bringTheArrow(CommonConstants.mailFileWord);
        List<String> mailType = bringTheArrow(CommonConstants.mailFileType);
        for(int i =0;i<word.size();i++) {
            file.put(word.get(i).split(CommonConstants.mailFileWord)[1],MailType.convert(mailType.get(i).split(CommonConstants.mailFileType)[1]));
        }
        return file;
    }

    private List<String> bringTheArrow(String startName) {
        List<String> startNameList = new LinkedList<>();
        try (Stream<String> stream = Files.lines(Paths.get(CommonConstants.mailFileTXT))) {
            startNameList = stream.filter(line -> line.startsWith(startName))
                    .toList();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return startNameList;
    }

}
