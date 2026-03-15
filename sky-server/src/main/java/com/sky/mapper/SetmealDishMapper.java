package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id查询对应的套餐id
     *
     * @param ids
     * @return
     */
    //select setmeal_id from setmeal_dish where dish_id in()
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

    /**
     * 根据id修改套餐
     *
     * @param setmeal
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Setmeal setmeal);


    /**
     * 批量保存套餐和菜品的关联关系
     *
     * @param setmealDishList
     */
    void insertBatch(List<SetmealDish> setmealDishList);

    /**
     *
     *
     * @param id
     */
    void deleteBySetmealId(Long id);

    /**
     * 根据套餐id查询套餐和菜品的关联关系
     *
     * @param id
     * @return
     */
    List<SetmealDish> getBySetmealId(Long id);
}
