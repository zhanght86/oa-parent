# meetingroom-parent

##### 会议室的增删改查基本信息管理，供会议室使用申请流程调用
  * api：后台的service和dao的接口
  * biz：后台service接口的实现
  * dao：后台dao接口的实现
  * entities：javaBean实体类
  * extjs：前台增删改查界面的展示
  * rest：前台到后台的rest服务配置
  * web：前台菜单的实现

# 使用
引用该模块所生成的osgi jar包，在karaf的环境etc目录下的ConfigMenu.cfg配置文件中增加菜单配置，菜单的配置详见“菜单的配置”说明。
