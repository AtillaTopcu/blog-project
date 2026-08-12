package com.atillatpc.data.entity;

import com.atillatpc.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="blog_categories")
public class BlogCategoryEntity extends AuditingAwareBaseEntity {

    @Serial
    private static final long serialVersionUID = 1l; // Serial

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_Category_Id")
    private Long blogCategoryId;
    @Column(unique = true, nullable = false, length = 200)
    private String categoryName;

    //relation
    @OneToMany(mappedBy = "blogCategoryEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BlogEntity> blogEntityList;
}