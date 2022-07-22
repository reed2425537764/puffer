create table if not exists asset_list
(
    ID                 bigint auto_increment comment '主键'
    primary key,
    NAME               char(32)    not null comment '名单名称',
    LABEL              varchar(60) not null comment '显示标签',
    TYPE               varchar(10) not null comment '名单类型',
    EFFECT_PERIOD      int         not null comment '生效保质期',
    EFFECT_PERIOD_UNIT char        not null comment '生效保质期单位',
    CREATE_UID         int         not null comment '外键-创建用户ID',
    CREATE_TIME        timestamp   not null comment '创建时间',
    UPDATE_UID         int         not null comment '外键-更新用户ID',
    UPDATE_TIME        timestamp   not null comment '更新时间'
    )
    comment '资产-名单';

create table if not exists asset_list_detail
(
    ID          bigint auto_increment comment '主键'
    primary key,
    LIST_NAME   char(32)    not null comment '对应主表NAME',
    LIST_VALUE  varchar(90) not null comment '名单值',
    SOURCE      varchar(10) not null comment '来源',
    DEAD_LINE   char(14)    not null comment '到期日yyyyMMddHHmmss',
    CREATE_UID  int         not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment 'CREATE_TIME',
    UPDATE_UID  int         not null comment '外键-更新用户ID',
    UPDATE_TIME timestamp   not null comment '更新时间'
    )
    comment '资产-名单明细';

create table if not exists deploy_context
(
    ID              bigint auto_increment comment '主键'
    primary key,
    DEPLOY_ID       bigint      not null comment '外键-部署ID',
    TYPE            varchar(10) not null comment '模型内容类型',
    MAIN_ID         bigint      not null comment '外键-该模型内容对应主表ID',
    HIS_ID          bigint      not null comment '外键-该模型内容对应历史表ID',
    SUPERIOR_HIS_ID bigint      not null comment '外键-该模型上级模型的历史表ID',
    NAME            varchar(32) not null comment '模型名称',
    LABEL           varchar(60) not null comment '显示标签',
    CREATE_TIME     timestamp   not null comment '创建时间'
    )
    comment '部署-相关模型内容';

create table if not exists deploy_do_field
(
    ID           bigint auto_increment comment '主键'
    primary key,
    DEPLOY_ID    bigint      not null comment '外键-部署ID',
    DO_HIS_ID    bigint      not null comment '外键-数据对象历史ID',
    FIELD_ID     bigint      not null comment '外键-字段ID',
    FIELD_HIS_ID bigint      not null comment '外键-字段历史ID',
    FIELD_NAME   char(32)    not null comment '字段名称',
    CLASS_TYPE   varchar(20) not null comment '字段数据类型',
    CREATE_TIME  timestamp   not null comment '创建时间'
    )
    comment '模型-数据对象与字段关系';

create table if not exists deploy_rs_do
(
    ID           bigint auto_increment comment '主键'
    primary key,
    DEPLOY_ID    bigint       not null comment '外键-部署ID',
    RS_HIS_ID    bigint       not null comment '外键-规则集历史ID',
    DO_ID        bigint       not null comment '外键-数据对象ID',
    DO_HIS_ID    bigint       not null comment '外键-数据对象历史ID',
    PACKAGE_NAME varchar(120) not null comment '包名',
    CLASS_NAME   char(32)     not null comment '类名',
    CREATE_TIME  timestamp    not null comment '创建时间'
    )
    comment '部署-规则集与数据对象关系';

create table if not exists log_fired_rule
(
    ID                  bigint auto_increment comment '主键'
    primary key,
    GROUP_ID            char(32)    not null comment '项目组',
    ARTIFACT_ID         char(32)    not null comment '项目名称',
    DEPLOY_ID           bigint      not null comment '外键-部署ID',
    PROCESS_INSTANCE_ID bigint      not null comment '实例流程ID',
    RS_NAME             char(32)    not null comment '规则集名称',
    RULE_NAME           char(32)    not null comment '规则名称',
    DESCRIPTION         varchar(90) null comment '描述',
    CREATE_TIME         timestamp   not null comment '创建时间'
    )
    comment '日志-规则命中';

create table if not exists log_focus_rule
(
    ID                  bigint auto_increment comment '主键'
    primary key,
    GROUP_ID            char(32)    not null comment '项目组',
    ARTIFACT_ID         char(32)    not null comment '项目名称',
    DEPLOY_ID           bigint      not null comment '外键-部署ID',
    PROCESS_INSTANCE_ID bigint      not null comment '实例流程ID',
    PROCESS_ID          char(32)    not null comment '规则流ID',
    RS_NAME             char(32)    not null comment '规则集名称',
    DESCRIPTION         varchar(90) null comment '描述',
    CREATE_TIME         timestamp   not null comment '创建时间'
    )
    comment '日志-规则命中';

