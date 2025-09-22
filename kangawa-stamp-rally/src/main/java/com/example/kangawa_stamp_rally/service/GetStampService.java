// GetStampService.java
package com.example.kangawa_stamp_rally.service;

import com.example.kangawa_stamp_rally.dto.QuizDto;
import com.example.kangawa_stamp_rally.dto.ReturnStampInfoDto;
import com.example.kangawa_stamp_rally.entity.*;
import com.example.kangawa_stamp_rally.repository.GetStampRepository;
import com.example.kangawa_stamp_rally.config.AppProperties; // AppPropertiesをインポート
import com.example.kangawa_stamp_rally.repository.QuizRepository;
import com.example.kangawa_stamp_rally.repository.StampRepository;
import com.example.kangawa_stamp_rally.repository.UserRepository;
import com.github.dozermapper.core.Mapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetStampService {

    private final GetStampRepository getStampRepository;
    @Getter
    private final StampRepository stampRepository;
    @Getter
    private final QuizRepository quizRepository;
    @Getter
    private final Mapper mapper;
    private final String imageBaseUrl;
    private final UserRepository userRepository;
    @Getter

    // フォーマッター
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public GetStampService(GetStampRepository getStampRepository, StampRepository stampRepository, QuizRepository quizRepository, Mapper mapper, AppProperties appProperties, UserRepository userRepository) {
        this.getStampRepository = getStampRepository;
        this.stampRepository = stampRepository;
        this.quizRepository = quizRepository;
        this.mapper = mapper;
        this.imageBaseUrl = appProperties.getImageBaseUrl();
        this.userRepository = userRepository;
    }

    //ユーザが獲得したスタンプ一覧
    public List<ReturnStampInfoDto> getAcquiredStampsWithUrls(String uuid) {
        List<GetStampEntity> getStamps = getStampRepository.findById_Uuid(uuid);

        return getStamps.stream()
                .map(this::convertToDtoWithUrl)
                .collect(Collectors.toList());
    }

    //スタンプ獲得時
    @Transactional
    public ReturnStampInfoDto addStampToDb(String uuid, String stampNo) {
        UserEntity user = userRepository.findById(uuid).orElseThrow(
                () -> new IllegalArgumentException("存在しないユーザです。"));

        StampEntity stamp = stampRepository.findById(stampNo).orElseThrow(
                () -> new IllegalArgumentException("存在しないスタンプです。"));

        GetStampId Id = new GetStampId(uuid, stampNo);

        GetStampEntity getStampEntity = new GetStampEntity();
        getStampEntity.setId(Id); // 複合主キーをセット
        getStampEntity.setUser(user);     // UserEntityオブジェクトをセット
        getStampEntity.setStamp(stamp);   // StampEntityオブジェクトをセット

        if (getStampRepository.findById(Id).isPresent()) {
            throw new IllegalArgumentException("すでに獲得済みのスタンプです");
        }

        getStampRepository.save(getStampEntity);

        GetStampEntity entity = getStampRepository.findById(Id).orElseThrow(
                () -> new IllegalArgumentException("内部エラー"));

        return convertToDtoWithUrl(entity);
    }

    //クイズ取得
    public QuizDto getQuiz(String stampId){
        var Id = stampRepository.findById(stampId).orElseThrow(
                () -> new IllegalArgumentException("不正なスタンプ番号です"));
        QuizEntity entity = quizRepository.findById(Id.getQuizNo()).orElseThrow();
        return mapper.map(entity, QuizDto.class);
    }

    private ReturnStampInfoDto convertToDtoWithUrl(GetStampEntity getStampEntity) { // 引数名を明確に
        ReturnStampInfoDto dto = new ReturnStampInfoDto();

        // 獲得スタンプのIDからスタンプNoをセット
        if (getStampEntity.getId() != null) {
            dto.setStampNo(getStampEntity.getId().getStampNo());
        }

        // --- StampEntityの情報を直接利用 ---
        StampEntity stamp = getStampEntity.getStamp(); // すでにエンティティとして持っている
        if (stamp != null) { // nullチェックは重要
            dto.setStampName(stamp.getStampName());
            dto.setStampText(stamp.getStampText());

            // 画像URLの構築
            String imageFilename = stamp.getImgPath(); // StampEntityのimgPathを利用
            if (imageFilename != null && !imageFilename.isEmpty()) {
                dto.setImgPath(imageBaseUrl + imageFilename);
            }

            // --- QuizEntityの情報を直接利用し、QuizDtoに変換 ---
            QuizEntity quiz = quizRepository.findById(stamp.getQuizNo()).orElse(new QuizEntity()); // StampEntityが持つQuizEntityを取得
            dto.setQuizDto(mapper.map(quiz, QuizDto.class)); // Optionalではなく生のオブジェクトを渡す
        }


        // --- 獲得日時 (LocalDateTime) をStringに変換してセット ---
        if (getStampEntity.getDatetime() != null) {
            dto.setDate(formatter1.format(getStampEntity.getDatetime()));
        } else {
            // DATETIMEがnullの場合の処理 (DBで自動設定されるので通常はnullにならないはずだが、念のため)
            dto.setDate(null); // または適切なデフォルト値
        }

        return dto;
    }
}