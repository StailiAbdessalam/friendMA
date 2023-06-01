package com.Abdessalam.friendMA.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.Abdessalam.friendMA.dto.mapperInterface.IMapperDto;
import com.Abdessalam.friendMA.dto.model.UserInfoDto;
import com.Abdessalam.friendMA.entity.UserInfo;
import com.Abdessalam.friendMA.repository.UserInfoRepository;
import com.Abdessalam.friendMA.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final IMapperDto<UserInfo,UserInfoDto> userInfoMapper;

    public UserInfoDto getUserInfo(String userId) {
        UserInfo userInfo = userInfoRepository.findByUserId(userId);
        return userInfoMapper.convertToDto(userInfo, UserInfoDto.class);
    }

    public UserInfoDto saveUserInfo(UserInfoDto userInfoDto) {
        log.warn("saveUserInfo" + userInfoDto);
        UserInfo userInfo = userInfoMapper.convertToEntity(userInfoDto, UserInfo.class);
        userInfo = userInfoRepository.save(userInfo);
        return userInfoMapper.convertToDto(userInfo, UserInfoDto.class);
    }


    public ResponseEntity<String> saveUserAvatar(MultipartFile avatar, String userId) throws FirebaseAuthException {

        UserInfo userInfo = userInfoRepository.findByUserId(userId);

        if (Objects.isNull(userInfo)) {
            return new ResponseEntity<>("UserInfo not found", HttpStatus.NOT_FOUND);
        }

        String username = FirebaseAuth.getInstance().getUser(userId).getDisplayName();
        FileUtil.createAvatarDirIfNotExist();
        String fileName = username + FileUtil.getExtension(Objects.requireNonNull(avatar.getOriginalFilename()));
        String filePath = FileUtil.avatarFolderPath  + fileName;

        try {
            avatar.transferTo(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        userInfo.setAvatar(fileName);
        userInfoRepository.save(userInfo);

        return new ResponseEntity<>(fileName, HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteUserInfo(Long userId) {
        try {
            userInfoRepository.deleteById(userId);
            return new ResponseEntity<>("UserInfo deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("UserInfo not found", HttpStatus.NOT_FOUND);
        }
    }
}
