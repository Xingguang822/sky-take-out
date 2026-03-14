package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味数据
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 删除被删除菜品的风味
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据菜品id对应的口味数据
     *
     * @param id
     * @return
     */
    List<DishFlavor> getByDishId(Long id);
}
