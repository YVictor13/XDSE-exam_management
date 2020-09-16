use exam_management;

drop table if exists student_info;
create table student_info
(
    student_id   VARCHAR(16), -- 学生ID
    info_state   INT,         -- 0,正常;1,已毕业;2,已转出;3,毕业预提;4,退学
    sex          INT,         -- 性别编码，1:男;2:女
    nation       INT,         -- 民族编码，1：汉；具体参考教育部考试中心名族编码表
    political    INT,         -- 政治面貌编码，1：党员；2：团员；3：其他；255：未采集
    job          INT,         -- 职业编码，具体参考教育部考试中心职业编码表
    degree       INT,         -- 学位编码，1:本科以上;2:本科;3:大专(专科);4:中专(中 技);5:高中(职高);6:初中及初中以下;255:未采集
    health       INT,         -- 健康状况编码，1：健康；2：残疾
    census_place INT,         -- 户籍地编码，按标准编码存储
    student_type INT,         -- 学生类型编码，1:一类；2:二类；3:三类；4:四类
    id_card_type INT,         -- 身份证件类型代码
    id_card_num  VARCHAR(32), -- 省份证件号
    name         VARCHAR(16), -- 姓名
    birthday     VARCHAR(8),  -- 生日
    phone_number VARCHAR(16), -- 手机号码
    address      VARCHAR(32), -- 地址
    post_code    VARCHAR(8),  -- 邮政编码
    work_place   VARCHAR(16), -- 工作单位
    photo_path   VARCHAR(32), -- 照片路径
    english_name VARCHAR(8),  -- 英文名称

    email        VARCHAR(32),-- 电子邮件
    primary key (student_id)
);

drop table if exists exam_info;
create table exam_info
(
    exam_num   VARCHAR(6), -- 例子:201901 2019年一月考试
    start_date VARCHAR(16), -- 考次开始时间
    end_date   VARCHAR(16), -- 考次结束时间
    primary key (exam_num)
);

drop table if exists major;
create table major
(
    major_num   VARCHAR(8), -- 专业代码
    major_name  VARCHAR(8), -- 专业名称
    major_level VARCHAR(8), -- 专业层次代码
    major_type  VARCHAR(8), -- 专业类型代码 61：专科；62：基础科段； 51：本科；52：本科段；53：独立本科段
    major_dir   VARCHAR(8), -- 专业方向
    primary key (major_num)
);

drop table if exists sign_up_info;
create table sign_up_info
(
    exam_card_num  VARCHAR(12), -- 准考证号
#     长12 位数字，其格式如下： 市代码（两位）+ 县代码（两位）+ 年度末两位+ 年度内报名次数（一位）+ 考生流水号（五位）。
#     必须 按县编排准考证号，即县代码不能为"00"。
#     例如：2010年0101县某第一次报名，考生报名后的 准考证号码为010110100001
#     准考证上，短线和长线只有一个区别：第7 位：1、2 社会考试，3、4 是短线。
#     因为将学校视为一个区 县，因此不会学校之间重复。
    student_id     VARCHAR(16), -- 学生ID
    exam_num       VARCHAR(6),  -- 例子:201901 2019年一月考试

    major_num      VARCHAR(8),  -- 专业代码
    city_code      VARCHAR(8),  -- 报名地市州编码
    region_code    VARCHAR(8),  -- 报名区县编码
    sign_up_school VARCHAR(32), -- 报名学校
    sign_up_type   INT,         -- 报名形式，0：本地报名；1：外地转入
    sign_up_date   VARCHAR(8),  -- 报名时间

    foreign key (student_id) references student_info (student_id),
    foreign key (exam_num) references exam_info (exam_num),
    foreign key (major_num) references major (major_num),
    primary key (exam_card_num)
);

# 考籍修改申请
drop table if exists info_change_apply;
create table info_change_apply
(
    id            VARCHAR(16), -- ID
    exam_card_num VARCHAR(12), -- 准考证号

    field_name    VARCHAR(32), -- 字段名称
    old_value     VARCHAR(32), -- 旧值
    new_value     VARCHAR(32), -- 新值
    change_reason VARCHAR(256),-- 更改原因

    change_state  INT,         -- 审核状态
    city_opinion  VARCHAR(8),  -- 地市州审核意见
    root_opinion  VARCHAR(8),  -- 省考办审核意见

    handle_time   VARCHAR(8),  -- 办理完成时间
    result_state  INT,         -- 办理结果 0正常 1撤销

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    primary key (id)
);

