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

        path = "C:/Users/이재훈/IdeaProjects/product/product/src/main/webapp/product/file/";
        System.out.println("===> path:" + path);

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs(); // 경로가 없을 경우 경로를 생성
        }

        Random rnd = new Random();
        String RName = (rnd.nextInt(999999)+100001)+"";

        MultipartFile file = vo.getImage();
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {
                String	Fname = fileName.substring(0, fileName.lastIndexOf("."));
                String Lname = fileName.substring(fileName.lastIndexOf("."));

                fileName = Fname + "_" + RName + Lname;

                file.transferTo(new File(path+fileName)); // 파일저장

        } else {
            fileName ="space.png";
        }
        vo.setStrImage(fileName);

        service.insert(vo);
        System.out.println("=> ProductInsert : " + vo);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    void ProductList(ProductVO vo, Model model){
        System.out.println("=> ProductList : " + service.select(vo));
        model.addAttribute("li", service.select(vo));
    }
    @GetMapping("/delete")
    String ProductDelete(ProductVO vo, HttpServletRequest request){
        System.out.println("=> ProductDelete");
        vo.setIdx(Integer.parseInt(request.getParameter("idx")));
        service.delete(vo);
        return "redirect:/product/list";
    }
}