create table if not exists log_node_instance
(
    ID                  bigint auto_increment comment '主键'
    primary key,
    NODE_INSTANCE_ID    varchar(255) not null comment '节点实例ID',
    EVENT_TYPE          varchar(5)   not null comment '事件类型',
    CONNECTION          varchar(255) null comment '连接方向',
    PROCESS_INSTANCE_ID bigint       not null comment '实例流程ID',
    NODE_ID             varchar(255) not null comment '节点ID',
    NODE_NAME           varchar(255) null comment '节点名称',
    NODE_TYPE           varchar(255) null comment '节点类型',
    CREATE_TIME         timestamp    not null comment '创建时间'
    )
    comment '日志-节点实例';

create table if not exists log_process_instance
(
    ID                  bigint auto_increment comment '主键'
    primary key,
    PROCESS_ID          char(32)     null comment '流程ID',
    PROCESS_NAME        varchar(255) not null comment '流程名称',
    PROCESS_VERSION     int          not null comment '流程版本',
    PROCESS_INSTANCE_ID bigint       not null comment '流程实例ID',
    STATUS              int          not null comment '状态',
    EVENT_TYPE          char         not null comment '事件类型',
    CREATE_TIME         timestamp    not null comment '创建时间'
    )
    comment '日志-流程实例';

create table if not exists model_bpm
(
    ID          bigint auto_increment comment '主键'
    primary key,
    PROCESS_ID  varchar(32) not null comment '流程ID',
    LABEL       varchar(60) not null comment '显示标签',
    DESCRIPTION varchar(90) null comment '描述',
    BPMN        mediumtext  not null comment '规则流内容',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    HIS_ID      bigint      null comment '外键-当前历史表主键',
    SUBMIT_FLAG bit         not null comment '是否提交',
    CREATE_UID  int         null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间',
    UPDATE_UID  int         null comment '外键-更新用户ID',
    UPDATE_TIME timestamp   not null comment '更新时间'
    )
    comment '模型-规则流';

create table if not exists model_bpm_his
(
    ID          bigint auto_increment comment '主键'
    primary key,
    TYPE        char        not null comment '类型',
    FOREIGN_ID  bigint      not null comment '外键-关联表主键',
    PROCESS_ID  varchar(32) not null comment '流程ID',
    LABEL       varchar(60) not null comment '显示标签',
    DESCRIPTION varchar(90) null comment '描述',
    BPMN        mediumtext  not null comment '规则流内容',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    COMMENT     varchar(90) null comment '备注',
    CREATE_UID  int         null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间'
    )
    comment '模型-规则流历史表';

create table if not exists model_bpm_rs
(
    ID          bigint auto_increment comment '主键'
    primary key,
    PROJECT_ID  bigint    not null comment '外键-项目ID',
    BPM_ID      bigint    not null comment '外键-规则流ID',
    RULE_SET_ID bigint    not null comment '外键-规则集ID',
    CREATE_TIME timestamp not null comment '创建时间'
)
    comment '模型-规则流与规则关联';

create table if not exists model_deploy
(
    ID          bigint auto_increment comment '主键'
    primary key,
    GROUP_ID    varchar(32) not null comment '项目组',
    ARTIFACT_ID varchar(32) null comment '项目名',
    LABEL       varchar(60) not null comment '显示标签',
    DEPLOY_DESC varchar(90) not null comment '发布说明',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    LATEST      char        null comment '是否最新(YN)',
    CREATE_UID  int         null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间',
    UPDATE_TIME timestamp   null comment '更新时间'
    )
    comment '模型-部署';

create table if not exists model_do
(
    ID          bigint auto_increment comment '主键'
    primary key,
    CLASS_NAME  varchar(32) not null comment '类名simpleName',
    LABEL       varchar(60) not null comment '显示标签',
    DESCRIPTION varchar(90) null comment '描述',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    HIS_ID      bigint      not null comment '外键-历史表ID',
    CATALOG_ID  bigint      not null comment '外键-目录ID',
    DO_TYPE     varchar(10) not null comment '数据对象类型',
    CREATE_UID  int         not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   null,
    UPDATE_UID  int         not null comment '外键-更新用户ID',
    UPDATE_TIME timestamp   null comment '更新时间'
    )
    comment '模型-数据对象';

