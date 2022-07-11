# flowable

## 集成flowable-ui

* flowable-ui version 6.7.2
* springboot version 2.6.9

### 出现的问题

* 问题：开启`database-schema-update: true`，但是提示表不存在
    * 原因：数据库实例的其他database中存在flow的表，jdbc默认根据catalog来扫描全部的表，flowable会认为表存在了，但是实际上表不存在
    * 解决：jdbc:url添加`nullCatalogMeansCurrent=true`
