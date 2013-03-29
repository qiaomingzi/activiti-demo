create table if not exists  OA_LEAVE
(
   ID                   bigint auto_increment COMMENT 'ID',
   PROCESS_INSTANCE_ID  VARCHAR(64)  COMMENT '流程ID',
   USER_ID              VARCHAR(20)           not null  COMMENT '工号',
   START_TIME           TIMESTAMP            not null  COMMENT '开始时间',
   END_TIME             TIMESTAMP            not null  COMMENT '结束时间',
   LEAVE_TYPE           VARCHAR(20)  COMMENT '假种',
   REASON               VARCHAR(2000)  COMMENT '请假事由',
   APPLY_TIME           TIMESTAMP            not null  COMMENT '申请时间',
   REALITY_START_TIME   TIMESTAMP  COMMENT '实际开始时间',
   REALITY_END_TIME     TIMESTAMP  COMMENT '实际结束时间',
   constraint PK_OA_LEAVE primary key (ID)
);