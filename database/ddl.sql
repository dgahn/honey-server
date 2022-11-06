create table membership
(
    code    varchar(10) not null
        primary key,
    user_id varchar(9) null
);

create index membership_user_id_index
    on membership (user_id);

create table partner
(
    id       bigint       not null
        primary key,
    category int          null,
    name     varchar(50) null
);

create table point
(
    id              bigint       not null
        primary key,
    category        varchar(50) null,
    membership_code varchar(10) null,
    total_point     bigint       not null,
    version         bigint       not null
);

create index point_mc_c_index
    on point (membership_code, category);

create table point_history
(
    id              bigint       not null
        primary key,
    approved_at     datetime(6)  null,
    category        varchar(50) null,
    membership_code varchar(10) null,
    partner_name    varchar(50) null,
    type            int          null
);

create index point_history_mc_aa_index
    on point_history (membership_code, approved_at);
