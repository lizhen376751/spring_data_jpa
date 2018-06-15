package com.lizhen2.service;



import com.lizhen2.entity.User;
import com.lizhen2.respository.SpecificationExecutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\6\15 0015.
 */
@Service
public class SpecificationExecutorRepositoryManager {
    @Autowired
    private SpecificationExecutorRepository dao;
    /**
     * 描述：根据name来查询用户
     */
    public Optional<User> findUserByName(final String name){
        return dao.findOne(new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        }
        );
    }

    /**
     * 描述：根据name和email来查询用户
     */
    public Optional<User> findUserByNameAndEmail(final String name, final String email){
        return dao.findOne(new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate predicate1 = cb.equal(root.get("name"), name);
                Predicate predicate2 = cb.equal(root.get("email"), email);
                list.add(predicate1);
                list.add(predicate2);
                // 注意此处的处理
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });
    }

    /**
     * 描述：组合查询
     */
    public  Optional<User> findUserByUser(final User userVo){
        return dao.findOne(new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.equal(root.get("name"), userVo.getName());
                cb.and(predicate, cb.equal(root.get("email"), userVo.getEmail()));
                cb.and(predicate, cb.equal(root.get("password"), userVo.getPassword()));
                return predicate;
            }
        });
    }

    /**
     * 描述：范围查询in方法，例如查询用户id在[2,10]中的用户
     */
    public List<User> findUserByIds(final List<Integer> ids){
        return dao.findAll(new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                return root.in(ids);
            }
        });
    }

    /**
     * 描述：范围查询gt方法，例如查询用户id大于9的所有用户
     */
    public List<User> findUserByGtId(final int id){
        return dao.findAll(new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.gt(root.get("id").as(Integer.class), id);
            }
        });
    }

    /**
     * 描述：范围查询lt方法，例如查询用户id小于10的用户
     */
    public List<User> findUserByLtId(final int id){
        return dao.findAll(new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.lt(root.get("id").as(Integer.class), id);
            }
        });
    }

    /**
     * 描述：范围查询between方法，例如查询id在3和10之间的用户
     */
    public List<User> findUserBetweenId(final int start, final int end){
        return dao.findAll(new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.between(root.get("id").as(Integer.class), start, end);
            }
        });
    }

    /**
     * 描述：排序和分页操作
     */
    public Page<User> findUserAndOrder(final int id){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return dao.findAll(new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.gt(root.get("id").as(Integer.class), id);
            }
        }, new PageRequest(0, 5, sort));
    }

    /**
     * 描述：只有排序操作
     */
    public List<User> findUserAndOrderSecondMethod(final int id){
        return dao.findAll(new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                cb.gt(root.get("id").as(Integer.class), id);
                query.orderBy(cb.desc(root.get("id").as(Integer.class)));
                return query.getRestriction();
            }
        });
    }
}
