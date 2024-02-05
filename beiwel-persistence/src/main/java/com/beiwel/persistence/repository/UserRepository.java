package com.beiwel.persistence.repository;

import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.filter.UserFilter;
import com.beiwel.persistence.repository.specification.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends AbstractRepository<UserEntity> {
    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findByNickname(String nickname);
    UserEntity findByPhoneNumber(String phoneNumber);


    UserEntity findByNicknameAndRolesId(String nickname, Long roleId);

    UserEntity findByHashPreregister(String hashPreregister);

    List<UserEntity> findAll();

    UserEntity findById(long userId);

    default Page<UserEntity> findAllByFilter(UserFilter userFilter) {
        UserSpecification userSpecification = new UserSpecification(userFilter);

        Sort sort = Sort.by(Direction.DESC, "id");
        Pageable pageable =
                PageRequest.of(userFilter.getPage(), userFilter.getSize(), sort);

        return findAll(userSpecification, pageable);
    }
}
