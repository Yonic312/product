package com.order.Controller;

import com.product.ProductApplication;
import com.product.ProductService;
import com.product.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@RequestMapping("/product")
@Controller
public class ProductController {

    @Autowired
    ProductService service;
    String path = "";

    ProductController(){
        System.out.println("ProductController 생성자");
    }

    @GetMapping("/form")
    void ProductForm(){
        System.out.println("=> ProductForm");
        ProductVO vo = new ProductVO();
    }

    @PostMapping("/insert")
    String ProductInsert(ProductVO vo, HttpServletRequest request) throws IOException {

        // 경로 잡기
        path = "C:/Users/이재훈/IdeaProjects/product/product/src/main/webapp/product/file/";
        System.out.println("===> path:" + path);

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs(); // 경로가 없을 경우 경로를 생성
        }

        Random rnd = new Random();
        String RName = (rnd.nextInt(999999)+100001)+"";

        MultipartFile file = vo.getImage();
        String fileName = file.getOriginalFilename(); // 파일 이름을 String으로 넣기
        if (!file.isEmpty()) {
                String Fname = fileName.substring(0, fileName.lastIndexOf(".")); // . 앞에
                String Lname = fileName.substring(fileName.lastIndexOf(".")); // . 뒤에
                fileName = Fname + "_" + RName + Lname;

                file.transferTo(new File(path+fileName)); // 파일저장

        } else {
            fileName ="space.png";
        }
        vo.setStrImage(fileName);

        service.insert(vo);
        System.out.println("=> ProductInsert : " + vo);
        return "redirect:/product/list?nowPage=0";
    }

    @GetMapping("/list")
    void ProductList(ProductVO vo, Model model, HttpServletRequest request){
        System.out.println("=> ProductList : " + service.select(vo));
        vo.setNowPage(Integer.parseInt(request.getParameter("nowPage")));

        System.out.println("vo.getNowPage() : " + vo.getNowPage());
        // 쿼리 시작과 끝을 정하기
        vo.setQs((vo.getNowPage()) * vo.getPageSize());
        vo.setQe(vo.getQs()+vo.getPageSize());

        vo.setSearchKeywordR("%"+vo.getSearchKeyword()+"%");

        model.addAttribute("li", service.select(vo));
        model.addAttribute("m", service.totalCount(vo.getSearchCondition(), vo.getSearchKeywordR()));

        // tp(총 페이지)
        if(service.totalCount(vo.getSearchCondition(), vo.getSearchKeywordR()) % 10 == 0) {
            vo.setTp(service.totalCount(vo.getSearchCondition(), vo.getSearchKeywordR()) / 10) ;
        } else { // 총 페이지 수가 10으로 나누어 떨어지지 않으면 1페이지 추가
            vo.setTp(service.totalCount(vo.getSearchCondition(), vo.getSearchKeywordR()) / 10 +1);
        }

        model.addAttribute("vo", vo);
    }

    @GetMapping("/delete")
    String ProductDelete(ProductVO vo, HttpServletRequest request){
        System.out.println("=> ProductDelete");
        vo.setIdx(Integer.parseInt(request.getParameter("idx")));
        service.delete(vo);
        return "redirect:/product/list";
    }

    @GetMapping("/edit")
    void ProductEdit(ProductVO vo, Model model){
        System.out.println("=> ProductEdit : " + service.selectOne(vo));
        model.addAttribute("li", service.selectOne(vo));
    }


    @PostMapping("/update")
    String ProductUpdate(ProductVO vo, HttpServletRequest request) throws IOException {

        path = "C:/Users/이재훈/IdeaProjects/product/product/src/main/webapp/product/file/";
        System.out.println("===> path:" + path);

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs(); // 경로가 없을 경우 경로를 생성
        }

        Random rnd = new Random();
        String RName = (rnd.nextInt(999999) + 100001) + "";

        MultipartFile file = vo.getImage();
        String fileName = file.getOriginalFilename();

        // 기존 이미지 값을 가져옵니다.
        ProductVO existingProduct = service.selectOne(vo);
        String existingImage = existingProduct.getStrImage();

        if (!file.isEmpty()) {
            String Fname = fileName.substring(0, fileName.lastIndexOf("."));
            String Lname = fileName.substring(fileName.lastIndexOf("."));

            fileName = Fname + "_" + RName + Lname;

            file.transferTo(new File(path + fileName)); // 파일 저장
        } else {
            // 파일이 없는 경우 기존 이미지를 유지합니다.
            fileName = existingImage != null ? existingImage : "space.png";
        }

        vo.setStrImage(fileName);

        service.update(vo);
        System.out.println("=> ProductUpdate : " + vo);
        return "redirect:/product/list";
    }





}
