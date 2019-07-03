package org.group18.back.Service;

import org.group18.back.Entity.Category;
import org.group18.back.Entity.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService  {
    public List<Goods> getCategoryGoods(int category_uid);
    public List<Category> getCategorys(int category_uid);
}
