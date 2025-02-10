create table ww_third_corp
(
    id             varchar(128),
    name           varchar(256),
    permanent_code varchar(1024),
    auth_info      text,
    create_time    datetime
);

create table ww_third_agent
(
    id              bigint primary key,
    corp_id         varchar(128),
    agent_id        bigint,
    agent_name      varchar(128),
    auth_mode       tinyint,
    privilege_level tinyint,
    create_time     datetime,
    is_deleted      tinyint,
    delete_time     datetime
);

create table ww_third_agent_member
(
    id          bigint primary key,
    corp_id     varchar(128),
    agent_id    bigint,
    member_type varchar(10) comment '成员类型，user:用户,dept:部门,tag:标签',
    member_id   varchar(128),
    create_time datetime,
    is_deleted  tinyint,
    delete_time datetime
);