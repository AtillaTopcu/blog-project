package com.atillatpc.data.entity;

import com.atillatpc.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name="blog_categories")
public class BlogEntity extends AuditingAwareBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long blogId;
    @Column(unique = true,nullable = false)
    private String header;
    private String title;
    @Lob
    private String content;
    private String image = "resim.png";

    // Relations
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "blog_Category_Id", nullable = false)
    private BlogCategoryEntity blogCategoryEntity;
}