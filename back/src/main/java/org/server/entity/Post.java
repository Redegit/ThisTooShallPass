package org.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('Post_id_seq'")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @Column(name = "create_dt", nullable = false)
    private Long createDt;

    @Column(name = "end_dt")
    private Long endDt;

    @Column(name = "update_dt")
    private Long updateDt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"topicId\"", nullable = false)
    private Topic topic;

    @Column(name = "content", length = Integer.MAX_VALUE)
    private String content;

    @ColumnDefault("0")
    @Column(name = "view_count", nullable = false)
    private Long viewCount;

    @ColumnDefault("0")
    @Column(name = "upvotes", nullable = false)
    private Long upvotes;

    @ColumnDefault("0")
    @Column(name = "downvotes", nullable = false)
    private Integer downvotes;

    @ColumnDefault("false")
    @Column(name = "\"isValidated\"", nullable = false)
    private Boolean isValidated = false;

}