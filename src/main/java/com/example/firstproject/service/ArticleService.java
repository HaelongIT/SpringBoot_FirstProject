package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;        // 레파지터리 필드에 추가후 객체 주입

    // GET
    public List<Article> index() {
        return articleRepository.findAll();
    }
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    public Article create(ArticleForm dto) {
        Article articleEntity = dto.toEntity();
        if (articleEntity.getId() != null) {        // 클라가 id값을 직접 보내면 게시판 생성이 아니라 수정이 될 수도 있음
            return null;                            // 그걸 막기 위해, 적절히 제한하는 조건문 추가
        }                                           // (참고 : id값은 엔티티를 통해서 db에 들어갈 때 자동생성되므로 보내줄 필요없음)
        return articleRepository.save(articleEntity);
    }

    // PATCH
    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티로 변환
        Article articleEntity = dto.toEntity();
        log.info("id: {}, article: {}", id, articleEntity.toString());
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if (target == null || id != articleEntity.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, articleEntity.toString());
            return null;
        }
        //4. 업데이트 및 정상 응답(200)하기
        Article saveEntity = target.patch(articleEntity);       // 수정할 데이터를 기존 데이터에 덮어쓰기(일부 수정 위해)
//        Article updated = articleRepository.save(articleEntity);
        Article updated = articleRepository.save(saveEntity);
        return updated;
    }

    // DELETE
    public Article delete(@PathVariable Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if (target == null) {
            return null;
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return target;
    }

    // 트랜잭션 맛보기용 테스트 핸들러
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환시키기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
        // 4. 결과 값 반환하기
        return articleList;
    }
}
