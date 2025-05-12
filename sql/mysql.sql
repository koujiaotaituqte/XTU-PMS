CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
                        `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
                        `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
                        `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
                        `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮件',
                        `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
                        `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `username_index` (`username`) USING BTREE COMMENT '账号唯一'
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息';


CREATE TABLE `admin` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
                         `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
                         `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
                         `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
                         `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
                         `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮件',
                         `role` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
                         `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `username_index` (`username`) USING BTREE COMMENT '账号唯一'
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员信息';


CREATE TABLE `notice` (
                          `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                          `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公告标题',
                          `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公告内容',
                          `time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统公告表';

CREATE TABLE `bookroom` (
                            `roomtype_id` int NOT NULL COMMENT '房间类型id',
                            `user_id` int NOT NULL COMMENT '用户id',
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '订单号',
                            `start_time` date NOT NULL COMMENT '入住时间',
                            `day` int DEFAULT NULL COMMENT '入住时长',
                            `end_time` date DEFAULT NULL COMMENT '退房时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `room` (
                        `id` int NOT NULL COMMENT '房间号',
                        `roomtype_id` int NOT NULL COMMENT '房间类型id',
                        `status` int DEFAULT NULL COMMENT '状态，0为没住人',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `roomtype` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '房间类型id',
                            `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名字',
                            `money` int DEFAULT NULL COMMENT '价格',
                            `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片',
                            `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '房间描述',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;