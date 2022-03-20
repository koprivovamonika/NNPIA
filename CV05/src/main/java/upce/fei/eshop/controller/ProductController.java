package upce.fei.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import upce.fei.eshop.dto.AddOrEditProductDTO;
import upce.fei.eshop.entity.Producer;
import upce.fei.eshop.entity.Product;
import upce.fei.eshop.repository.ProducerRepository;
import upce.fei.eshop.repository.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @ExceptionHandler(RuntimeException.class)
    public String handleException(){
        return "error";
    }

    @GetMapping("/")
    public String showAllProducts(Model model){
        model.addAttribute("productList", productRepository.findAll());
        return "product-list";
    }

    @GetMapping(value = {"/product-form", "/product-form/{id}"})
    public String showProductForm(@PathVariable(required = false) Long id, Model model){
        if (id != null){
           Product product = productRepository.findById(id).orElse(new Product());
           model.addAttribute("product", product);
        }else{
            model.addAttribute("product", new AddOrEditProductDTO());
        }

        return "product-form";
    }

    @PostMapping("/product-form-process")
    public String productFormProcess(AddOrEditProductDTO addOrEditProductDTO){
        Producer producer = new Producer();
        producer.setName("producer2");
        producer.setEmail("pr2@email.com");
        producer.setPhone("777777777");
        producerRepository.save(producer);

        Product product = new Product();
        product.setProducer(producer);
        product.setPrice(100.8);
        product.setId(addOrEditProductDTO.getId());
        product.setProductName(addOrEditProductDTO.getProductName());
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/product-detail/{id}")
    public String showProductDetail(@PathVariable(required = true) Long id, Model model){
        model.addAttribute("product", productRepository.findById(id).get());
        return "product-detail";
    }
}
