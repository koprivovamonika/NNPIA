package upce.fei.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import upce.fei.eshop.dto.AddOrEditProducerDTO;
import upce.fei.eshop.entity.Producer;
import upce.fei.eshop.entity.Product;
import upce.fei.eshop.repository.ProducerRepository;
import upce.fei.eshop.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ProducerController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @ExceptionHandler(RuntimeException.class)
    public String handleException(){
        return "error";
    }

    @GetMapping("/producer-list")
    public String showAllProducers(Model model){
        model.addAttribute("producerList", producerRepository.findAll());
        return "producer-list";
    }

    @GetMapping(value = {"/producer-form", "/producer-form/{id}"})
    public String showProducerForm(@PathVariable(required = false) Long id, Model model){
        if (id != null){
            Producer producer = producerRepository.findById(id).orElse(new Producer());
            model.addAttribute("producer", producer);
        }else{
            model.addAttribute("producer", new AddOrEditProducerDTO());
        }

        return "producer-form";
    }

    @PostMapping("/producer-form-process")
    public String producerFormProcess(AddOrEditProducerDTO addOrEditProducerDTO){
        Producer producer = new Producer();
        producer.setId(addOrEditProducerDTO.getId());
        producer.setName(addOrEditProducerDTO.getName());
        producer.setEmail(addOrEditProducerDTO.getEmail());
        producer.setPhone(addOrEditProducerDTO.getPhone());
        producerRepository.save(producer);

        return "redirect:/producer-list";
    }

    @GetMapping("/producer-detail/{id}")
    public String showProducerDetail(@PathVariable(required = true) Long id, Model model){
        model.addAttribute("producer", producerRepository.findById(id).get());
        return "producer-detail";
    }

    @GetMapping("/producer-remove/{id}")
    public String shoppingCartRemove(@PathVariable Long id, Model model){
        Producer producer = producerRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<Product> all = productRepository.findAll();
        Boolean canDelete = true;
        for (Product product : all) {
            if(product.getProducer().getId() == id){
                canDelete = false;
            }
        }
        if(canDelete){
            producerRepository.delete(producer);
            return "redirect:/producer-list";
        }else{
            return "delete-error";
        }

    }
}
