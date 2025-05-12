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