# 考籍修改日志
create table info_change_log
(
    id INT not null auto_increment, -- 日志ID
    exam_card_num VARCHAR(12), -- 准考证号
    id_card_type INT,         -- 身份证件类型代码
    id_card_num  VARCHAR(32), -- 省份证件号
    change_content VARCHAR(256), -- 修改内容
    change_date VARCHAR(8),

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    primary key (id)
);
# 考籍操作日志
create table info_operate_log
(
    id INT not null auto_increment,
    operator_name VARCHAR(16),
    operate_content VARCHAR(128),
    operate_date VARCHAR(8),
    primary key (id)
);

drop table if exists ecn_change_log;
create table ecn_change_log
(
    id           VARCHAR(16), -- ID
    old_ECN      VARCHAR(12), -- 原准考证号
    new_ECN      VARCHAR(12), -- 新准考证号

    change_state INT,         -- 审核状态
    city_opinion VARCHAR(8),  -- 地市州审核意见
    root_opinion VARCHAR(8),  -- 省考办审核意见

    handle_time  VARCHAR(8),  -- 办理完成时间
    result_state INT,         -- 办理结果 0正常 1撤销
    foreign key (old_ECN) references sign_up_info(exam_card_num),
    primary key (id)
);

drop table if exists course;
create table course
(
    course_code         VARCHAR(16), -- 课程代码
    course_name         VARCHAR(16), -- 课程名称
    course_english_name VARCHAR(64), -- 课程英文名称
    primary key (course_code)
);

drop table if exists major_course;
create table major_course
(
    id          int not null auto_increment, -- id主键
    major_num   VARCHAR(8),                  -- 专业代码
    course_code VARCHAR(16),                 -- 课程代码

    foreign key (major_num) references major(major_num),
    foreign key (course_code) references course(course_code),
    primary key (id)
);

drop table if exists grade;
create table grade
(
    exam_card_num VARCHAR(12), -- 准考证号
    course_code   VARCHAR(16), -- 课程代码
    grade         VARCHAR(8),  -- 成绩
    grade_type    INT,         -- 成绩类型，0：国考；1：转入；2：实践课；3：免考；4：特批；5：省考；6：手工加入
    valid         INT,         -- 是否有效，是否被注销
    putin_state   INT,         -- 是否入库，0未入库，1入库

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    foreign key (course_code) references course(course_code),
    primary key (exam_card_num, course_code)
);

create table graduate_cert
(
    graduate_id   VARCHAR(16), -- 毕业证编号
#     毕业证毕业编号生成规则：
#         X-6
#         X-5（本科）6（专科）
#         XX-省代码（51）
#         12 位准考证号
#         X-校验位（0-9）
    exam_card_num VARCHAR(12), -- 准考证号
    graduate_date VARCHAR(8),

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    primary key (graduate_id)
);

create table one_class_apply
(
    id              int not null auto_increment,
    old_ecn         VARCHAR(12), -- 原准考证号
    new_ecn         VARCHAR(12), -- 现准考证号
    old_course_code VARCHAR(16), -- 原课程编码
    new_course_code VARCHAR(16), -- 申请免考课程编码
    city_code       VARCHAR(8),  -- 报名地市州编码
    region_code     VARCHAR(8),  -- 报名区县编码
    apply_state     INT,         -- 审核状态
    result          INT,         -- 申请结果
    start_date      VARCHAR(8),  -- 开始时间
    end_date        VARCHAR(8),  -- 结束时间

    foreign key (old_ecn) references sign_up_info(exam_card_num),
    foreign key (old_course_code) references course(course_code),
    foreign key (new_course_code) references course(course_code),
    primary key (id)
);

create table two_class_apply
(
    id              int not null auto_increment,
    graduate_id     VARCHAR(16), -- 毕业证编号
    exam_card_num   VARCHAR(12), -- 准考证号
    old_course_code VARCHAR(16), -- 原课程编码
    new_course_code VARCHAR(16), -- 申请免考课程编码
    city_code       VARCHAR(8),  -- 报名地市州编码
    region_code     VARCHAR(8),  -- 报名区县编码
    apply_state     INT,         -- 审核状态
    result          INT,         -- 申请结果
    start_date      VARCHAR(8),  -- 开始时间
    end_date        VARCHAR(8),  -- 结束时间

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    foreign key (old_course_code) references course(course_code),
    foreign key (new_course_code) references course(course_code),
    primary key (id)
);

