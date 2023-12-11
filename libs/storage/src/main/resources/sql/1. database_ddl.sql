CREATE TABLE `account`
(
    `id`                          int          not null primary key auto_increment,
    `user_identifier`             char(36)     not null,
    `o_auth_type`                 varchar(20)  not null,
    `is_spam_notification_agreed` boolean      not null,
    `status`                      varchar(20)  not null,
    `email`                       varchar(100) not null,
    `created_at`                  timestamp    not null default now(),
    unique index UDX_USER_IDENTIFIER (`user_identifier`)
);

CREATE TABLE `blog`
(
    `id`              int          not null primary key auto_increment,
    `blog_identifier` char(36)     not null,
    `user_identifier` char(36)     not null,
    `url`             varchar(255) not null,
    unique index UDX_BLOG_IDENTIFIER (`blog_identifier`),
    index IDX_USER_IDENTIFIER (`user_identifier`)
);

create table `category`
(
    `id`         int          not null primary key auto_increment,
    `identifier` char(36)     not null,
    `name`       varchar(255) not null,
    unique index UDX_CATEGORY_IDENTIFIER (`identifier`)
);

create table `draft`
(
    `id`              int      not null primary key auto_increment,
    `user_identifier` char(36) not null,
    `data`            text     not null,
    `updated_at`      datetime not null,
    unique index UDX_USER_IDENTIFIER (`user_identifier`)
);

CREATE TABLE `post`
(
    `id`                  int           not null primary key auto_increment,
    `identifier`          char(36)      not null,
    `category_identifier` char(36)      not null,
    `user_identifier`     char(36)      not null,
    `title`               varchar(200)  not null,
    `description`         varchar(1000) not null,
    `url`                 varchar(255)  not null,
    `hit_count`           int           not null,
    `created_at`          timestamp     not null default now(),
    unique index UDX_IDENTIFIER (`identifier`),
    index IDX_USER_IDENTIFIER (`user_identifier`)
);

CREATE TABLE `recommended_post`
(
    `id`                  int       not null primary key auto_increment,
    `post_identifier`     char(36)  not null,
    `category_identifier` char(36)  not null,
    `created_at`          timestamp not null default now(),
    index IDX_CATEGORY_IDENTIFIER (`category_identifier`)
);

CREATE TABLE `token`
(
    `id`                   int          not null primary key auto_increment,
    `user_identifier`      char(36)     not null,
    `access_token`         varchar(512) not null,
    `refresh_token`        varchar(512) not null,
    `refresh_token_expire` timestamp    not null default now(),
    index `idx_token_userIdentifier_accessToken` (`user_identifier`, `access_token`)
);

CREATE TABLE `user`
(
    `id`                  int          not null primary key auto_increment,
    `name`                varchar(10)  not null,
    `user_identifier`     char(36)     not null,
    `category_identifier` char(36)     null,
    `introduction`        varchar(200) null,
    `thumbnail_url`       varchar(255) not null,
    `path`                varchar(255) not null,
    `status`              varchar(20)  not null,
    unique index UDX_USER_IDENTIFIER (`user_identifier`),

    unique index UDX_PATH (`path`)
);


/*Spec 1.5*/
CREATE TABLE `question_type`
(
    `id`                  int          not null primary key auto_increment,
    `question_type_name`                varchar(255)  not null,
    `question_type`     char(255)     not null
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `question`
(
    `id`                  int          not null primary key auto_increment,
    `question_name`                varchar(255)  not null,
    `question_type`     char(255)     not null
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `retrospect`
(
    `id`                        int          not null primary key auto_increment,
    `retrospect_identifier`     char(36)     not null,
    `category_identifier`     char(36)     not null,
    `user_identifier`           char(36)      not null,
    `question_type`           char(255)      not null,
    `is_secret` boolean      not null,
    `created_at`                  timestamp    not null default now()
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `retrospect_qna`
(
    `id`                        int          not null primary key auto_increment,
    `retrospect_identifier`     char(36)     not null,
    `question_name`     char(255)     not null,
    `answer`                varchar(255) not null
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `recommended_retrospect`
(
    `id`                        int          not null primary key auto_increment,
    `retrospect_identifier`     char(36)     not null,
    `category_identifier`     char(36)     not null,
    `created_at`                  timestamp    not null default now()
)DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;