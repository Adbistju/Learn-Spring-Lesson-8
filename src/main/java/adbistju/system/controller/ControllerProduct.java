package adbistju.system.controller;


import adbistju.system.repo.Product;
import adbistju.system.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControllerProduct {

    private ProductRepo productRepo;

    @Autowired
    public ControllerProduct(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping
    public String getProductPage(Model model){
        model.addAttribute("product",productRepo.showAllProducts());
        return "repo.html";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepo.findProductById(id));
        return "product_form";
    }

    @GetMapping("/createProduct")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepo.deleteProductById(id);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
       if (productRepo.getProductMap().containsKey(product.getId())) {
            productRepo.updateProduct(product);
        } else {
            productRepo.insertProduct(product);
        }
        return "redirect:/";
    }
}
