package org.group18.back.Service;

import org.group18.back.Dao.CategoryMapper;
import org.group18.back.Dao.GoodsMapper;
import org.group18.back.Entity.Category;
import org.group18.back.Entity.CategoryExample;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.GoodsExample;
import org.group18.back.Service.Impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> getCategoryGoods(int category_uid) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria1 = goodsExample.createCriteria();
        criteria1.andCategoryUidEqualTo(category_uid);

        List<Goods> result = goodsMapper.selectByExample(goodsExample);
        return result;
    }

    @Override
    public List<Category> getCategorys(int category_uid) {
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andUidEqualTo(category_uid);

        List<Category> result1 = categoryMapper.selectByExample(categoryExample);
        return result1;
    }


}

