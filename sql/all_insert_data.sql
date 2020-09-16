/*Data for the table `const_variable` */

insert  into `const_variable`(`name`,`value`) values
('cur_exam_num','2020-01'),
('province_code','001');


/*Data for the table `course` */

insert  into `course`(`course_code`,`course_name`,`course_english_name`) values
('0001','java','java'),
('0002','python','python'),
('0003','c++','c++');

/*Data for the table `exam_info` */
insert  into `exam_info`(`exam_num`,`start_date`,`end_date`) values
('202010','20201015','20201017'),
('202012','20201215','20201217');


/*Data for the table `grade` */

insert  into `grade`(`exam_card_num`,`course_code`,`grade`,`grade_type`,`valid`,`putin_state`) values
('010120100001','0001','98',6,1,1),
('010120100001','0002','90',6,1,0),
('010120100003','0001','98',3,1,1),
('010120100003','0002','90',3,1,1),
('010120100003','0003','58',6,0,0);

/*Data for the table `graduate_apply` */

insert  into `graduate_apply`(`apply_id`,`exam_card_num`,`apply_state`,`result`,`start_date`,`end_date`) values
(1,'010120100001',1,1,NULL,NULL),
(2,'010120100002',1,1,NULL,NULL),
(3,'010120100003',1,1,NULL,NULL);

/*Data for the table `graduate_cert` */

insert  into `graduate_cert`(`graduate_id`,`exam_card_num`,`graduate_date`) values
('100020200903','010120100001','20190904'),
('100020200904','010120100002','20180904'),
('100020200905','010120100004','20170904');

/*Data for the table `one_class_apply` */

insert  into `one_class_apply`(`id`,`old_ecn`,`new_ecn`,`old_course_code`,`new_course_code`,`city_code`,`region_code`,`apply_state`,`result`,`start_date`,`end_date`) values
(1,'010120100001','010120100001','0001','0001',NULL,NULL,3,1,NULL,'20200903'),
(5,'010120100001','010120100001','0001','0001','1','002',NULL,NULL,NULL,NULL),
(6,'010120100001','010120100003','0001','0001','1','002',0,0,'20200902','20200902'),
(7,'010120100001','010120100003','0002','0002','1','002',3,1,'20200902','20200904'),
(8,'010120100001','010120100003','0003','0003','1','002',1,NULL,'20200902',NULL);

/*Data for the table `sign_up_info` */

insert  into `sign_up_info`(`exam_card_num`,`student_id`,`exam_num`,`major_num`,`city_code`,`region_code`,`sign_up_school`,`sign_up_type`,`sign_up_date`) values
('010120100001','20200001','202010','软工','61','002','西电',NULL,NULL),
('010120100002','20200002','202012','软工','61','003','西电',NULL,NULL),
('010120100003','20200001','202012','计科','61','004','西电',NULL,NULL),
('010120100004','20200003','202010','软工','11','005','北邮',NULL,NULL);

/*Data for the table `student_info` */

insert  into `student_info`(`student_id`,`info_state`,`sex`,`nation`,`political`,`job`,`degree`,`health`,`census_place`,`student_type`,`id_card_type`,`id_card_num`,`name`,`birthday`,`phone_number`,`address`,`post_code`,`work_place`,`photo_path`,`english_name`,`email`) values
('20200001',0,1,1,2,NULL,NULL,1,1111,NULL,NULL,'644658199908046570','李四','20000116','','','1234566','',NULL,NULL,''),
('20200002',0,2,1,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'张三','20000117',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('20200003',0,1,1,2,NULL,NULL,1,1111,NULL,NULL,'644658199908046570','小明','20000116','12389764560','北京','1234566','西安人事所',NULL,NULL,'789456123@qq.com'),
('20200004',0,1,1,2,NULL,NULL,1,1111,NULL,NULL,NULL,'王五',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Data for the table `time_manage` */

insert  into `time_manage`(`user_id`,`begin_time`,`end_time`) values
(0,'2020-08-01','2020-08-02'),
(1,'2020-08-06','2020-08-06'),
(2,'2020-08-14','2020-08-16'),
(3,'2020-08-17','2020-08-20'),
(4,'2020-08-22','2020-08-25'),
(NULL,NULL,NULL);


/*Data for the table `two_class_apply` */

insert  into `two_class_apply`(`id`,`graduate_id`,`exam_card_num`,`old_course_code`,`new_course_code`,`city_code`,`region_code`,`apply_state`,`result`,`start_date`,`end_date`) values
(1,'100020200903','010120100003','0001','0001','1','002',0,0,'20200903','20200903'),
(2,'100020200903','010120100003','0002','0002','1','002',2,1,'20200903','20200903');