create table if not exists model_do_catalog
(
    ID          bigint auto_increment comment '主键'
    primary key,
    NAME        char(32)    not null comment '目录名称',
    LABEL       varchar(60) not null comment '显示标签',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    CREATE_UID  int         not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间',
    UPDATE_UID  int         not null comment '外键-更新用户ID',
    UPDATE_TIME timestamp   not null comment '更新时间',
    constraint model_do_catalog_NAME_uindex
    unique (NAME)
    )
    comment '模型-数据对象目录';

create table if not exists model_do_his
(
    ID          bigint auto_increment comment '主键'
    primary key,
    TYPE        char        not null comment '类型',
    FOREIGN_ID  bigint      not null comment '外键-关联表主键',
    CLASS_NAME  varchar(32) not null comment '类名simpleName',
    LABEL       varchar(60) not null comment '显示标签',
    DESCRIPTION varchar(90) not null comment '描述',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    CATALOG_ID  bigint      not null comment '外键-目录ID',
    DO_TYPE     varchar(10) not null comment '数据对象类型',
    COMMENT     varchar(90) not null comment '备注',
    CREATE_UID  bigint      not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间'
    )
    comment '模型-数据对象历史';

create table if not exists model_field
(
    ID          bigint auto_increment comment '主键'
    primary key,
    NAME        char(12)    not null comment '字段名称',
    LABEL       varchar(60) not null comment '显示标签',
    DESCRIPTION varchar(90) not null comment '描述',
    CLASS_TYPE  varchar(20) not null comment '字段数据类型',
    LIST_FLAG   char        not null comment '是否是列表Y/N',
    HIS_ID      bigint      not null comment '外键-最新的字段历史ID',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    DO_ID       bigint      not null comment '外键-数据对象ID',
    CREATE_UID  bigint      not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间',
    UPDATE_UID  int         not null comment '外键-更新用户ID',
    UPDATE_TIME timestamp   not null comment '更新时间'
    )
    comment '模型-字段';

create table if not exists model_field_his
(
    ID          bigint auto_increment comment '主键'
    primary key,
    TYPE        char        not null comment '类型',
    FOREIGN_ID  bigint      not null comment '外键-关联表主键',
    NAME        char(12)    not null comment '字段名称',
    LABEL       varchar(60) not null comment '显示标签',
    DESCRIPTION varchar(90) not null comment '描述',
    CLASS_TYPE  varchar(20) not null comment '字段数据类型',
    LIST_FLAG   char        not null comment '是否是列表Y/N',
    HIS_ID      bigint      not null comment '外键-最新的字段历史ID',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    DO_ID       bigint      not null comment '外键-数据对象ID',
    COMMENT     varchar(90) not null comment '备注',
    CREATE_UID  bigint      not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间'
    )
    comment '模型-字段历史';

create table if not exists model_project
(
    ID          bigint auto_increment comment '主键'
    primary key,
    GROUP_ID    char(32)    not null comment '项目组',
    ARTIFACT_ID char(32)    not null comment '项目名称',
    LABEL       varchar(60) not null comment '显示标签',
    DESCRIPTION varchar(90) not null comment '描述',
    HIS_ID      bigint      not null comment '外键-当前历史表主键',
    DEPLOY_ID   bigint      not null comment '外键-当前部署ID',
    CREATE_UID  int         not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间',
    UPDATE_UID  int         not null comment '外键-更新用户ID',
    UPDATE_TIME timestamp   not null comment '更新时间'
    )
    comment '模型-项目';

create table if not exists model_project_his
(
    ID          bigint auto_increment comment '主键'
    primary key,
    TYPE        char        not null comment '类型',
    FOREIGN_ID  bigint      not null comment '外键-关联表主键',
    GROUP_ID    char(32)    not null comment '项目组',
    ARTIFACT_ID char(32)    not null comment '项目名称',
    LABEL       varchar(60) not null comment '显示标签',
    DESCRIPTION varchar(90) not null comment '描述',
    COMMENT     varchar(90) not null comment '备注',
    CREATE_UID  int         not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间'
    )
    comment '模型-项目';

create table if not exists model_rs
(
    ID          bigint auto_increment comment '主键'
    primary key,
    NAME        char(32)    not null comment '名称',
    LABEL       varchar(60) not null comment '显示标签',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    HIS_ID      bigint      not null comment '外键-当前历史表主键',
    CATALOG_ID  bigint      not null comment '外键-目录ID',
    CREATE_UID  int         not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间',
    UPDATE_UID  int         not null comment '外键-更新用户ID',
    UPDATE_TIME timestamp   not null comment '更新时间'
    )
    comment '模型-规则集';

create table if not exists model_rs_catalog
(
    ID          bigint auto_increment comment '主键'
    primary key,
    NAME        char(32)    not null comment '名称',
    LABEL       varchar(60) not null comment '显示标签',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    CREATE_UID  int         not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间',
    UPDATE_UID  int         not null comment '外键-更新用户ID',
    UPDATE_TIME timestamp   not null comment '更新时间'
    )
    comment '模型-规则集目录';

