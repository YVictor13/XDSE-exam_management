
/*Data for the table `student_info` */

insert  into `student_info`(`student_id`,`info_state`,`sex`,`nation`,`political`,`job`,`degree`,`health`,`census_place`,`student_type`,`id_card_type`,`id_card_num`,`name`,`birthday`,`phone_number`,`address`,`post_code`,`work_place`,`photo_path`,`english_name`,`email`) values
('20200001',0,1,1,2,NULL,NULL,1,1111,NULL,NULL,'644658199908046570','李四','20000116','','','','','20200001',NULL,''),
('20200002',0,2,1,2,NULL,NULL,NULL,NULL,NULL,NULL,'644689131654649887','张三','20000117',NULL,NULL,'',NULL,'20200002',NULL,NULL),
('20200003',0,1,1,2,NULL,NULL,1,1111,NULL,NULL,'644658199908046570','小明','20000116','12389764560','北京','','西安人事所','20200003',NULL,'789456123@qq.com'),
('20200004',0,1,1,2,NULL,NULL,1,1111,NULL,NULL,NULL,'王五',NULL,NULL,NULL,'',NULL,'20200004',NULL,NULL);

-- 时间管理表测试数据
insert  into `time_manage`(`user_id`,`begin_time`,`end_time`) values
(0,'2020-08-01','2020-08-02'),
(1,'2020-08-06','2020-08-06'),
(2,'2020-08-14','2020-08-16'),
(3,'2020-08-17','2020-08-20'),
(4,'2020-08-22','2020-08-25');


-- 考次信息数据
insert  into `exam_info`(`exam_num`,`start_date`,`end_date`) values
('202010','20201015','20201017'),
('202012','20201215','20201217');

/*Data for the table `course` */

insert  into `course`(`course_code`,`course_name`,`course_english_name`) values
('0001','java','java'),
('0002','python','python'),
('0003','c++','c++');

/*Data for the table `major` */
insert into `major` values('CS0101', '计算机科学与技术', '一级学科', '本科', '数据科学');
insert into `major` values('CS0102', '计算机科学与技术', '一级学科', '本科', '计算机系统');
insert into `major` values('CS0201', '软件工程', '一级学科', '本科', '大数据工程');

/*Data for the table `sign_up_info` */
insert  into `sign_up_info`(`exam_card_num`,`student_id`,`exam_num`,`major_num`,`city_code`,`region_code`,`sign_up_school`,`sign_up_type`,`sign_up_date`) values
('010120100001','20200001','202010',NULL,'1','002',NULL,NULL,NULL),
('010120100002','20200002','202010',NULL,'1','003',NULL,NULL,NULL),
('010120100003','20200001','202012',NULL,'1','004',NULL,NULL,NULL);
update sign_up_info set major_num = 'CS0101' where exam_card_num = '010120100001';
update sign_up_info set major_num ='CS0102' where exam_card_num = '010120100002';
update sign_up_info set major_num ='CS0201' where exam_card_num = '010120100003';

/*Data for the table `grade` */

insert  into `grade`(`exam_card_num`,`course_code`,`grade`,`grade_type`,`valid`,`putin_state`) values
('010120100001','0001','98',6,1,1),
('010120100001','0002','90',6,1,0),
('010120100003','0003','58',6,0,NULL);

/*Data for the table `one_class_apply` */

insert  into `one_class_apply`(`id`,`old_ecn`,`new_ecn`,`old_course_code`,`new_course_code`,`city_code`,`region_code`,`apply_state`,`result`,`start_date`,`end_date`) values
(1,'010120100001','010120100001','0001','0001',NULL,NULL,NULL,NULL,NULL,NULL),
(5,'010120100001','010120100001','0001','0001','1','002',NULL,NULL,NULL,NULL),
(6,'010120100001','010120100003','0001','0001','1','002',1,NULL,'20200902',NULL),
(7,'010120100001','010120100003','0002','0002','1','002',1,NULL,'20200902',NULL),
(8,'010120100001','010120100003','0002','0002','1','002',0,NULL,'20200902',NULL);

/* 用户信息 */
insert into `account` values('010120100001', '123456', 1);
insert into `account` values('010120100002', '123456', 1);
