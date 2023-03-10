CREATE TABLE `account`
(
    `id`                           int          not null primary key auto_increment,
    `oauth_identifier`             varchar(100) not null,
    `user_identifier`              char(36)     not null,
    `oauth_type`                   varchar(20)  not null,
    `is_spam_notification_aggreed` boolean      not null,
    `status`                       varchar(20)  not null,
    `created_at`                   timestamp    not null,
    unique index UDX_USER_IDENTIFIER (`user_identifier`)
);

CREATE TABLE `alarm`
(
    `id`              int          not null primary key auto_increment,
    `user_identifier` char(36)     not null,
    `enable`          boolean      not null default false,
    `iteration`       varchar(128) null,
    unique index UDX_USER_IDENTIFIER (`user_identifier`)
);

CREATE TABLE `blog`
(
    `id`              int           not null primary key auto_increment,
    `blog_identifier` char(36)      not null,
    `user_identifier` char(36)      not null,
    `url`             varchar(255) not null,
    unique index UDX_BLOG_IDENTIFIER (`blog_identifier`),
    index IDX_USER_IDENTIFIER (`user_identifier`)
);

create table `category`
(
    `id`         int          not null primary key auto_increment,
    `identifier` char(36) not null,
    `name`       varchar(255) not null,
    `createdAt`  datetime     not null,
    unique index UDX_CATEGORY_IDENTIFIER (`identifier`)
);

create table `draft`
(
    `id`              int          not null primary key auto_increment,
    `user_identifier` char(36) not null,
    `data`            text         not null,
    `updated_at`      datetime     not null,
    unique index UDX_USER_IDENTIFIER (`user_identifier`)
);

CREATE TABLE `post`
(
    `id`                  int           not null primary key auto_increment,
    `identifier`          char(36)      not null,
    `category_identifier` char(36)      not null,
    `user_identifier`     char(36)      not null,
    `title`               varchar(30)   not null,
    `description`         varchar(250)  not null,
    `url`                 varchar(255) not null,
    `hit_count`           int           not null,
    `created_at`          timestamp     not null,
    unique index UDX_IDENTIFIER (`identifier`),
    index IDX_USER_IDENTIFIER (`user_identifier`)
);

CREATE TABLE `recommended_post`
(
    `id`                  int       not null primary key auto_increment,
    `post_identifier`     char(36)  not null,
    `category_identifier` char(36)  not null,
    `created_at`          timestamp not null,
    index IDX_CATEGORY_IDENTIFIER (`category_identifier`)
);

CREATE TABLE `user`
(
    `id`                  int           not null primary key auto_increment,
    `name`                varchar(30)   not null,
    `user_identifier`     char(36)      not null,
    `category_identifier` varchar(20)   not null,
    `introduction`        TEXT,
    `profile_img_src`     varchar(255),
    `path`                varchar(255) not null,
    `status`              varchar(20)   not null,
    unique index UDX_USER_IDENTIFIER (`user_identifier`),
    unique index UDX_NAME (`name`),
    unique index UDX_PATH (`path`)
);