create table if not exists model_rs_his
(
    ID          bigint auto_increment comment '主键'
    primary key,
    TYPE        char        not null comment '类型',
    FOREIGN_ID  bigint      not null comment '外键-关联表主键',
    NAME        char(32)    not null comment '名称',
    LABEL       varchar(60) not null comment '显示标签',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    CATALOG_ID  bigint      not null comment '外键-目录ID',
    COMMENT     varchar(90) not null comment '备注',
    CREATE_UID  int         not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间'
    )
    comment '模型-规则集';

create table if not exists model_rule
(
    ID          bigint auto_increment comment '主键'
    primary key,
    NAME        char(32)    not null comment '名称',
    LABEL       varchar(60) not null comment '显示标签',
    RS_ID       bigint      not null comment '外键-规则集ID',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    HIS_ID      bigint      not null comment '外键-历史表ID',
    RULE_TYPE   char(3)     not null comment '规则类型',
    DRL         text        not null comment '规则语法',
    ENABLE      char        not null comment '是否启用Y/N',
    CREATE_UID  int         not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间',
    UPDATE_UID  int         not null comment '外键-更新用户ID',
    UPDATE_TIME timestamp   not null comment '更新时间'
    )
    comment '模型-规则';

create table if not exists model_rule_do
(
    ID          bigint auto_increment comment '主键'
    primary key,
    RULE_ID     bigint    not null comment '外键-规则ID',
    RULE_HIS_ID bigint    not null comment '外键-规则历史ID',
    DO_ID       bigint    not null comment '外键-数据对象ID',
    FIELD_ID    bigint    null comment '外键-字段ID',
    CREATE_TIME timestamp not null comment '创建时间'
)
    comment '模型-规则与数据对象关系';

create table if not exists model_rule_his
(
    ID          bigint auto_increment comment '主键'
    primary key,
    TYPE        char        not null comment '类型',
    FOREIGN_ID  bigint      not null comment '外键-关联表主键',
    NAME        char(32)    not null comment '名称',
    LABEL       varchar(60) not null comment '显示标签',
    RS_ID       bigint      not null comment '外键-规则集ID',
    PROJECT_ID  bigint      not null comment '外键-项目ID',
    RULE_TYPE   char(3)     not null comment '规则类型',
    DRL         text        not null comment '规则语法',
    ENABLE      char        not null comment '是否启用Y/N',
    COMMENT     varchar(90) not null comment '备注',
    CREATE_UID  int         not null comment '外键-创建用户ID',
    CREATE_TIME timestamp   not null comment '创建时间'
    )
    comment '模型-规则';

-- puffer.deploy_rs_field definition

CREATE TABLE `deploy_rs_field` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DEPLOY_ID` bigint(20) NOT NULL COMMENT '外键-部署ID',
  `RS_HIS_ID` bigint(20) NOT NULL COMMENT '外键-规则集历史ID',
  `DO_HIS_ID` bigint(20) NOT NULL COMMENT '外键-数据对象历史ID',
  `FIELD_HIS_ID` bigint(20) NOT NULL COMMENT '外键-字段历史ID',
  `RULE_DEPS` char(1) NOT NULL COMMENT '该字段是否被规则依赖',
  `RULE_HIS_IDS` text COMMENT '字段被规则依赖的历史ID',
  `CUSTOM_DEPS` char(1) NOT NULL COMMENT 'Y-是N-否D-默认 该字段是否是业务自定义的依赖. D-使用规则依赖;N-不触发该依赖;Y-不管是否有规则依赖,必须触发因为是业务自定义的依赖',
  `CREATE_TIME` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) COMMENT='部署-规则集与字段关系';


-- puffer.model_rs_field definition

CREATE TABLE `model_rs_field` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PROJECT_ID` bigint(20) NOT NULL COMMENT '外键-项目ID',
  `RS_ID` bigint(20) NOT NULL COMMENT '外键-规则集ID',
  `DO_ID` bigint(20) NOT NULL COMMENT '外键-数据对象ID',
  `FIELD_ID` bigint(20) NOT NULL COMMENT '外键-字段ID',
  `RULE_DEPS` char(1) NOT NULL COMMENT '该字段是否被规则依赖',
  `RULE_IDS` text NOT NULL COMMENT '规则list',
  `CUSTOM_DEPS` char(1) NOT NULL,
  `CREATE_UID` int(11) NOT NULL COMMENT '外键-用户ID',
  `CREATE_TIME` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) COMMENT='模型-规则集与字段关系';