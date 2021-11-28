package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.dto.UserDto;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query("Select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);


    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDayBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameLikeOrderByIdAsc(String name);

    List<User> findByNameContainingOrderByIdDesc(String name);

    @Query("SELECT new com.fundamentosplatzi.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDay)"+
    " FROM User u "+
    " where u.birthDay=:parametroFecha " +
    " and u.email=:parametroEmail")
    Optional<UserDto> getAllByBirthDayAndEmail(@Param("parametroFecha") LocalDate date,
                                               @Param("parametroEmail") String email);

        List<User> findAll();

}
