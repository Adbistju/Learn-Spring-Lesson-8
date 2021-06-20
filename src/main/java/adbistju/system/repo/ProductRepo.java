package adbistju.system.repo;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepo {
    private Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private AtomicLong identity = new AtomicLong(0);

    public Map<Long, Product> getProductMap() {
        return productMap;
    }

    public void productId () {
        System.out.println(productMap.entrySet());
    }

    @PostConstruct
    public void initRepo() {
        this.insertProduct(new Product("paint","black ink",5));
        this.insertProduct(new Product("brushes","0.5 brush",2));
        this.insertProduct(new Product("paint","red paint",8));
        this.insertProduct(new Product("pencils","HB black pencil",1));

    }

    public List<Product> showAllProducts() {
        return new ArrayList<>(productMap.values());
    }


    public Product findProductById (long id) {
        return productMap.get(id);
    }


    public void insertProduct (Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    public void updateProduct (Product product) {
        productMap.put(product.getId(),product);

    }

    public void deleteProductById (long id) {
        productMap.remove(id);
    }

    public int repoSize() {
        int i = productMap.size();
        return i;
    }

}
