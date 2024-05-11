drop table if exists Comment;
drop table if exists Post;
drop table if exists Topic;
drop table if exists "user";


create table Topic
(
    id   bigserial
        constraint Topic_pk
            primary key,
    name varchar not null
);

alter table Topic
    owner to postgres;

create table "user"
(
    id       bigserial
        constraint user_pk
            primary key,
    name     varchar,
    password varchar
);

alter table "user"
    owner to postgres;

create table Post
(
    id          bigserial
        constraint Post_pk
            primary key,
    author      bigint
        constraint Post_user_null_fk not null
        references "user",
    create_dt   bigint                not null,
    end_dt      bigint,
    update_dt   bigint,
    topicId     bigint                not null
        constraint Post_Topic_null_fk
            references Topic,
    content     text,
    view_count  bigint  default 0     not null,
    upvotes     bigint  default 0     not null,
    downvotes   integer default 0     not null,
    isValidated boolean default false not null
);

alter table Post
    owner to postgres;

create table Comment
(
    id      bigserial
        constraint Comment_pk
            primary key,
    author  bigint,
    content text   not null,
    post_id bigint not null
        constraint Comment_Post_null_fk
            references Post
);

alter table Comment
    owner to postgres;

INSERT INTO "user" (name, password) VALUES ('DmitryP', '$2a$10$9Cb10Lwl9bQ9X2tO1o8fl.T0DU0Uj0TTvlIY6opirZ5CLIzUJH5Xi');

