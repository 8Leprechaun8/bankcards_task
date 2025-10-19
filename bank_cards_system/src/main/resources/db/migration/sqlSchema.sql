--liquibase formatted sql

--changeset dvoyevodin_17102025:1
CREATE TABLE users (
	id UUID PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
	second_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	login VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	role VARCHAR(255) NOT NULL,
	archive_flag BOOLEAN NOT NULL,
	email VARCHAR(255) NOT NULL
);

--changeset dvoyevodin_17102025:2
INSERT INTO users (id, first_name, second_name, last_name, login, password, role, archive_flag, email)
VALUES ('4fbd3fff-4476-4380-a2a7-7721685425fc', 'Иванов', 'Иван', 'Иванович', 'User_Ivanov', 'Pa$$w0rd', 'ROLE_ADMIN', false, 'ivanov@mail.ru');

--changeset dvoyevodin_17102025:3
INSERT INTO users (id, first_name, second_name, last_name, login, password, role, archive_flag, email)
VALUES ('a02c2413-07eb-477f-801f-f5537c8a0788', 'Петров', 'Петр', 'Петрович', 'User_Petrov', 'Pa$$w0rd', 'ROLE_ADMIN', false, 'petrov@mail.ru');

--changeset dvoyevodin_17102025:4
INSERT INTO users (id, first_name, second_name, last_name, login, password, role, archive_flag, email)
VALUES ('57b1cff4-bb2d-46fb-9812-639aa014f64c', 'Сидоров', 'Семен', 'Семенович', 'User_Sidorov', 'Pa$$w0rd', 'ROLE_ADMIN', false, 'sidorov@mail.ru');

--changeset dvoyevodin_17102025:5
INSERT INTO users (id, first_name, second_name, last_name, login, password, role, archive_flag, email)
VALUES ('acd71c43-8dff-44de-94db-4eb3c6f972f9', 'Александров', 'Александр', 'Александрович', 'User_Alexandrov', 'Pa$$w0rd', 'ROLE_USER', false, 'alex@mail.ru');

--changeset dvoyevodin_17102025:6
INSERT INTO users (id, first_name, second_name, last_name, login, password, role, archive_flag, email)
VALUES ('dcad40f2-e5f1-4643-a8f6-8cbf78d7ea7d', 'Дмитриев', 'Дмитрий', 'Дмитриевич', 'User_Dmitriev', 'Pa$$w0rd', 'ROLE_USER', false, 'dmitr@mail.ru');

--changeset dvoyevodin_17102025:7
INSERT INTO users (id, first_name, second_name, last_name, login, password, role, archive_flag, email)
VALUES ('3b7361fa-90a3-47ea-bea6-a9e67acd0f50', 'Андреев', 'Андрей', 'Андреевич', 'User_Andreev', 'Pa$$w0rd', 'ROLE_USER', false, 'andreev@mail.ru');

--changeset dvoyevodin_17102025:8
INSERT INTO users (id, first_name, second_name, last_name, login, password, role, archive_flag, email)
VALUES ('0ad4dcb7-57b3-49b9-9008-17891a3eecca', 'Николаев', 'Николай', 'Николаевич', 'User_Nicolaev', 'Pa$$w0rd', 'ROLE_USER', false, 'nikolaev@mail.ru');

--changeset dvoyevodin_17102025:9
INSERT INTO users (id, first_name, second_name, last_name, login, password, role, archive_flag, email)
VALUES ('55b5b405-2dd6-4f66-8280-5c0a22d9b95f', 'Михаилов', 'Михаил', 'Михайлович', 'User_Michailov', 'Pa$$w0rd', 'ROLE_USER', false, 'michailov@mail.ru');

