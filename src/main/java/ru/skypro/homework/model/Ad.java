package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;
/**
 * Сущность объявления.
 * <p>
 * Содержит информацию:
 * <ul>
 *   <li>Заголовок и описание объявления</li>
 *   <li>Цену</li>
 *   <li>Ссылку на изображение</li>
 *   <li>Автора объявления</li>
 *   <li>Комментарии к объявлению</li>
 * </ul>
 */
@Entity
@Table(name = "ads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "image")
    private String image;
    @Column(name = "price", nullable = false)
    private Integer price;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne
    private User author;
}
