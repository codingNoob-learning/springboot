package com.fastcampus.java.model.entity;

import com.fastcampus.java.model.enumclass.ItemStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderDetailList", "partner"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;          // 등록 / 해지 / 검수중(등록대기중)
    private String name;
    private String title;
    private String content;
    private BigDecimal price;
    private String brandName;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    // Item N : 1 Partner
    @ManyToOne
    private Partner partner;

    // Item 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

    // LAZY : 1 : N
    // LAZY = 지연로딩  , EAGER = 즉시로딩

    // LAZY = SELECT * FROM item where id = ?

    // EAGER = 1:1, N:1 추천 / 성능 저하 우려
    // item_id = order_detail.item.id
    // user_id = order_detail.user_id
    // where item.id = ?
    // JOIN item item0_ left outer
    // join order_detail orderdetai1_ on item0_.id=orderdetai1_.item_id left outer
    // join user user2_ on orderdetai1_.user_id=user2_.id
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    // private List<OrderDetail> orderDetailList;

}