create table three_class_apply
(
    id              int not null auto_increment,
    card_num        VARCHAR(32), -- 证件号
    card_type       INT,         -- 证件类型
    exam_card_num   VARCHAR(12), -- 准考证号
    new_course_code VARCHAR(16), -- 申请免考课程编码
    city_code       VARCHAR(8),  -- 报名地市州编码
    region_code     VARCHAR(8),  -- 报名区县编码
    apply_state     INT,         -- 审核状态
    result          INT,         -- 申请结果
    start_date      VARCHAR(8),  -- 开始时间
    end_date        VARCHAR(8),  -- 结束时间

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    foreign key (new_course_code) references course(course_code),
    primary key (id)
);

# 不保存地区，使用准考证号获取报名地区
create table course_replace_apply
(
    apply_id      int not null auto_increment, -- 申请ID
    -- 同一准考证下的课程顶替
    exam_card_num VARCHAR(12),                 -- 准考证号
    apply_state   INT,                         -- 审核状态
    result        INT,                         -- 申请结果
    start_date    VARCHAR(8),                  -- 开始时间
    end_date      VARCHAR(8),                  -- 结束时间

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    primary key (apply_id)
);
# 课程顶替中原课程
create table cra_old_courses
(
    apply_id    int not null auto_increment, -- 申请ID
    course_code VARCHAR(16),                 -- 课程代码

    foreign key (course_code) references course(course_code),
    primary key (apply_id, course_code)
);
# 课程顶替中现课程
create table cra_new_courses
(
    apply_id    int not null auto_increment, -- 申请ID
    course_code VARCHAR(16),                 -- 课程代码

    foreign key (course_code) references course(course_code),
    primary key (apply_id, course_code)
);

create table graduate_pre_apply
(
    id            INT not null auto_increment,
    exam_card_num VARCHAR(12), -- 准考证号
    apply_state   INT,         -- 审核状态
    result        INT,         -- 申请结果
    start_date    VARCHAR(8),  -- 开始时间
    end_date      VARCHAR(8),  -- 结束时间

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    primary key (id)
);

create table graduate_apply
(
    apply_id      INT not null auto_increment,
    exam_card_num VARCHAR(12), -- 准考证号
    -- 通过准考证号获取专业毕业要求
    apply_state   INT,         -- 审核状态
    result        INT,         -- 申请结果
    start_date    VARCHAR(8),  -- 开始时间
    end_date      VARCHAR(8),  -- 结束时间

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    primary key (apply_id)
);

create table paper
(
    paper_id VARCHAR(32), -- 论文ID
    paper_name VARCHAR(256), -- 论文名称
    pub_date VARCHAR(8), -- 发表时间
    exam_card_num VARCHAR(12), -- 准考证号

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    primary key (paper_id)
);

create table roll_out_apply
(
    apply_id INT not null auto_increment, -- 申请编号
    exam_card_num VARCHAR(12), -- 准考证号
    apply_code VARCHAR(32), -- 转出省代码 转入省代码 准考证号
    roll_out_time VARCHAR(8), -- 转出时间
    roll_out_reason VARCHAR(128), -- 转出原因

    apply_state   INT,         -- 审核状态
    check_person VARCHAR(8), -- 审核人姓名
    result        INT,         -- 申请结果
    start_date    VARCHAR(8),  -- 开始时间
    end_date      VARCHAR(8),  -- 结束时间

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    primary key (apply_id)
);

create table roll_in_apply
(
    apply_id INT not null auto_increment, -- 申请编号
    exam_card_num VARCHAR(12), -- 准考证号
    apply_code VARCHAR(32), -- 转出省代码 转入省代码 准考证号
    roll_in_time VARCHAR(8), -- 转入时间
    roll_in_major_num VARCHAR(8), -- 专业代码
    roll_out_reason VARCHAR(128), -- 转出原因

    apply_state   INT,         -- 审核状态
    check_person VARCHAR(8), -- 审核人姓名
    result        INT,         -- 申请结果
    start_date    VARCHAR(8),  -- 开始时间
    end_date      VARCHAR(8),  -- 结束时间

    foreign key (exam_card_num) references sign_up_info(exam_card_num),
    primary key (apply_id)
);

drop table if exists account;
create table account
(
    account  VARCHAR(16), -- 准考证号
    password VARCHAR(16), -- 密码
    role     INT,
    primary key (account)
);

create table const_variable
(
    name VARCHAR(16), -- 常量名
    value VARCHAR(16), -- 常量值
    primary key (name)
);
insert into const_variable values('cur_exam_num', '2020-01');
insert into const_variable values('province_code', '001');

#审核处理时间表
DROP TABLE IF EXISTS `time_manage`;

CREATE TABLE `time_manage` (
                               `user_id` int(2) DEFAULT NULL COMMENT '时间修改对象:0在线申请，1：县区，2：市区3：考试院，4：入库时间',
                               `begin_time` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '办理开始时间',
                               `end_time` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '办理结束时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


