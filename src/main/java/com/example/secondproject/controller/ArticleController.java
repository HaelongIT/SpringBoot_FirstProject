package com.example.secondproject.controller;

import com.example.secondproject.dto.ArticleForm;
import com.example.secondproject.entity.Article;
import com.example.secondproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        // System.out.println(form.toString());        // form 데이터 dto로 변환 확인
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        // System.out.println(article.toString());     // dto 데이터 entity로 변환 확인
        log.info(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        // System.out.println(saved.toString());       // entity가 db로 저장된거 확인
        log.info(saved.toString());
        // return "/articles/show";                     // 일반 반환으로는 show로 연결 불가능
        return "redirect:/articles/" + saved.getId();      // 리다이렉트를 통해서 show로 연결 가능
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        // id를 조회해 db에서 해당 데이터 가져오기
//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 가져온 데이터를 모델에 등록하기
        model.addAttribute("article", articleEntity);
        // 조회한 데이터를 사용자에게 보여주기 위한 뷰페이지로 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        // 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        // 리파지터리에서 해당 데이터 찾아오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 엔티티를 모델로 등록
        model.addAttribute("article", articleEntity);
        // 수정 페이지에 해당 데이터를 보여줘야 함
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());          // 수정 데이터 dto로 받은 것 확인
        // DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());       // 엔티티로 변환된 것 확인
        // 리파지터리로 엔티티를 db에 저장
        Article target = articleRepository.findById(article.getId()).orElse(null);
        if (target != null) {
            Article saved = articleRepository.save(article);
            log.info(saved.toString());
        }
        // 뷰 페이지 설정(수정된 <글 상세페이지>로 연결)
        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("id = " + id + " 라는 데이터를 삭제하는 요청이 들어옴!");
        // 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());            // target에 데이터 있는지 확인
        // 검증 후 대상 엔티티 삭제
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!!");  // 휘발성 데이터 등록
            log.info("대상 삭제 완료");
        }
        // <결과 페이지 = 전체 글목록 페이지>로 리다이렉트
        return "redirect:/articles";
    }
}