--changeset dvoyevodin_17102025:10
CREATE TABLE cards (
	id UUID PRIMARY KEY,
	number VARCHAR(19) NOT NULL,
	expiration TIMESTAMP NOT NULL,
	balance DECIMAL(10, 5) NOT NULL,
	status VARCHAR(255) NOT NULL,
	user_id UUID NOT NULL,
	archive_flag BOOLEAN NOT NULL,
	CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

--changeset dvoyevodin_17102025:11
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('5c6e09e9-153d-45fc-bcfd-9b6e48f0d6f0', '1234 5678 0001 0001', NOW() + INTERVAL '1 day', 100, 'ACTIVE', '4fbd3fff-4476-4380-a2a7-7721685425fc', false);

--changeset dvoyevodin_17102025:12
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('0b084568-f09d-45ea-ad0b-2233fc6c1e44', '1234 5678 0002 0002', NOW() + INTERVAL '1 day', 0, 'ACTIVE', '4fbd3fff-4476-4380-a2a7-7721685425fc', false);

--changeset dvoyevodin_17102025:13
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('afc475ec-e5b7-4ef0-abe2-86c132a9bacc', '1234 5678 0003 0003', NOW() + INTERVAL '1 day', 200, 'BLOCKED', '4fbd3fff-4476-4380-a2a7-7721685425fc', false);

--changeset dvoyevodin_17102025:14
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('1bbb566e-138e-4554-8889-a65a08d6ef7b', '1234 5678 0004 0004', NOW() + INTERVAL '1 day', 300, 'ACTIVE', 'a02c2413-07eb-477f-801f-f5537c8a0788', false);

--changeset dvoyevodin_17102025:15
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('6e107fad-e25a-41b1-bb32-ddc095f81bf4', '1234 5678 0005 0005', NOW() + INTERVAL '1 day', 0, 'ACTIVE', 'a02c2413-07eb-477f-801f-f5537c8a0788', false);

--changeset dvoyevodin_17102025:16
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('aaf79d27-0d2d-4a4b-b9d6-8d137a2e2d36', '1234 5678 0006 0006', NOW() - INTERVAL '1 day', 0, 'EXPIRED', 'a02c2413-07eb-477f-801f-f5537c8a0788', false);

--changeset dvoyevodin_17102025:17
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('03775511-f326-4af1-a7d0-9fce77ac56c6', '1234 5678 0007 0007', NOW() + INTERVAL '1 day', 500, 'ACTIVE', '57b1cff4-bb2d-46fb-9812-639aa014f64c', false);

--changeset dvoyevodin_17102025:18
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('c3c05d2e-e6e5-45d6-a958-6ee02b265500', '1234 5678 0008 0008', NOW() + INTERVAL '1 day', 600, 'ACTIVE', '57b1cff4-bb2d-46fb-9812-639aa014f64c', false);

--changeset dvoyevodin_17102025:19
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('a9709f8d-78c9-46aa-ab8a-30323ffd7d00', '1234 5678 0009 0009', NOW() + INTERVAL '1 day', 300, 'ACTIVE', '57b1cff4-bb2d-46fb-9812-639aa014f64c', false);

--changeset dvoyevodin_17102025:20
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('220e14a6-7497-4f5d-8582-371c82583b18', '1234 5678 0010 0010', NOW() + INTERVAL '1 day', 200, 'ACTIVE', 'acd71c43-8dff-44de-94db-4eb3c6f972f9', false);

--changeset dvoyevodin_17102025:21
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('df4c2dfe-2e52-460f-ada0-f04dbd44d678', '1234 5678 0011 0011', NOW() + INTERVAL '1 day', 800, 'BLOCKED', 'acd71c43-8dff-44de-94db-4eb3c6f972f9', false);

--changeset dvoyevodin_17102025:22
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('60bc17a0-a886-403a-9a82-e7e9bc3b5795', '1234 5678 0012 0012', NOW() + INTERVAL '1 day', 300, 'BLOCKED', 'acd71c43-8dff-44de-94db-4eb3c6f972f9', false);

--changeset dvoyevodin_17102025:23
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('63a6cd2e-0968-4745-8e2a-c73d2c2dd4ea', '1234 5678 0013 0013', NOW() + INTERVAL '1 day', 500, 'ACTIVE', 'dcad40f2-e5f1-4643-a8f6-8cbf78d7ea7d', false);

--changeset dvoyevodin_17102025:24
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('a7f02b6e-ef51-4c46-aa3f-d6c2ebae36d9', '1234 5678 0014 0014', NOW() + INTERVAL '1 day', 200, 'BLOCKED', 'dcad40f2-e5f1-4643-a8f6-8cbf78d7ea7d', false);

--changeset dvoyevodin_17102025:25
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('f12bdb63-7951-4485-973e-eb07783ba203', '1234 5678 0015 0015', NOW() + INTERVAL '1 day', 100, 'EXPIRED', 'dcad40f2-e5f1-4643-a8f6-8cbf78d7ea7d', false);

--changeset dvoyevodin_17102025:26
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('75fc2bd0-5198-41c9-801f-9131912d37c1', '1234 5678 0016 0016', NOW() + INTERVAL '1 day', 0, 'ACTIVE', '3b7361fa-90a3-47ea-bea6-a9e67acd0f50', false);

--changeset dvoyevodin_17102025:27
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('569a0e14-7a88-4dbe-ae21-e4b55b6a1124', '1234 5678 0017 0017', NOW() - INTERVAL '1 day', 300, 'EXPIRED', '3b7361fa-90a3-47ea-bea6-a9e67acd0f50', false);

--changeset dvoyevodin_17102025:28
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('7dc37e46-cd16-4828-b7b5-b35713650bbf', '1234 5678 0018 0018', NOW() - INTERVAL '1 day', 400, 'EXPIRED', '3b7361fa-90a3-47ea-bea6-a9e67acd0f50', false);

--changeset dvoyevodin_17102025:29
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('52fe045b-7dd2-4243-9004-83dadc82fa45', '1234 5678 0019 0019', NOW() + INTERVAL '1 day', 200, 'BLOCKED', '0ad4dcb7-57b3-49b9-9008-17891a3eecca', false);

--changeset dvoyevodin_17102025:30
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('2b83d9a7-0f33-4f0c-9103-0abbd522327f', '1234 5678 0020 0020', NOW() + INTERVAL '1 day', 300, 'BLOCKED', '0ad4dcb7-57b3-49b9-9008-17891a3eecca', false);

--changeset dvoyevodin_17102025:31
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('8654b7ab-cbc4-4a00-abde-e47f693fc23f', '1234 5678 0021 0021', NOW() + INTERVAL '1 day', 400, 'BLOCKED', '0ad4dcb7-57b3-49b9-9008-17891a3eecca', false);

--changeset dvoyevodin_17102025:32
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('bd0d0b27-5e5e-4fe6-a3f7-717c95093b71', '1234 5678 0022 0022', NOW() + INTERVAL '1 day', 500, 'ACTIVE', '55b5b405-2dd6-4f66-8280-5c0a22d9b95f', false);

--changeset dvoyevodin_17102025:33
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('38d79304-679c-4dde-8f09-6d7da92cf002', '1234 5678 0023 0023', NOW() + INTERVAL '1 day', 600, 'ACTIVE', '55b5b405-2dd6-4f66-8280-5c0a22d9b95f', false);

--changeset dvoyevodin_17102025:34
INSERT INTO cards (id, number, expiration, balance, status, user_id, archive_flag)
VALUES ('1e2bc0e0-c6c0-4669-91c0-12ca83ed187a', '1234 5678 0024 0024', NOW() + INTERVAL '1 day', 700, 'ACTIVE', '55b5b405-2dd6-4f66-8280-5c0a22d9b95f', false);
