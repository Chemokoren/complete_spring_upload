drop table IF EXISTS users;
drop table IF EXISTS role_user;
drop table IF EXISTS BLS_members;
drop table IF EXISTS BLS_categories;
drop table IF EXISTS BLS_user_category;

create TABLE users  (
    id BIGINT auto_increment NOT NULL PRIMARY KEY,
    password VARCHAR(100),
    firstName VARCHAR(40),
    lastName VARCHAR(40),
    middleName VARCHAR(40),
    gender VARCHAR(40),
    date_of_birth DATE,
    photo VARCHAR(200),
    nationality VARCHAR(40),
    national_id VARCHAR(40),
    passport_no VARCHAR(40),
    mobile_phone_number VARCHAR(40),
    home_phone_number VARCHAR(40),
    residential_county VARCHAR(40),
    residential_address VARCHAR(40),
    postal_address VARCHAR(40),
    email VARCHAR(40),
    marital_status VARCHAR(40),
    occupation VARCHAR(40),
    dialing_code VARCHAR(40)
);

create TABLE role_user  (
    id BIGINT auto_increment NOT NULL PRIMARY KEY,
    user_id BIGINT,
    role_id BIGINT
);
create TABLE BLS_members  (
    id BIGINT auto_increment NOT NULL PRIMARY KEY,
    user_id BIGINT,
    member_id INT,
    principal_id INT,
    pin VARCHAR(40),
    relation VARCHAR(40),
    job_group VARCHAR(40),
    country VARCHAR(40),
    status VARCHAR(40)
);
create TABLE BLS_categories  (
    id BIGINT auto_increment NOT NULL PRIMARY KEY,
    user_id BIGINT,
    scheme_id VARCHAR(40),
    category_name VARCHAR(40)
);
create TABLE BLS_user_category  (
    id BIGINT auto_increment NOT NULL PRIMARY KEY,
    user_id BIGINT,
    category_id BIGINT
);