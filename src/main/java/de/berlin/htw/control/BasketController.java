package de.berlin.htw.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import de.berlin.htw.boundary.dto.Basket;
import de.berlin.htw.boundary.dto.Item;
import de.berlin.htw.entity.dao.UserRepository;
import de.berlin.htw.entity.dto.UserEntity;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.hash.HashCommands;
import io.quarkus.redis.datasource.string.StringCommands;
import io.quarkus.redis.datasource.list.ListCommands;

import java.util.Map;

/**
 * @author Alexander Stanik [stanik@htw-berlin.de]
 */
@Dependent
public class BasketController {

    @Inject
    protected RedisDataSource redisDS;

    UserRepository userRepository;



    protected StringCommands<String, Integer> countCommands;
    protected ListCommands<String, String> stringListCommands;
    protected HashCommands<String, String, Item> hashCommands;



    @PostConstruct
    protected void init() {
        countCommands = redisDS.string(Integer.class);
        stringListCommands = redisDS.list(String.class);
        hashCommands = redisDS.hash(Item.class);
    }

    public Map<String, Item> todo(int userId) {
        return hashCommands.hgetall(userId + ":basket");
    }
    public float add(int userId, String field, Item value){
        Basket basket = new Basket();
        UserEntity user = userRepository.findUserById(userId);
        float userBalance = user.getBalance();

        Map<String, Item> allItems = todo(userId);
        float totalPrice = 0;
        for(Item item : allItems.values()){
            totalPrice += item.getPrice() * item.getCount();
        }

        totalPrice = totalPrice + value.getPrice() * value.getCount();
        basket.setRemainingBalance(totalPrice);
        if(userBalance >= basket.getRemainingBalance()){
            hashCommands.hset(userId + ":basket",field, value);
        }
        basket.setRemainingBalance(userBalance - basket.getRemainingBalance());
        return basket.getRemainingBalance();
    }
    public void deleteAll(int userId){
        hashCommands.hdel(userId + ":basket");
    }
    public float deleteItem(int userId, String productId){
        Basket basket = new Basket();
        hashCommands.hdel(userId + ":basket", productId);

        Map<String, Item> allItems = todo(userId);
        float totalPrice = 0;
        for(Item item : allItems.values()){
            totalPrice += item.getPrice() * item.getCount();
        }
        basket.setRemainingBalance(totalPrice);
        return basket.getRemainingBalance();
    }
    public void changeCount(int userId, String productId, Integer count){

        Basket basket = new Basket();
       /* UserEntity user = userRepository.findUserById(userId);
        float userBalance = user.getBalance();

        Map<String, Item> allItems = todo(userId);
        float totalPrice = 0;
        for(Item item : allItems.values()){
            totalPrice += item.getPrice() * item.getCount();
        }

        basket.setRemainingBalance(totalPrice);

        if(userBalance >= basket.getRemainingBalance()){
            item.setCount(count);
        }*/
    }

    }
