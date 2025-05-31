package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    @Query("SELECT a FROM Ad a WHERE a.user.id = :userId")
    List<Ad> findAllByAuthorId(@Param("userId") Integer userId);

    List<Ad> findByUser(User user);

    // Для работы с изображениями
    @Modifying
    @Query("UPDATE Ad a SET a.image = :image WHERE a.id = :id")
    void updateImage(@Param("id") Integer id, @Param("image") String image);
}
